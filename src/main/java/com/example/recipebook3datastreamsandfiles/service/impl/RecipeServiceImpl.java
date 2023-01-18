package com.example.recipebook3datastreamsandfiles.service.impl;

import com.example.recipebook3datastreamsandfiles.model.Recipe;
import com.example.recipebook3datastreamsandfiles.service.RecipeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final Map<Long, Recipe> recipes = new HashMap<>();
    private static long lastId = 0;

    private final Path path;
    private final ObjectMapper objectMapper;

    public RecipeServiceImpl(@Value("${applicatoin.file.recipe}") String path) {
        try {
            this.path = Paths.get(path);
            this.objectMapper = new ObjectMapper();
        } catch (InvalidPathException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostConstruct
    private void init() {
        readDataFromFile();
    }

    private void readDataFromFile() {
        try {
            byte[] file = Files.readAllBytes(path);
            Map<Long, Recipe> mapFromFile = objectMapper.readValue(file, new TypeReference<>() {
            });
            recipes.putAll(mapFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDataToFile() {
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(recipes);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Recipe addRecipe(Recipe recipe) {
        if (recipes.containsKey(recipe.getId())) {
            throw new RuntimeException("Такой рецепт уже добавлен");
        } else {
            recipes.put(lastId++, recipe);
            writeDataToFile();
        }
        return recipe;
    }


    public Recipe getRecipeId(long id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        } else {
            throw new RuntimeException("Рецепт не найден");
        }
    }

    public Collection<Recipe> getAllRecipes() {
        return recipes.values();
    }

    public Recipe updateRecipe(long id, Recipe recipe) {
        if (recipes.containsKey(id)) {
            recipes.put(id, recipe);
            writeDataToFile();
            return recipe;
        } else {
            throw new RuntimeException("Рецепт не найден");
        }
    }

    @Override
    public Recipe remoweRecipe(long id) {
        if (recipes.containsKey(id)) {
            Recipe recipe = recipes.remove(id);
            writeDataToFile();
            return recipe;
        }else {
            throw new RuntimeException("Рецепт не найден");
        }
    }
}
