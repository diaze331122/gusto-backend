package com.gusto.gustoapi.controllers;

import com.gusto.gustoapi.dtos.UpdateDraftRecipeDto;
import com.gusto.gustoapi.models.DraftRecipe;
import com.gusto.gustoapi.models.Recipe;
import com.gusto.gustoapi.services.RecipeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping(value = "")
    public ResponseEntity<List<Recipe>> getRecipes() {
        return null;
    }


}
