package com.gusto.gustoapi.services;

import com.gusto.gustoapi.enums.DraftRecipeStatus;
import com.gusto.gustoapi.models.DraftRecipe;
import com.gusto.gustoapi.models.Recipe;
import com.gusto.gustoapi.repositories.DraftRecipeRepository;
import com.gusto.gustoapi.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private DraftRecipeRepository draftRecipeRepository;

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe getRecipe(String id){
        return recipeRepository.findById(id).orElseThrow();
    }

    public List<Recipe> getRecipesByAuthorId(String authorId) {
        return recipeRepository.findRecipesByAuthorId(authorId);
    }

    public DraftRecipe createEmptyDraftRecipe(String authorId) {
        DraftRecipe newDraft = new DraftRecipe();

        newDraft.setAuthorId(authorId);
        newDraft.setTitle("Untitled Draft Recipe");
        newDraft.setDescription("No description.");
        newDraft.setIngredients(new ArrayList<>());
        newDraft.setSteps(new ArrayList<>());
        newDraft.setAdditionalNotes("No additional notes.");
        newDraft.setFiles(new ArrayList<>());
        newDraft.setStatus(DraftRecipeStatus.CREATED);
        newDraft.setPublishedRecipeId(null);
        newDraft.setCreatedAt(LocalDateTime.now());
        newDraft.setUpdatedAt(LocalDateTime.now());

        return draftRecipeRepository.save(newDraft);
    }

    public List<DraftRecipe> getDraftRecipesByAuthorIdAndStatus(String authorId, DraftRecipeStatus status) {
        return draftRecipeRepository.findDraftRecipesByAuthorIdAndStatus(authorId, status);
    }

    public DraftRecipe getDraftRecipe(String id) {
        return draftRecipeRepository.findById(id).orElseThrow();
    }

    public DraftRecipe updateDraftRecipe(DraftRecipe draft) {
        return draftRecipeRepository.save(draft);
    }

    public void deleteDraft(String id) {
        draftRecipeRepository.deleteById(id);
    }

    public Recipe updateRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

}
