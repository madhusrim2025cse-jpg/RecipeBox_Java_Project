package com.example.recipe_api.service;

import com.example.recipe_api.model.IngredientModel;
import com.example.recipe_api.model.RecipeModel;
import com.example.recipe_api.repo.RecipeRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public RecipeModel createRecipe(RecipeModel recipe) {

        if (recipe.getIngredients() != null) {

            for (IngredientModel ingredient : recipe.getIngredients()) {
                ingredient.setRecipe(recipe);
            }
        }

        return recipeRepository.save(recipe);
    }

    @Override
    public List<RecipeModel> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public RecipeModel getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public RecipeModel updateRecipe(Long id, RecipeModel recipe) {

        RecipeModel existingRecipe =
                recipeRepository.findById(id).orElse(null);

        if (existingRecipe != null) {

            existingRecipe.setName(recipe.getName());
            existingRecipe.setCuisine(recipe.getCuisine());
            existingRecipe.setTags(recipe.getTags());
            existingRecipe.setSteps(recipe.getSteps());
            existingRecipe.setPrepTime(recipe.getPrepTime());

            if (recipe.getIngredients() != null) {

                for (IngredientModel ingredient : recipe.getIngredients()) {
                    ingredient.setRecipe(existingRecipe);
                }

                existingRecipe.setIngredients(recipe.getIngredients());
            }

            return recipeRepository.save(existingRecipe);
        }

        return null;
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public List<RecipeModel> searchByName(String name) {
        return recipeRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<RecipeModel> searchByCuisine(String cuisine) {
        return recipeRepository.findByCuisineIgnoreCase(cuisine);
    }

    @Override
    public List<RecipeModel> searchByTag(String tag) {
        return recipeRepository.findByTagsContainingIgnoreCase(tag);
    }
    @Override
public RecipeModel markFavourite(Long id) {

    RecipeModel recipe =
            recipeRepository.findById(id).orElse(null);

    if (recipe != null) {

        recipe.setFavourite(!recipe.isFavourite());

        return recipeRepository.save(recipe);
    }

    return null;
}
}