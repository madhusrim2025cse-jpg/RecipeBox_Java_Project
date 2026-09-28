package com.example.recipe_api.controller;

import com.example.recipe_api.model.IngredientModel;
import com.example.recipe_api.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/createIngredient")
    public IngredientModel createIngredient(
            @RequestBody IngredientModel ingredient) {

        return ingredientService.createIngredient(ingredient);
    }

    @GetMapping("/getIngredients")
    public List<IngredientModel> getAllIngredients() {

        return ingredientService.getAllIngredients();
    }

    @GetMapping("/getIngredient/{id}")
    public IngredientModel getIngredientById(
            @PathVariable Long id) {

        return ingredientService.getIngredientById(id);
    }

    @PutMapping("/updateIngredient/{id}")
    public IngredientModel updateIngredient(
            @PathVariable Long id,
            @RequestBody IngredientModel ingredient) {

        return ingredientService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("/deleteIngredient/{id}")
    public String deleteIngredient(@PathVariable Long id) {

        ingredientService.deleteIngredient(id);

        return "Ingredient deleted successfully";
    }
}

