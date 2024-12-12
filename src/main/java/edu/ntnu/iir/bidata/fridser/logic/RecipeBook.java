package edu.ntnu.iir.bidata.fridser.logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Keeps track of all the different recipes.
 * <ul>
 *     <li>able to add and remove recipes</li>
 *     <li>able to find a recipe based on the recipe name</li>
 *     <li>able to return an iterator of all the recipes contained</li>
 *     <li>able to return an iterator of all the recipes that can be used given
 *     a FoodStorage</li>
 *     <li>able to recommend a recipe based on the urgent and dire values of the
 *     recipes contained</li>
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
    if (recipeList.containsKey(recipe.getRecipeName().toUpperCase())) {
      throw new IllegalArgumentException("Cannot put two recipes with same"
              + "name in the list.");
    }
    this.recipeList.put(recipe.getRecipeName().toUpperCase(), recipe);
  }

  /**
   * Returns a recipe with the stated name.
   *
   * @param recipeName The name of the recipe
   * @return recipe, the recipe corresponding to the name.
   */
  public Recipe getRecipe(String recipeName) {
    if (!containsKey(recipeName)) {
      throw new IllegalArgumentException("This recipe does not exist. Make sure"
              + " you have entered the name of the recipe correctly");
    }
    return this.recipeList.get(recipeName.toUpperCase());
  }

  /**
   * Removes the recipe belonging to the name key specified.
   *
   * @param name The name of the recipe being removed.
   */
  public void deleteRecipe(String name) {
    if (!containsKey(name)) {
      throw new IllegalArgumentException("This recipe does not exist. Make sure"
              + " you have entered the name of the recipe correctly");
    }
    this.recipeList.remove(name.toUpperCase());
  }

  /**
   * Returns an iterator containing all the recipes in the recipeBook.
   *
   * @return Iterator, the iterator containing the recipes.
   */
  public Iterator<Recipe> getRecipeIterator() {
    return this.recipeList.values().iterator();
  }

  /**
   * Returns true if the recipeList contains a recipe with the stated name.
   *
   * @param key The name of the recipe that is being checked.
   * @return boolean, true if the key exists in the recipeList already.
   */
  public boolean containsKey(String key) {
    return this.recipeList.containsKey(key.toUpperCase());
  }


  /**
   * Recommends a recipe based on which ingredients are about to expire.
   *
   * @param currentDate The date the ingredients are compared to.
   * @param foodStorage          The foodstorage the ingredients reside in.
   * @return recipe, the recipe who uses the most amount of Ingredients
   * that are about to expire.
   */
  public Recipe recommendRecipe(LocalDate currentDate, FoodStorage foodStorage) {
    Recipe bestRecipe = null;
    Iterator<Recipe> it = getPossibleRecipes(foodStorage);
    if (it.hasNext()) {
      while (it.hasNext()) {
        Recipe currentRecipe = it.next();
        if (bestRecipe == null) {
          bestRecipe = currentRecipe;
        }
        if (currentRecipe.getDireValue(currentDate, foodStorage)
                > bestRecipe.getDireValue(currentDate, foodStorage)) {
          bestRecipe = currentRecipe;
        } else if (currentRecipe.getDireValue(currentDate, foodStorage)
                == bestRecipe.getDireValue(currentDate, foodStorage)) {
          if (currentRecipe.getUrgentValue(currentDate, foodStorage)
                  > bestRecipe.getUrgentValue(currentDate, foodStorage)) {
            bestRecipe = currentRecipe;
          }
        }
      }
    } else {
      throw new IllegalArgumentException("There are no possible recipes to make");
    }
    return bestRecipe;
  }

  /**
   * Returns a list of recipes possible to make with the ingredients in the
   * given FoodStorage, in the for of an iterator.
   *
   * @param foodStorage The FoodStorage containing the ingredients used in the recipe.
   * @return pr, The iterator containing the possible recipes.
   */
  public Iterator<Recipe> getPossibleRecipes(FoodStorage foodStorage) {
    Iterator<Recipe> it = this.recipeList.values().iterator();
    ArrayList<Recipe> possibleRecipes = new ArrayList<>();
    while (it.hasNext()) {
      Recipe recipe = it.next();
      if (recipe.canUseRecipe(foodStorage)) {
        possibleRecipes.add(recipe);
      }
    }
    Iterator<Recipe> pr = possibleRecipes.iterator();
    return pr;
  }

  public int getAmountOfRecipes() {
    return this.recipeList.size();
  }
}
