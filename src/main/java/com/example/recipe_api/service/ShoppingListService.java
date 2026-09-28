package com.example.recipe_api.service;

import com.example.recipe_api.model.ShoppingListModel;

import java.time.LocalDate;
import java.util.List;

public interface ShoppingListService {

    List<ShoppingListModel> generateShoppingList(
            LocalDate startDate,
            LocalDate endDate);
}