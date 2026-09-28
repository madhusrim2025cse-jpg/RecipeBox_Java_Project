
package com.example.recipe_api.service;

import com.example.recipe_api.model.IngredientModel;

import java.util.List;

public interface IngredientService {

    IngredientModel createIngredient(IngredientModel ingredient);

    List<IngredientModel> getAllIngredients();

    IngredientModel getIngredientById(Long id);

    IngredientModel updateIngredient(Long id, IngredientModel ingredient);

    void deleteIngredient(Long id);
}

