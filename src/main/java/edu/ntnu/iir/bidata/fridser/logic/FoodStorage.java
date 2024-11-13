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
            if (isSame(ingredient, ingredient2)) {
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
    public boolean deleteIngredient(Ingredient ingredient) {
        Iterator<Ingredient> it = this.ingredients.iterator();
        boolean deleted = false;
        while (it.hasNext() && !deleted) {
            Ingredient ingredient1 = it.next();
            if (ingredient1.equals(ingredient)) {
                it.remove();
                deleted = true;
            }
        }
        return deleted;
    }

    /**
     * Returns the size of the ingredients list
     *
     * @return numberOfIngredients The amount of ingredient in the FoodStorage
     */
    public int getNumberOfIngredients() {
        return this.ingredients.size();
    }

    /**
     * Returns the ingredient at a specified index
     *
     * @param index The index of the ingredient
     * @return Ingredient The ingredient at the index
     */
    public Ingredient getIngredient(int index) {
        return this.ingredients.get(index);
    }

    /**
     * Returns ingredient with  specified name. Meant to be used in Recipe
     *
     * @param name The name of the ingredient
     * @return foundIngredient The ingredient with the specified name
     */
    public Ingredient findIngredientByName(String name)
    {
        if ((name.isBlank()) || (name == null)) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        Ingredient foundIngredient = null;

        int index = 0;
        boolean ingredientNotFound = true;

        while ((index < this.ingredients.size()) && ingredientNotFound) {
            Ingredient ingredient = this.ingredients.get(index);


            if (ingredient.getIngredientName().equals(name)) {
                ingredientNotFound = false;
                foundIngredient = ingredient;
            }
            index++;
        }


        return foundIngredient;
    }


    /**
     * Deletes all the ingredients with the given name.
     *
     * @param name The name of the ingredients to be deleted
     */
    public void deleteAllIngredientWithSameName(String name) {
        Iterator<Ingredient> it = this.ingredients.iterator();
        while (it.hasNext()) {
            Ingredient ingredient = it.next();
            if (ingredient.getIngredientName().equals(name)){
                it.remove();
            }
        }
    }

    /**
     * Checks if two ingredients have the same name, expiry date and unit.
     * If they do it returns true.
     *
     * @param ingredient1 First ingredient to be compared
     * @param ingredient2 Second ingredient to be compared
     * @return true If the ingredients have the same name and expiry date
     * @return false If the ingredients do not have the same name and expiry date
     */
    public boolean isSame(Ingredient ingredient1, Ingredient ingredient2) {
        if ((ingredient1.getIngredientName().equals(ingredient2.getIngredientName()))
                && (ingredient1.getExpiryDate().equals(ingredient2.getExpiryDate()))
                && (ingredient1.getUnit().equals(ingredient2.getUnit()))){
            return true;
        }
        else {
            return false;
        }
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
     * @param amount The amount of the ingredient to be used
     * @param ingredientName The name of the ingredient to be used
     */
    public boolean useIngredient(double amount, String ingredientName) {
        if ((amount < 0)) {
            throw new IllegalArgumentException("Used amount cannot be less than zero");
        }
        if ((amount < 0)) {
            throw new IllegalArgumentException("Used amount cannot be zero");
        }
        boolean success = false;
        sortByDate();
        if (getAmountOfIngredients(ingredientName) < amount) {
            success = false;
        }
        else {
            Iterator<Ingredient> it = this.ingredients.iterator();
            while ((it.hasNext()) && (amount > 0)) {
                Ingredient ingredient = it.next();
                if (ingredient.getIngredientName().equals(ingredientName)) {
                    if (ingredient.getAmount() < amount) {
                        amount -= ingredient.getAmount();
                        it.remove();
                    } else {
                        ingredient.reduceAmount(amount);
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
     * using the name of the ingredient
     *
     * @param ingredientName The name of the ingredient
     * @return amount The amount of the ingredient
     */
    public double getAmountOfIngredients(String ingredientName) {
        if ((ingredientName.isBlank()) || (ingredientName == null)) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        double amount = 0;
        Iterator<Ingredient> it = ingredients.iterator();
        while (it.hasNext()) {
            Ingredient ingredient = it.next();
            if (ingredient.getIngredientName().equals(ingredientName)) {
                amount += ingredient.getAmount();
            }
        }
        return amount;

    }

    /**
     * Returns the list of ingredients as an Iterator
     *
     * @return it, list of ingredient in FoodStorage
     */
    public Iterator getIngredientList() {
        Iterator<Ingredient> it = ingredients.iterator();
        return it;
    }


    /**
     * Returns an arraylist sorted by date.
     *
     * I found a better way to do this five minutes after I wrote this,
     * but look! My brainchild! Isn't she beautiful?
     *
     * @return ArrayList<Ingredient> An arraylist of te sorted ingredients
     */
    public ArrayList<Ingredient> sortByDateOld() {
        ArrayList<Ingredient> sortedList = new ArrayList<>();
        int index = 0;
        for (Ingredient ingredient : ingredients) {
            int indexOfSortedIngredient = 0;
            boolean sorted = false;
            while ((indexOfSortedIngredient < sortedList.size()) && (!sorted)) {
                Ingredient sortedIngredient = sortedList.get(indexOfSortedIngredient);
                if (sortedIngredient.getExpiryDate().isAfter(ingredient.getExpiryDate())) {
                    index = sortedList.indexOf(sortedIngredient);
                    sorted = true;
                }
                else {
                    indexOfSortedIngredient++;
                }

            }
            sortedList.add(index,ingredient);

        }
        return sortedList;

    }

    /**
     * Sorts the ingredients by date.
     */
    public void sortByDate() {
        Collections.sort(ingredients, Comparator.comparing(Ingredient::getExpiryDate));
    }

    /**
     * Sorts the ingredients alphabetically
     */
    public void sortAlphabetically() {
        Collections.sort(ingredients, Comparator.comparing(Ingredient::getIngredientName));
    }

    /**
     * Returns the expired ingredients as an iterator.
     *
     * @param currentDate The current date that the ingredients are compared to.
     * @return it, The iterator containing the expired ingredients.
     */
    public Iterator<Ingredient> getExpiredIngredients(LocalDate currentDate) {
        Iterator<Ingredient> it = this.ingredients.iterator();
        while (it.hasNext()) {
            if (!it.next().isExpired(currentDate)) {
                it.remove();
            }

        }
        return it;
    }

    /**
     * Removes the ingredients that are expired.
     *
     * @param currentDate The date the ingredients are compared to.
     */
    public void removeExpiredIngredients(LocalDate currentDate) {
        Iterator<Ingredient> it = this.ingredients.iterator();
        while (it.hasNext()) {
            Ingredient ingredient = it.next();
            if (ingredient.isExpired(currentDate)) {
                it.remove();
            }
        }

    }





    //TODO: make a method that checks if we have enough of a ingredient
    //TODO: make a method that checks if we have enough of a list of ingredients




}

