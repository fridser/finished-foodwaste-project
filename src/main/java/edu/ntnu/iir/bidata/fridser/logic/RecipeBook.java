package edu.ntnu.iir.bidata.fridser.logic;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Keeps track of all the different recipes.
 * <ul>
 *     <li>able to add and remove recipe</li>
 *     <li>able to find a recipe based on the recipe name</li>
 * </ul>
 */


public class RecipeBook {
    private HashMap<String,Recipe> recipeList;

    /**
     * Constructor for the RecipeBook class.
     */
    public RecipeBook() {
        this.recipeList = new HashMap<>();
    }

    /**
     * Adds a recipe to the recipebook.
     *
     * @param recipe The recipe being added.
     */
    public void addRecipe(Recipe recipe) {
        this.recipeList.put(recipe.getRecipeName(),recipe);
    }


    /**
     * Recommends a recipe based on which ingredients are about to expire.
     *
     * @param currentDate The date the ingredients are compared to.
     * @param fd The foodstorage the ingredients reside in.
     * @return recipe, the recipe who uses the most amount of Ingredients
     * that are about to expire.
     */
    public Recipe getRecommendedRecipe(LocalDate currentDate, FoodStorage fd) {
        Recipe bestRecipe = null;
        Iterator<Recipe> it = this.recipeList.values().iterator();
        while (it.hasNext()) {
            Recipe currentRecipe = it.next();
            if (bestRecipe == null) {
                bestRecipe = currentRecipe;
            }
            if (currentRecipe.getDire(currentDate, fd) > bestRecipe.getDire(currentDate, fd)) {
                bestRecipe = currentRecipe;
            } else if (currentRecipe.getDire(currentDate, fd) == bestRecipe.getDire(currentDate, fd)) {
                if (currentRecipe.getUrgent(currentDate, fd) > bestRecipe.getUrgent(currentDate, fd)) {
                    bestRecipe = currentRecipe;
                }
            }
        }
        return bestRecipe;
    }
}
