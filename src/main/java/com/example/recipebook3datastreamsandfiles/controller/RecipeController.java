package com.example.recipebook3datastreamsandfiles.controller;


import com.example.recipebook3datastreamsandfiles.model.Recipe;
import com.example.recipebook3datastreamsandfiles.service.impl.RecipeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @Operation(
            summary = "Показать все рецепты",
            description = "Выдаёт рецепты без ввода параметров"
    )
    public Collection<Recipe> getAllRecipe() {
        return this.recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Показать рецепт по идентификатору",
            description = "Выдаёт рецепт по указанному идентификатору"
    )
    public Recipe getRecipe(@PathVariable long id) {
        return this.recipeService.getRecipeId(id);
    }

    @PostMapping
    @Operation(
            summary = "Запись рецепта",
            description = "Создает рецепт по заданным параметрам"
    )
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(recipe);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование рецепта",
            description = "Редактирует уже созданный рецепт по указанному индетефикатору"
    )
    public Recipe updateRecipe(@PathVariable ("id") long id,@RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта",
            description = "Удаляет рецепт по указанному индетефикатору"
    )
    public Recipe remoweRecipe(@PathVariable ("id") long id) {
        return recipeService.remoweRecipe(id);
    }
}
