package com.gusto.gustoapi.dtos;

import com.gusto.gustoapi.models.Recipe;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReadDraftRecipeDto {

    @Id
    private String id;

    private String authorId;

    private String title;

    private String description;

    private List<Recipe.Ingredient> ingredients;

    private List<Recipe.Step> steps;

    private String additionalNotes;

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

        private String filename;

        private String originalFilename;

        private LocalDateTime uploadedAt;
    }
}
