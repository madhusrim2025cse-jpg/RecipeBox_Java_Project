package com.example.recipe_api.service;

import com.example.recipe_api.model.RecipeModel;

import java.util.List;

public interface RecipeService {

    RecipeModel createRecipe(RecipeModel recipe);

    List<RecipeModel> getAllRecipes();

    RecipeModel getRecipeById(Long id);

    RecipeModel updateRecipe(Long id, RecipeModel recipe);

    void deleteRecipe(Long id);

    List<RecipeModel> searchByName(String name);

    List<RecipeModel> searchByCuisine(String cuisine);

    List<RecipeModel> searchByTag(String tag);

    RecipeModel markFavourite(Long id);
}