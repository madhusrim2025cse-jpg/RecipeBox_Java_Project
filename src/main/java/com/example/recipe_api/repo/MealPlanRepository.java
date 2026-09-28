package com.example.recipe_api.repo;

import com.example.recipe_api.model.MealPlanModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealPlanRepository extends JpaRepository<MealPlanModel, Long> {

    List<MealPlanModel> findByMealDate(LocalDate mealDate);
}