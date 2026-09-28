package com.example.recipe_api.service;

import com.example.recipe_api.model.MealPlanModel;
import com.example.recipe_api.model.RecipeModel;
import com.example.recipe_api.repo.MealPlanRepository;
import com.example.recipe_api.repo.RecipeRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MealPlanServiceImpl implements MealPlanService {

    private final MealPlanRepository mealPlanRepository;
    private final RecipeRepository recipeRepository;

    public MealPlanServiceImpl(
            MealPlanRepository mealPlanRepository,
            RecipeRepository recipeRepository) {

        this.mealPlanRepository = mealPlanRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public MealPlanModel createMealPlan(MealPlanModel mealPlan) {

        if (mealPlan.getRecipe() != null) {

            Long recipeId = mealPlan.getRecipe().getId();

            RecipeModel existingRecipe =
                    recipeRepository.findById(recipeId).orElse(null);

            mealPlan.setRecipe(existingRecipe);
        }

        return mealPlanRepository.save(mealPlan);
    }

    @Override
    public List<MealPlanModel> getAllMealPlans() {
        return mealPlanRepository.findAll();
    }

    @Override
    public MealPlanModel getMealPlanById(Long id) {
        return mealPlanRepository.findById(id).orElse(null);
    }

    @Override
    public MealPlanModel updateMealPlan(
            Long id,
            MealPlanModel mealPlan) {

        MealPlanModel existingMealPlan =
                mealPlanRepository.findById(id).orElse(null);

        if (existingMealPlan != null) {

            existingMealPlan.setMealDate(
                    mealPlan.getMealDate());

            existingMealPlan.setMealType(
                    mealPlan.getMealType());

            if (mealPlan.getRecipe() != null) {

                Long recipeId =
                        mealPlan.getRecipe().getId();

                RecipeModel existingRecipe =
                        recipeRepository.findById(recipeId)
                                .orElse(null);

                existingMealPlan.setRecipe(existingRecipe);
            }

            return mealPlanRepository.save(existingMealPlan);
        }

        return null;
    }

    @Override
    public void deleteMealPlan(Long id) {
        mealPlanRepository.deleteById(id);
    }

    @Override
    public List<MealPlanModel> getMealPlansByDate(
            LocalDate mealDate) {

        return mealPlanRepository.findByMealDate(mealDate);
    }
}