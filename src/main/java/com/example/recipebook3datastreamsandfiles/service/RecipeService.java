package com.example.recipebook3datastreamsandfiles.service;

import com.example.recipebook3datastreamsandfiles.model.Recipe;

import java.util.Collection;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipeId(long id);

    Collection<Recipe> getAllRecipes();

    Recipe updateRecipe(long id, Recipe recipe);

    Recipe remoweRecipe(long id);
}
