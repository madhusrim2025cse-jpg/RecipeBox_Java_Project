package com.example.recipe_api.controller;

import com.example.recipe_api.model.RecipeModel;
import com.example.recipe_api.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/createRecipe")
    public RecipeModel createRecipe(@RequestBody RecipeModel recipe) {
        return recipeService.createRecipe(recipe);
    }

    @GetMapping("/getRecipes")
    public List<RecipeModel> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/getRecipe/{id}")
    public RecipeModel getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PutMapping("/updateRecipe/{id}")
    public RecipeModel updateRecipe(
            @PathVariable Long id,
            @RequestBody RecipeModel recipe) {

        return recipeService.updateRecipe(id, recipe);
    }

    @DeleteMapping("/deleteRecipe/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return "Recipe deleted successfully";
    }

    @GetMapping("/searchByName")
    public List<RecipeModel> searchByName(@RequestParam String name) {
        return recipeService.searchByName(name);
    }

    @GetMapping("/searchByCuisine")
    public List<RecipeModel> searchByCuisine(
            @RequestParam String cuisine) {

        return recipeService.searchByCuisine(cuisine);
    }

    @GetMapping("/searchByTag")
    public List<RecipeModel> searchByTag(
            @RequestParam String tag) {

        return recipeService.searchByTag(tag);
    }

    @PutMapping("/markFavourite/{id}")
    public RecipeModel markFavourite(@PathVariable Long id) {
        return recipeService.markFavourite(id);
    }
}