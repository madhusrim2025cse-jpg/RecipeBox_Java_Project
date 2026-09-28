package com.example.recipe_api.service;

import com.example.recipe_api.model.IngredientModel;
import com.example.recipe_api.model.MealPlanModel;
import com.example.recipe_api.model.RecipeModel;
import com.example.recipe_api.model.ShoppingListModel;
import com.example.recipe_api.repo.MealPlanRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private final MealPlanRepository mealPlanRepository;

    public ShoppingListServiceImpl(
            MealPlanRepository mealPlanRepository) {

        this.mealPlanRepository = mealPlanRepository;
    }

    @Override
    public List<ShoppingListModel> generateShoppingList(
            LocalDate startDate,
            LocalDate endDate) {

        List<MealPlanModel> mealPlans =
                mealPlanRepository.findAll();

        Map<String, ShoppingListModel> shoppingMap =
                new LinkedHashMap<>();

        for (MealPlanModel mealPlan : mealPlans) {

            LocalDate mealDate =
                    mealPlan.getMealDate();

            if (mealDate == null) {
                continue;
            }

            if (mealDate.isBefore(startDate)
                    || mealDate.isAfter(endDate)) {
                continue;
            }

            RecipeModel recipe =
                    mealPlan.getRecipe();

            if (recipe == null) {
                continue;
            }

            List<IngredientModel> ingredients =
                    recipe.getIngredients();

            if (ingredients == null) {
                continue;
            }

            for (IngredientModel ingredient : ingredients) {

                String name =
                        ingredient.getIngredientName();

                String unit =
                        ingredient.getUnit();

                String key =
                        name.toLowerCase()
                                + "_"
                                + unit.toLowerCase();

                if (shoppingMap.containsKey(key)) {

                    ShoppingListModel existingItem =
                            shoppingMap.get(key);

                    existingItem.setQuantity(
                            existingItem.getQuantity()
                                    + ingredient.getQuantity()
                    );

                } else {

                    ShoppingListModel shoppingItem =
                            new ShoppingListModel(
                                    name,
                                    ingredient.getQuantity(),
                                    unit
                            );

                    shoppingMap.put(
                            key,
                            shoppingItem
                    );
                }
            }
        }

        return new ArrayList<>(
                shoppingMap.values()
        );
    }
}