package edu.ntnu.iir.bidata.fridser.logic;

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
     *
     * @param recipeName
     * @param description
     */
    public Recipe(String recipeName, String description) {
        setRecipeName(recipeName);
        setDescription(description);
        this.ingredientList = new FoodStorage();
    }

    /**
     * Adds an ingredient to the recipe.
     *
     * @param ingredient The ingredient being added.
     */
    public void addIngredient(Ingredient ingredient) {
        this.ingredientList.addIngredient(ingredient);
    }

    /**
     * Changes the name of the recipe to the new name.
     *
     * @param newName The new name of the recipe
     */
    public void setRecipeName(String newName) {
        if ((newName.isBlank()) || (newName == null)) {
            throw new IllegalArgumentException("Recipe name cannot be empty");
        }
        this.recipeName = newName;
    }

    /**
     * Returns the name of the recipe.
     *
     * @return recipeName, The name of the recipe.
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * Changes the description to the new description
     *
     * @param newDescription The new description of the recipe
     */
    public void setDescription(String newDescription) {
        if ((newDescription.isBlank()) || (newDescription == null)) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = newDescription;
    }

    /**
     * Returns the description of the recipe.
     *
     * @return description, The description of the recipe.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if there are enough of each ingredient in the ingredientList in the
     * FoodStorage. Returns true if there is.
     *
     * @param foodStorage The foodstorage containing the ingredients
     *                    to use the recipe
     * @return enoughIngredients Boolean true if there is enough ingredients
     * to make the recipe, false if there isn't.
     */
    public boolean canUseRecipe(FoodStorage foodStorage) {
        boolean enoughIngredients = true;
        Iterator<Ingredient> it = ingredientList.getIngredientList();
        while ((it.hasNext()) && (enoughIngredients)){
            Ingredient ingredient = it.next();
            if (ingredient.getAmount() >
                    foodStorage.getAmountOfIngredients(ingredient.getIngredientName())) {
                enoughIngredients = false;
            }
        }
        return enoughIngredients;

    }

    /**
     * Returns the list of ingredients needed to make the recipe as
     * an iterator
     *
     * @return it, The iterator of the list of ingredients.
     */
    public Iterator<Ingredient> getIngredientList() {
        Iterator<Ingredient> it = this.ingredientList.getIngredientList();
        return it;
    }



}
