package at.kuchel.service;

import at.kuchel.model.*;
import at.kuchel.repostitory.IngredientRepository;
import at.kuchel.repostitory.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Objects;

@Transactional
@Service("recipeService")
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public void createRecipe(Recipe recipe) {
        //TODO logic for validating the recipe comes <here>
        //TODO extract someday
        extractToControllerSomeDay(recipe);

        for (int i = 0; i < recipe.getInstructions().size(); i++) {
            Instruction instruction = recipe.getInstructions().get(i);
            instruction.setStep(String.valueOf(i + 1));
        }

        replaceIngredientIfExist(recipe);

        recipeRepository.save(recipe);
    }

    private void replaceIngredientIfExist(Recipe recipe) {

        for(RecipeIngredient recipeIngredient: recipe.getRecipeIngredients()){
            Ingredient existingIngredient = ingredientRepository.findByName(recipeIngredient.getIngredient().getName());

            if(Objects.nonNull(existingIngredient)){
                recipeIngredient.setIngredient(existingIngredient);
            }
        }
    }

    private void extractToControllerSomeDay(Recipe recipe){
        for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()) {
            recipeIngredient.setRecipe(recipe);
        }

        for (Instruction instruction : recipe.getInstructions()) {
            instruction.setRecipe(recipe);
        }
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findOne(id);
    }

    @Override
    public Recipe getRecipeByName(String name) {
        return recipeRepository.findByName(name);
    }

    @Override
    public List<Recipe> getRecipeByUser(User user) {
        throw new NotImplementedException();
    }
}
