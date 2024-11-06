package edu.ntnu.iir.bidata.fridser.logic;

//TODO: method to see if there is enough ingredients in FoodStorage to make the recipe
//TODO: method to use the ingredients to make a new recipe.

import edu.ntnu.iir.bidata.fridser.data.Ingredient;

import java.util.Iterator;


public class Recipe {
    private String recipeName;
    private String description;
    private int portions;
    private FoodStorage ingredientList;

    /**
     * Creates a new instance if Recipe.
     * @param recipeName
     * @param description
     */
    public Recipe(String recipeName, String description){
        setRecipeName(recipeName);
        setDescription(description);
        this.ingredientList = new FoodStorage();
    }

    /**
     * Changes the name of the recipe to the new name.
     *
     * @param newName
     */
    public void setRecipeName(String newName){
        if ((newName.isBlank())||(newName == null)) {
            throw new IllegalArgumentException("Recipe name cannot be empty");
        }
        this.recipeName = newName;
    }

    /**
     * Changes the description to the new description
     *
     * @param newDescription
     */
    public void setDescription(String newDescription){
        if ((newDescription.isBlank())||(newDescription == null)) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = newDescription;
    }

    /**
     * Checks if there are enough of each ingredient in the ingredientList in the
     * FoodStorage. Returns true if there is.
     *
     * @param foodStorage
     * @return enoughIngredients
     */
    public boolean canUseRecipe(FoodStorage foodStorage){
        boolean enoughIngredients = true;
        Iterator<Ingredient> it = ingredientList.getIngredientList();
        while ((it.hasNext())&&(enoughIngredients)){
            Ingredient ingredient = it.next();
            if (ingredient.getAmount() < foodStorage.getAmountOfIngredients(ingredient.getIngredientName())){
                enoughIngredients = false;
            }
        }
        return enoughIngredients;

    }



}
