package com.example.recipe_api.repo;

import com.example.recipe_api.model.RecipeModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RecipeRepository extends JpaRepository<RecipeModel, Long> {


    List<RecipeModel> findByNameContainingIgnoreCase(String name);

    List<RecipeModel> findByCuisineIgnoreCase(String cuisine);

    List<RecipeModel> findByTagsContainingIgnoreCase(String tag);

}