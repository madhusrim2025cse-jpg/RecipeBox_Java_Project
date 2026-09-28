package com.example.recipe_api.controller;

import com.example.recipe_api.model.ShoppingListModel;
import com.example.recipe_api.service.ShoppingListService;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shoppingList")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(
            ShoppingListService shoppingListService) {

        this.shoppingListService = shoppingListService;
    }

    @GetMapping("/generateShoppingList")
    public List<ShoppingListModel> generateShoppingList(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        LocalDate start =
                LocalDate.parse(startDate);

        LocalDate end =
                LocalDate.parse(endDate);

        return shoppingListService.generateShoppingList(
                start,
                end
        );
    }
}