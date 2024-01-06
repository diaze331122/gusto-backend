package com.gusto.gustoapi.repositories;

import com.gusto.gustoapi.models.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

    List<Recipe> findRecipesByAuthorId(String authorId);

}
