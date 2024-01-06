package com.gusto.gustoapi.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "recipes")
public class Recipe {

    @Id
    private String id;

    private String authorId;

    private String title;

    private String description;

    private List<Ingredient> ingredients;

    private List<Step> steps;

    private String additionalNotes;

    private List<RecipeFile> files;

    private LocalDateTime createdAt;

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

        private LocalDateTime uploadedAt;
    }

}


