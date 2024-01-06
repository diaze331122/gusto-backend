package com.gusto.gustoapi.models;

import com.gusto.gustoapi.enums.DraftRecipeStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "draft_recipes")
public class DraftRecipe {

    @Id
    private String id;

    private String authorId;

    private String title;

    private String description;

    private List<Recipe.Ingredient> ingredients;

    private List<Recipe.Step> steps;

    private String additionalNotes;

    private List<RecipeFile> files;

    private DraftRecipeStatus status;

    private String publishedRecipeId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Data
    public static class Ingredient {

        private Float quantity;

        private String measurement;

        private String name;
    }

    @Data
    public static class Step {
        String step;
    }

    @Data
    public static class RecipeFile {

        private String uploaderId;

        private String bucket;

        private String filename;

        private String originalFilename;

        private LocalDateTime uploadedAt;
    }
}
