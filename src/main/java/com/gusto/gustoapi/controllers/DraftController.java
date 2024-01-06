package com.gusto.gustoapi.controllers;

import com.gusto.gustoapi.dtos.UpdateDraftRecipeDto;
import com.gusto.gustoapi.enums.DraftRecipeStatus;
import com.gusto.gustoapi.models.DraftRecipe;
import com.gusto.gustoapi.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/drafts")
public class DraftController {

    @Autowired
    private RecipeService recipeService;

    //Initialize Draft
    @PostMapping("")
    public ResponseEntity<DraftRecipe> createDraftRecipe(@RequestParam(name = "author_id") String authorId) {
        DraftRecipe newDraft = recipeService.createEmptyDraftRecipe(authorId);

        return new ResponseEntity<DraftRecipe>(newDraft, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<DraftRecipe>> getDraftRecipes(@RequestParam(name = "author_id") String authorId) {
        List<DraftRecipe> drafts = recipeService.getDraftRecipesByAuthorIdAndStatus(authorId, DraftRecipeStatus.CREATED);

        return new ResponseEntity<List<DraftRecipe>>(drafts, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DraftRecipe> getDraftRecipe(@PathVariable String id) {
        DraftRecipe draft = recipeService.getDraftRecipe(id);

        return new ResponseEntity<DraftRecipe>(draft, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DraftRecipe> updateDraftRecipe(
            @PathVariable String id,
            @RequestBody UpdateDraftRecipeDto updatedDraft) {

        DraftRecipe draft = recipeService.getDraftRecipe(id);

        if (draft == null) {
            return new ResponseEntity<DraftRecipe>((DraftRecipe) null, HttpStatus.NOT_FOUND);
        }

        draft.setTitle(updatedDraft.getTitle());
        draft.setDescription(updatedDraft.getDescription());
        draft.setIngredients(updatedDraft.getIngredients());
        draft.setSteps(updatedDraft.getSteps());
        draft.setAdditionalNotes(updatedDraft.getAdditionalNotes());
        draft.setUpdatedAt(LocalDateTime.now());

        return new ResponseEntity<DraftRecipe>(recipeService.updateDraftRecipe(draft), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDraftRecipe(@PathVariable String id) {
        DraftRecipe draft  = recipeService.getDraftRecipe(id);

        if (draft == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if (draft.getStatus() == DraftRecipeStatus.SUBMITTED) {
            return new ResponseEntity<>("Unable to delete draft.", HttpStatus.OK);
        }

        recipeService.deleteDraft(id);

        return new ResponseEntity<>("Draft deleted.", HttpStatus.OK);
    }

}
