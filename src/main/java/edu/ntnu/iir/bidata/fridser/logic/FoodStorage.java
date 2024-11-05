package edu.ntnu.iir.bidata.fridser.logic;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * can add and remove food in a list
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
        while ((index < this.ingredients.size())&&(foundSame == false)){
            Ingredient ingredient2 = this.ingredients.get(index);
            if (isSame(ingredient,ingredient2)){
                ingredient2.addAmount(ingredient.getAmount());
                foundSame = true;
            }
            index++;
        }
        if (foundSame == false){
            ingredients.add(ingredient);
        }
    }

    /**
     * returns the size of the ingredients list
     * @return int
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

    public void useIngredient(double amount){
        Iterator<Ingredient> it = this.ingredients.iterator();
        while((it.hasNext()) && (amount > 0)){
            Ingredient ingredient = it.next();
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

