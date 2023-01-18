package com.example.recipebook3datastreamsandfiles.service;

import com.example.recipebook3datastreamsandfiles.model.Ingredient;

public interface IngredientsService {

     Ingredient addIngredient(Ingredient ingredient);

     Ingredient getIngredientId(long id);

     Ingredient updateIngredient(long id, Ingredient ingredient);

     Ingredient remoweIngredient(long id);
}
