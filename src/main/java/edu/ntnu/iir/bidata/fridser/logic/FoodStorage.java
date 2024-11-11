package edu.ntnu.iir.bidata.fridser.logic;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.time.LocalDate;


//TODO: create sorting methods


/**
 * can add and remove ingredients in a list
 * keeps track of how many different and the amount of food
 * v.14.10.2024
 * Eriksen F. E.
 */


public class FoodStorage {
    private ArrayList<Ingredient> ingredients;


    /**
     * Creates an instance of edu.ntnu.iir.bidata.fridser.logic.FoodStorage object
     */
    public FoodStorage(){
        ingredients = new ArrayList<>();
    }

    /**
     * Adds an ingredient to the array list ingredients. If an item
     * with the same name, unit and expiry
     * @param ingredient
     */
    public void addIngredient(Ingredient ingredient){
        boolean foundSame = false;
        int index = 0;
        while ((index < this.ingredients.size())&&(!foundSame)){
            Ingredient ingredient2 = this.ingredients.get(index);
            if (isSame(ingredient,ingredient2)){
                ingredient2.addAmount(ingredient.getAmount());
                foundSame = true;
            }
            index++;
        }
        if (!foundSame){
            ingredients.add(ingredient);
        }
    }

    /**
     * returns the size of the ingredients list
     * @return numberOfIngredients
     */
    public int getNumberOfIngredients(){
        return this.ingredients.size();
    }

    /**
     * returns the ingredient at a specified index
     * @param index
     * @return edu.ntnu.iir.bidata.fridser.data.Ingredient
     */
    public Ingredient getIngredient(int index){
        return this.ingredients.get(index);
    }

    /**
     * Returns the index of a specified edu.ntnu.iir.bidata.fridser.data.Ingredient
     * @param ingredient
     * @return int
     */
    public int getIndexOfIngredient(Ingredient ingredient){
        return this.ingredients.indexOf(ingredient);
    }

    /**
     * returns ingredients with  specified name
     * @param name
     * @return
     */
    public Ingredient findIngredientByName(String name)
    {
        Ingredient foundIngredient = null;

        int index = 0;
        boolean ingredientNotFound = true;

        while ((index < this.ingredients.size()) && ingredientNotFound )
        {
            Ingredient ingredient = this.ingredients.get(index);


            if (ingredient.getIngredientName().equals(name))
            {
                ingredientNotFound = false;
                foundIngredient = ingredient;
            }
            index++;
        }


        return foundIngredient;
    }


    /**
     * Deletes all the ingredients with the given name.
     * @param name
     */
    public void deleteAllIngredientWithSameName(String name){
        Iterator<Ingredient> it = this.ingredients.iterator();
        while(it.hasNext()){
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
     * @param ingredient1
     * @param ingredient2
     * @return true
     * @return false
     */
    public boolean isSame(Ingredient ingredient1,Ingredient ingredient2){
        if ((ingredient1.getIngredientName().equals(ingredient2.getIngredientName()))&&(ingredient1.getExpiryDate().equals(ingredient2.getExpiryDate())) && (ingredient1.getUnit().equals(ingredient2.getUnit()))){
            return true;
        }
        else{
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
     * @param amount
     * @param ingredientName
     */
    public void useIngredient(double amount, String ingredientName){
        sortByDate2();
        if (getAmountOfIngredients(ingredientName)<amount){
            throw new IllegalArgumentException("You do not have enough of the ingredient");
        }
        Iterator<Ingredient> it = this.ingredients.iterator();
        while((it.hasNext()) && (amount > 0)){
            Ingredient ingredient = it.next();
            if (ingredient.getIngredientName() == ingredientName){
                if (ingredient.getAmount() < amount){
                    amount -= ingredient.getAmount();
                    it.remove();
                }
                else{
                    ingredient.reduceAmount(amount);
                    amount = 0;
                }
            }

        }

    }

    /**
     * Returns the amount of ingredients there are of one ingredient in FoodStorage,
     * using the name of the ingredient
     * @param ingredientName
     * @return amount
     */
    public double getAmountOfIngredients(String ingredientName){
        double amount = 0;
        Iterator<Ingredient> it = ingredients.iterator();
        while (it.hasNext()){
            if (it.next().getIngredientName().equals(ingredientName)){
                amount += it.next().getAmount();
            }
        }
        return amount;

    }

    /**
     * Returns the list of ingredients as an Iterator
     * @return it, list of ingredient in FoodStorage
     */
    public Iterator getIngredientList(){
        Iterator<Ingredient> it = ingredients.iterator();
        return it;
    }

    /**
     * Returns the length of the ingredients list.
     * @return int, size of ingredients
     */
    public int getLength(){
        return ingredients.size();
    }

    /**
     * Returns an arraylist sorted by date.
     *
     * I found a better way to do this five minutes after I wrote this,
     * but look! My brainchild! Isn't she beautiful?
     *
     * @return ArrayList<Ingredient>
     */
    public ArrayList<Ingredient> sortByDate(){
        ArrayList<Ingredient> sortedList = new ArrayList<>();
        int index = 0;
        for (Ingredient ingredient:ingredients){
            int indexOfSortedIngredient = 0;
            boolean sorted = false;
            while ((indexOfSortedIngredient < sortedList.size())&&(!sorted)){
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
     * Sorts the ingredients arraylist by date.
     */
    public void sortByDate2(){
        Collections.sort(ingredients, Comparator.comparing(Ingredient::getExpiryDate));
    }

    /**
     * Sorts the ingredients arrayList alphabetically.
     */
    public void sortAlphabetically(){
        Collections.sort(ingredients, new Comparator<Ingredient>() {
            public int compare(Ingredient i1, Ingredient i2) {
                return i1.getIngredientName().compareTo(i2.getIngredientName());
            }
        });

    }

    public void sortAlphabetically2(){
        Collections.sort(ingredients, Comparator.comparing(Ingredient::getIngredientName));
    }




}

