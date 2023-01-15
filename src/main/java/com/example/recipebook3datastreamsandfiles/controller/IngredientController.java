package com.example.recipebook3datastreamsandfiles.controller;

import com.example.recipebook3datastreamsandfiles.model.Ingredient;
import com.example.recipebook3datastreamsandfiles.service.impl.IngredientsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientsServiceImpl ingredientsService;


    public IngredientController(IngredientsServiceImpl ingredientsService) {
        this.ingredientsService = ingredientsService;
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Показать ингриедиент по идентификатору",
            description = "Выдаёт ингриедиент по указанному идентификатору"
    )
    public Ingredient getIngredient(@PathVariable long id) {
        return this.ingredientsService.getIngredientId(id);
    }

    @PostMapping
    @Operation(
            summary = "Запись ингриедиента",
            description = "Создает ингриедиент по заданным параметрам"
    )
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientsService.addIngredient(ingredient);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование ингриедиента по указанному индетефикатору",
            description = "Редактирует уже созданный ингриедиент"
    )
    public Ingredient updateIngredient(@PathVariable ("id") long id, @RequestBody Ingredient ingredient) {
        return ingredientsService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингриедиента",
            description = "Удаляет ингриедиент по указанному индетефикатору"
    )
    public Ingredient remoweIngredient(@PathVariable ("id") long id) {
        return ingredientsService.remoweIngredient(id);
    }
}
