package com.gusto.gustoapi.repositories;

import com.gusto.gustoapi.enums.DraftRecipeStatus;
import com.gusto.gustoapi.models.DraftRecipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DraftRecipeRepository extends MongoRepository<DraftRecipe, String> {

    List<DraftRecipe> findDraftRecipesByAuthorId(String authorId);

    List<DraftRecipe> findDraftRecipesByAuthorIdAndStatus(String authorId, DraftRecipeStatus status);
}
