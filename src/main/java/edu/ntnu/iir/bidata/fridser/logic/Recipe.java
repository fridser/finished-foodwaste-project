package edu.ntnu.iir.bidata.fridser.logic;

//TODO: method to use the ingredients to make a new recipe.

import edu.ntnu.iir.bidata.fridser.data.Ingredient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Recipe {
    private String recipeName;
    private String instruction;
    private HashMap<String, Ingredient> ingredientList;

    /**
     * Creates a new instance if Recipe.
     *
     * @param recipeName The name of the recipe.
     * @param instruction The instruction detailing how to cook the recipe.
     */
    public Recipe(String recipeName, String instruction) {
        setRecipeName(recipeName);
        setInstruction(instruction);
        this.ingredientList = new HashMap<>();
    }

    /**
     * Adds an ingredient to the recipe.
     *
     * @param ingredient The ingredient being added.
     */
    public void addIngredient(Ingredient ingredient) {
        this.ingredientList.put(ingredient.getIngredientName(), ingredient);
    }

    /**
     * Returns the ingredient with the stated name.
     *
     * @param ingredientName The name of the ingredient being
     *                       returned.
     * @return Ingredient, the ingredient corresponding to the stated
     * name.
     */
    public Ingredient getIngredient(String ingredientName) {
        if ((ingredientName.isBlank()) || (ingredientName == null) ||
                (ingredientName.isEmpty())) {
            throw new IllegalArgumentException("Cannot search for an" +
                    "empty name");
        }
        if (!ingredientList.containsKey(ingredientName)) {
            throw new IllegalArgumentException("Ingredient name does not exist.");
        }
        return this.ingredientList.get(ingredientName);
    }

    /**
     * Changes the name of the recipe to the new name.
     *
     * @param newName The new name of the recipe
     */
    public void setRecipeName(String newName) {
        if ((newName.isBlank()) || (newName == null) || (newName.isEmpty())) {
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
     * Changes the instruction to the new instruction
     *
     * @param newDescription The new instruction of the recipe
     */
    public void setInstruction(String newDescription) {
        if ((newDescription.isBlank()) || (newDescription == null) ||
                (newDescription.isEmpty())) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.instruction = newDescription;
    }

    /**
     * Returns the description of the recipe.
     *
     * @return description, The description of the recipe.
     */
    public String getInstruction() {
        return instruction;
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
        Iterator<Ingredient> it = getIngredientIterator();
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
     * Returns the ingredients and how much each of the ingredients are lacking
     * to be able to make the recipe.
     *
     * @param fd The FoodStorage containing the ingredients
     * @return it, the iterator containing the lacking ingredients.
     */
    public Iterator<Ingredient> getLackingIngredients(FoodStorage fd) {
        Iterator<Ingredient> it = getIngredientIterator();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        while (it.hasNext()) {
            Ingredient ingredient = it.next();
            if (ingredient.getAmount() >
                    fd.getAmountOfIngredients(ingredient.getIngredientName())) {
                ingredients.add(ingredient);
                ingredient.setAmount(ingredient.getAmount() -
                        fd.getAmountOfIngredients(ingredient.getIngredientName()));
            }
        }
        return ingredients.iterator();
    }

    /**
     * Returns the list of ingredients needed to make the recipe as
     * an iterator
     *
     * @return it, The iterator of the list of ingredients.
     */
    public Iterator<Ingredient> getIngredientIterator() {
        Iterator<Ingredient> it = this.ingredientList.values().iterator();
        return it;
    }

    /**
     * Returns an iterator containing the keys in the ingredientList.
     *
     * @return Iterator, the iterator containing the keys.
     */
    public Iterator<String> getKeyIterator() {
        Iterator<String> it = this.ingredientList.keySet().iterator();
        return it;
    }

    /**
     * Counts how many different ingredients used in the recipe who will
     * expire within a week. Ingredients with the same name will only
     * be counted once.
     *
     * @param currentDate The date the ingredient is compared to
     * @param fd The FoodStorage the ingredients reside in
     * @return int, how many different ingredients in the recipe who will
     * expire within a week.
     */
    public int getUrgentValue(LocalDate currentDate, FoodStorage fd) {
        int count = 0;
        fd.sortByDate();
        Iterator<String> it = getKeyIterator();
        while (it.hasNext()) {
            String name = it.next();
            if (fd.findIngredientByName(name).isUrgent(currentDate)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts how many different ingredients used in the recipe who will
     * expire within a day. Ingredients with the same name will only
     * be counted once.
     *
     * @param currentDate The date the ingredient is compared to
     * @param fd The FoodStorage the ingredients reside in
     * @return int, how many different ingredients in the recipe who will
     * expire within a day.
     */
    public int getDireValue(LocalDate currentDate, FoodStorage fd) {
        int count = 0;
        fd.sortByDate();
        Iterator<String> it = getKeyIterator();
        while (it.hasNext()) {
            String name = it.next();
            if (fd.findIngredientByName(name).isDire(currentDate)) {
                count++;
            }
        }
        return count;
    }



}
