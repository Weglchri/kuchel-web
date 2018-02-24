package at.kuchel.service;

import at.kuchel.model.Recipe;

import java.util.List;


public interface RecipeService {

    List<Recipe> getAllRecipes();
    Recipe getRecipeById(Long id);
}