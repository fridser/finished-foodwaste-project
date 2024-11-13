package edu.ntnu.iir.bidata.fridser.logic;

import java.util.HashMap;

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
}
