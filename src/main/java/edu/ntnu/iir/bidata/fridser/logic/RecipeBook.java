package edu.ntnu.iir.bidata.fridser.logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Keeps track of all the different recipes.
 * <ul>
 *     <li>able to add and remove recipe</li>
 *     <li>able to find a recipe based on the recipe name</li>
 * </ul>
 */


public class RecipeBook {
    private HashMap<String, Recipe> recipeList;

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
        if(recipeList.containsKey(recipe.getRecipeName())) {
            throw new IllegalArgumentException("Cannot put two recipes with same" +
                    "name in the list.");
        }
        this.recipeList.put(recipe.getRecipeName(),recipe);
    }

    /**
     * Returns a recipe with the stated name.
     *
     * @param recipeName The name of the recipe
     * @return recipe, the recipe corresponding to the name.
     */
    public Recipe getRecipe(String recipeName) {
        if ((recipeName.isBlank()) || (recipeName == null)) {
            throw new IllegalArgumentException("The name of the recipe " +
                    "cannot be empty");
        }
        return this.recipeList.get(recipeName);
    }

    /**
     * Removes the recipe belonging to the name key specified.
     *
     * @param name The name of the recipe being removed.
     */
    public void deleteRecipe(String name) {
        this.recipeList.remove(name);
    }

    /**
     * Returns true if the recipeList contains a recipe with the stated name.
     *
     * @param key The name of the recipe that is being checked.
     * @return boolean, true if the key exists in the recipeList already.
     */
    public boolean containsKey(String key) {
        return this.recipeList.containsKey(key);
    }

    /**
     * Returns true if the recipeList contains the stated recipe already.
     *
     * @param recipe The recipe that is being checked.
     * @return boolean, true if the recipe exists in the recipeList already.
     */
    public boolean containsRecipe(Recipe recipe) {
        return this.recipeList.containsValue(recipe);
    }


    /**
     * Recommends a recipe based on which ingredients are about to expire.
     *
     * @param currentDate The date the ingredients are compared to.
     * @param fd The foodstorage the ingredients reside in.
     * @return recipe, the recipe who uses the most amount of Ingredients
     * that are about to expire.
     */
    public Recipe recommendRecipe(LocalDate currentDate, FoodStorage fd) {
        Recipe bestRecipe = null;
        Iterator<Recipe> it = this.recipeList.values().iterator();
        while (it.hasNext()) {
            Recipe currentRecipe = it.next();
            if (bestRecipe == null) {
                bestRecipe = currentRecipe;
            }
            if (currentRecipe.getDireValue(currentDate, fd) >
                    bestRecipe.getDireValue(currentDate, fd)) {
                bestRecipe = currentRecipe;
            } else if (currentRecipe.getDireValue(currentDate, fd) ==
                    bestRecipe.getDireValue(currentDate, fd)) {
                if (currentRecipe.getUrgentValue(currentDate, fd) >
                        bestRecipe.getUrgentValue(currentDate, fd)) {
                    bestRecipe = currentRecipe;
                }
            }
        }
        return bestRecipe;
    }

    /**
     * Returns a list of recipes possible to make with the ingredients in the
     * given FoodStorage, in the for of an iterator.
     *
     * @param fd The FoodStorage containing the ingredients used in the recipe.
     * @return pr, The iterator containing the possible recipes.
     */
    public Iterator getPossibleRecipes(FoodStorage fd) {
        Iterator<Recipe> it = this.recipeList.values().iterator();
        ArrayList<Recipe> possibleRecipes = new ArrayList<>();
        while (it.hasNext()) {
            Recipe recipe = it.next();
            if(recipe.canUseRecipe(fd)) {
                possibleRecipes.add(recipe);
            }
        }
        Iterator<Recipe> pr = possibleRecipes.iterator();
        return pr;
    }
}
