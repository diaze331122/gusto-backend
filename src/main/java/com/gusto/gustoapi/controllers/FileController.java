package com.gusto.gustoapi.controllers;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.gusto.gustoapi.models.DraftRecipe;
import com.gusto.gustoapi.models.Recipe;
import com.gusto.gustoapi.services.FileService;
import com.gusto.gustoapi.services.RecipeService;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/files")
public class FileController {


    @Autowired
    private FileService fileService;

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/drafts/{id}")
    public ResponseEntity<Void> insertDraftImageFile(
            @PathVariable String id,
            @RequestPart(name = "file") MultipartFile file,
            @RequestPart(name = "authorId") String authorId,
            @RequestPart(name = "imageFileType") String imageFileType) throws IOException {

        DraftRecipe recipe = recipeService.getDraftRecipe(id);

        if (recipe == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!Objects.equals(recipe.getAuthorId(), authorId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<DraftRecipe.RecipeFile> files = recipe.getFiles() != null ? recipe.getFiles() : new ArrayList<>();

        String bucket = "gusto-recipes-image-files";
        String originalFilename = file.getOriginalFilename();
        String key = String.format("%s-%d-%s", id, files.size()+1, imageFileType);
        InputStream inputStream = new BufferedInputStream(file.getInputStream());
        ObjectMetadata metadata = new ObjectMetadata();

        Tika tika = new Tika();
        String detectedMimeType = tika.detect(inputStream);

        if (detectedMimeType != null) {
            metadata.setContentType(detectedMimeType);
        }

        //Save file to S3
        fileService.putObject(bucket, key, inputStream, metadata);

        //Create a recipe file and save to draft
        DraftRecipe.RecipeFile recipeFile = new DraftRecipe.RecipeFile();

        recipeFile.setBucket(bucket);
        recipeFile.setFilename(key);
        recipeFile.setOriginalFilename(originalFilename);
        recipeFile.setUploaderId(authorId);
        recipeFile.setUploadedAt(LocalDateTime.now());

        files.add(recipeFile);

        recipe.setFiles(files);

        recipeService.updateDraftRecipe(recipe);

        return null;
    }


    @PostMapping("/recipes/{id}")
    public ResponseEntity<Recipe> insertRecipeImageFile(
            @PathVariable String id,
            @RequestPart(name = "file") MultipartFile file,
            @RequestPart(name = "authorId") String authorId) throws IOException {

        Recipe recipe = recipeService.getRecipe(id);

        if (recipe == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Recipe.RecipeFile> files = recipe.getFiles() != null ? recipe.getFiles() : new ArrayList<>();

        String bucket = "gusto-recipes-image-files";
        String key = String.format("%s-%d-%s", id, files.size()+1, "SUPP");
        InputStream inputStream = new BufferedInputStream(file.getInputStream());
        ObjectMetadata metadata = new ObjectMetadata();

        Tika tika = new Tika();
        String detectedMimeType = tika.detect(inputStream);

        if (detectedMimeType != null) {
            metadata.setContentType(detectedMimeType);
        }

        //Save file to S3
        fileService.putObject(bucket, key, inputStream, metadata);

        Recipe.RecipeFile recipeFile = new Recipe.RecipeFile();
        recipeFile.setBucket(bucket);
        recipeFile.setFilename(key);
        recipeFile.setUploaderId(authorId);
        recipeFile.setUploadedAt(LocalDateTime.now());
        files.add(recipeFile);

        recipe.setFiles(files);

        return new ResponseEntity<>(recipeService.updateRecipe(recipe), HttpStatus.OK);
    }


    @DeleteMapping("/drafts/{id}")
    public ResponseEntity<Void> deleteDraftRecipeImage(
            @PathVariable String id,
            @RequestParam(name = "filename") String filename
    ) throws IOException {

        DraftRecipe recipe = recipeService.getDraftRecipe(id);

        if (recipe == null || recipe.getFiles() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        DraftRecipe.RecipeFile file = recipe.getFiles().stream()
                .filter(recipeFile -> filename.equals(recipeFile.getFilename()))
                .findAny()
                .orElse(null);

        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final String bucket = "gusto-recipes-image-files";

        fileService.deleteObject(bucket, file.getFilename());

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<Void> deleteRecipeImage(
            @PathVariable String id,
            @RequestParam String filename
    ) throws IOException {
        Recipe recipe = recipeService.getRecipe(id);

        if (recipe == null || recipe.getFiles() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Recipe.RecipeFile file = recipe.getFiles().stream()
                .filter(recipeFile -> filename.equals(recipeFile.getFilename()))
                .findAny()
                .orElse(null);

        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String bucket = "gusto-recipes-image-files";

        fileService.deleteObject(bucket, file.getFilename());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
