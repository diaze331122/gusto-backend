package com.gusto.gustoapi.dtos;

import com.gusto.gustoapi.models.Recipe;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.List;

@Data
public class UpdateDraftRecipeDto {

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
}
