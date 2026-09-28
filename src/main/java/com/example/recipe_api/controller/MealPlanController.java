package com.example.recipe_api.controller;

import com.example.recipe_api.model.MealPlanModel;
import com.example.recipe_api.service.MealPlanService;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/mealPlans")
public class MealPlanController {

    private final MealPlanService mealPlanService;

    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    @PostMapping("/createMealPlan")
    public MealPlanModel createMealPlan(
            @RequestBody MealPlanModel mealPlan) {

        return mealPlanService.createMealPlan(mealPlan);
    }

    @GetMapping("/getMealPlans")
    public List<MealPlanModel> getAllMealPlans() {

        return mealPlanService.getAllMealPlans();
    }

    @GetMapping("/getMealPlan/{id}")
    public MealPlanModel getMealPlanById(
            @PathVariable Long id) {

        return mealPlanService.getMealPlanById(id);
    }

    @PutMapping("/updateMealPlan/{id}")
    public MealPlanModel updateMealPlan(
            @PathVariable Long id,
            @RequestBody MealPlanModel mealPlan) {

        return mealPlanService.updateMealPlan(id, mealPlan);
    }

    @DeleteMapping("/deleteMealPlan/{id}")
    public String deleteMealPlan(
            @PathVariable Long id) {

        mealPlanService.deleteMealPlan(id);

        return "Meal plan deleted successfully";
    }

    @GetMapping("/getMealPlansByDate")
    public List<MealPlanModel> getMealPlansByDate(
            @RequestParam String date) {

        LocalDate mealDate = LocalDate.parse(date);

        return mealPlanService.getMealPlansByDate(mealDate);
    }
}