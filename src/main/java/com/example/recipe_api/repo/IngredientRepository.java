
package com.example.recipe_api.repo;

import com.example.recipe_api.model.IngredientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<IngredientModel, Long> {
}

