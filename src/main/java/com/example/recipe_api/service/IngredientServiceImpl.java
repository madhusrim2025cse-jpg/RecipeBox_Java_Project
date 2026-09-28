package com.example.recipe_api.service;

import com.example.recipe_api.model.IngredientModel;
import com.example.recipe_api.repo.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientModel createIngredient(IngredientModel ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<IngredientModel> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public IngredientModel getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    @Override
    public IngredientModel updateIngredient(Long id, IngredientModel ingredient) {

        IngredientModel existingIngredient =
                ingredientRepository.findById(id).orElse(null);

        if (existingIngredient != null) {

            existingIngredient.setIngredientName(
                    ingredient.getIngredientName());

            existingIngredient.setQuantity(
                    ingredient.getQuantity());

            existingIngredient.setUnit(
                    ingredient.getUnit());

            return ingredientRepository.save(existingIngredient);
        }

        return null;
    }

    @Override
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}

