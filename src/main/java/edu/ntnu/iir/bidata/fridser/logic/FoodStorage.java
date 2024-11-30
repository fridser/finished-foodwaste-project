package edu.ntnu.iir.bidata.fridser.logic;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


/**
 * can add and remove ingredients in a list
 * keeps track of how many different and the amount of food
 * v.14.10.2024
 * Eriksen F. E.
 */


public class FoodStorage {
  private ArrayList<Ingredient> ingredients;


  /**
   * Creates an instance of edu.ntnu.iir.bidata.fridser.logic.FoodStorage object.
   */
  public FoodStorage() {
    ingredients = new ArrayList<>();
  }

  /**
   * Adds an ingredient to the array list ingredients. If an item
   * with the same name, unit and expiry
   *
   * @param ingredient Ingredient to be added
   */
  public void addIngredient(Ingredient ingredient) {
    boolean foundSame = false;
    int index = 0;
    while ((index < this.ingredients.size()) && (!foundSame)) {
      Ingredient ingredient2 = getIngredient(index);
      if (ingredient.isSame(ingredient2)) {
        ingredient2.addAmount(ingredient.getAmount());
        ingredient2.setPrice((ingredient.getPrice() + ingredient2.getPrice()) / 2);
        foundSame = true;
      }
      index++;
    }
    if (!foundSame) {
      ingredients.add(ingredient);
    }
  }

  /**
   * Deletes the specified Ingredient from the list.
   *
   * @param ingredient Ingredient to be deleted
   */
  public void deleteIngredient(Ingredient ingredient) {
    Iterator<Ingredient> it = this.ingredients.iterator();
    boolean deleted = false;
    while (it.hasNext() && !deleted) {
      Ingredient ingredient1 = it.next();
      if (ingredient1.equals(ingredient)) {
        it.remove();
        deleted = true;
      }
    }
  }

  /**
   * Deletes the ingredient at the stated index. Returns true if this
   * was successful.
   *
   * @param i The index of the ingredient being removed.
   * @return boolean, true if successfully removed the ingredient.
   */
  public boolean deleteIngredientWithIndex(int i) {
    boolean success = false;
    if (i < (getNumberOfIngredients())) {
      this.ingredients.remove(i);
      success = true;
    }
    return success;
  }

  /**
   * Returns the size of the ingredients list.
   *
   * @return numberOfIngredients The amount of ingredient in the FoodStorage.
   */
  public int getNumberOfIngredients() {
    return this.ingredients.size();
  }

  /**
   * Returns the ingredient at a specified index.
   *
   * @param index The index of the ingredient
   * @return Ingredient The ingredient at the index
   */
  public Ingredient getIngredient(int index) {
    if ((index > (ingredients.size() - 1))) {
      throw new IllegalArgumentException("Invalid index");
    }
    return this.ingredients.get(index);
  }

  /**
   * Returns the first ingredient with  specified name. Meant to be used in Recipe
   *
   * @param name The name of the ingredient
   * @return foundIngredient The ingredient with the specified name
   */
  public Ingredient findIngredientByName(String name) {
    if ((name.isBlank()) || (name == null) || (name.isEmpty())) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    Ingredient foundIngredient = null;

    int index = 0;
    boolean ingredientNotFound = true;

    while ((index < this.ingredients.size()) && ingredientNotFound) {
      Ingredient ingredient = this.ingredients.get(index);


      if (ingredient.getIngredientName().equalsIgnoreCase(name)) {
        ingredientNotFound = false;
        foundIngredient = ingredient;
      }
      index++;
    }


    return foundIngredient;
  }

  /**
   * Uses a specified amount of ingredient of a specified name.
   * <ul>
   *     <li>If the amount subtracted is less than the amount of the ingredient,
   *     the amount is subtracted from the ingredient's amount, and the subtracted amount
   *     is set to zero</li>
   *     <li>If the amount is more than the amount of the first ingredient, the amount of the
   *      first ingredient is subtracted from the specified amount and the ingredietn is
   *      deleted, then the new amount subtracted is compared to the second object with the
   *      specified name</li>
   * </ul>
   *
   * @param ingredient The ingredient being used.
   */
  public boolean useIngredient(Ingredient ingredient) {
    boolean success = false;
    double amount = ingredient.getAmount();
    sortByDate();
    if (canUseIngredient(ingredient)) {
      Iterator<Ingredient> it = this.ingredients.iterator();
      while ((it.hasNext()) && (amount > 0)) {
        Ingredient ingredient1 = it.next();
        if (ingredient1.getIngredientName().equalsIgnoreCase(ingredient.getIngredientName())) {
          if (ingredient1.getAmount() <= amount) {
            amount -= ingredient1.getAmount();
            it.remove();
          } else {
            ingredient1.reduceAmount(amount);
            amount = 0;
          }
        }

      }
      success = true;
    }
    return success;

  }

  /**
   * Returns the amount of ingredients there are of one ingredient in FoodStorage,
   * using the name of the ingredient.
   *
   * @param ingredientName The name of the ingredient
   * @return amount The amount of the ingredient
   */
  public double getAmountOfIngredients(String ingredientName) {
    if ((ingredientName.isBlank()) || (ingredientName == null) || (ingredientName.isEmpty())) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    double amount = 0;
    Iterator<Ingredient> it = ingredients.iterator();
    while (it.hasNext()) {
      Ingredient ingredient = it.next();
      if (ingredient.getIngredientName().equalsIgnoreCase(ingredientName)) {
        amount += ingredient.getAmount();
      }
    }
    return amount;

  }

  /**
   * Returns the list of ingredients as an Iterator.
   *
   * @return it, list of ingredient in FoodStorage
   */
  public Iterator<Ingredient> getIngredientList() {
    Iterator<Ingredient> it = ingredients.iterator();
    return it;
  }

  /**
   * Sorts the ingredients by date.
   */
  public void sortByDate() {
    Collections.sort(ingredients, Comparator.comparing(Ingredient::getExpiryDate));
  }

  /**
   * Sorts the ingredients alphabetically.
   */
  public void sortAlphabetically() {
    Collections.sort(ingredients, Comparator.comparing(Ingredient::getIngredientName));
  }

  /**
   * Returns the expired ingredients as an iterator.
   *
   * @param currentDate The current date that the ingredients are compared to.
   * @return ei, The iterator containing the expired ingredients.
   */
  public Iterator<Ingredient> getExpiredIngredients(LocalDate currentDate) {
    Iterator<Ingredient> it = this.ingredients.iterator();
    ArrayList<Ingredient> expiredIngredients = new ArrayList<>();
    while (it.hasNext()) {
      Ingredient ingredient = it.next();
      if (ingredient.isExpired(currentDate)) {
        expiredIngredients.add(ingredient);
      }

    }
    Iterator<Ingredient> ei = expiredIngredients.iterator();
    return ei;
  }

  /**
   * Removes the ingredients that are expired.
   *
   * @param currentDate The date the ingredients are compared to.
   */
  public void removeExpiredIngredients(LocalDate currentDate) {
    this.ingredients.removeIf(n -> n.isExpired(currentDate));
  }

  /**
   * Checks if we have enough of an ingredient to use it.
   *
   * @param ingredient The ingredient that we want to use.
   * @return boolean, True if we have enough of the ingredient.
   */
  public boolean canUseIngredient(Ingredient ingredient) {
    boolean canUse = false;
    double amount = ingredient.getAmount();
    if (getAmountOfIngredients(ingredient.getIngredientName()) >= amount) {
      canUse = true;
    }
    return canUse;
  }

  /**
   * Checks if we have enough ingredients to make a recipe.
   *
   * @param ar The ArrayList holding the ingredients to use the recipe.
   * @return boolean, True if we have enough ingredients.
   */
  public boolean canUseRecipe(ArrayList<Ingredient> ar) {
    boolean canUse = true;
    for (Ingredient i : ar) {
      if (!canUseIngredient(i)) {
        canUse = false;
      }
    }
    return canUse;
  }

  /**
   * Uses the ingredients in a recipe.
   *
   * @param it The iterator holding the ingredients used in the recipe.
   * @return boolean, True if successfully used the recipe.
   */
  public boolean useRecipe(Iterator<Ingredient> it) {
    boolean success = false;
    ArrayList<Ingredient> ar = turnIteratorIntoArrayList(it);
    if (canUseRecipe(ar)) {
      for (Ingredient i : ar) {
        useIngredient(i);
      }
      success = true;
    }
    return success;
  }

  /**
   * Converts an iterator of ingredients into an arraylist. Necessary for
   * the useRecipe method to work.
   *
   * @param it The iterator getting converted into an arraylist.
   * @return ArrayList, The converted arraylist.
   */
  public ArrayList<Ingredient> turnIteratorIntoArrayList(Iterator<Ingredient> it) {
    ArrayList<Ingredient> al = new ArrayList<>();
    while (it.hasNext()) {
      al.add(it.next());
    }
    return al;
  }

  /**
   * Returns the cost of all the expired ingredients in the foodstorage.
   *
   * @param currentDate The date the ingredients are compared to.
   * @return cost, the cost of all the expired ingredients.
   */
  public double calculateCostOfExpiredIngredients(LocalDate currentDate) {
    Iterator<Ingredient> it = getExpiredIngredients(currentDate);
    double cost = 0;
    while (it.hasNext()) {
      cost += it.next().getPrice() * it.next().getAmount();
    }
    return cost;
  }


}

