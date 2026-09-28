package com.example.recipe_api.service;

import com.example.recipe_api.model.MealPlanModel;

import java.time.LocalDate;
import java.util.List;

public interface MealPlanService {

    MealPlanModel createMealPlan(MealPlanModel mealPlan);

    List<MealPlanModel> getAllMealPlans();

    MealPlanModel getMealPlanById(Long id);

    MealPlanModel updateMealPlan(Long id, MealPlanModel mealPlan);

    void deleteMealPlan(Long id);

    List<MealPlanModel> getMealPlansByDate(LocalDate mealDate);
}