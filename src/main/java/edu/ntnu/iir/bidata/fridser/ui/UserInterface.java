package edu.ntnu.iir.bidata.fridser.ui;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;
import edu.ntnu.iir.bidata.fridser.logic.Recipe;

import java.util.Iterator;

public class UserInterface {

    public void start() {
        Ingredient ingredient = new Ingredient("Apple", 2, "Kg",
                12.9,2000,1,1);
        printIngredientDetails(ingredient);

    }

    public void init() {

    }

    /**
     * Prints the information about the ingredient
     *
     * @param ingredient The ingredient for whom the details get printed
     */
    public void printIngredientDetails(Ingredient ingredient) {
        System.out.println("Name: " + ingredient.getIngredientName());
        System.out.println("Amount: " + ingredient.getAmount() +" " + ingredient.getUnit());
        System.out.println("Price: " + ingredient.getPrice() + " per " + ingredient.getUnit());
        System.out.println("Expiration date: " + ingredient.getExpiryDate().toString());
    }

    /**
     * Prints the name, amount and unit of the ingredient of the recipe.
     *
     * @param ingredient The ingredient in the recipe
     *                   whose details are being printed
     */
    public void printIngredientInRecipeDetails(Ingredient ingredient) {
        System.out.println("Name: " + ingredient.getIngredientName()+
                "Amount: " + ingredient.getAmount() +" " + ingredient.getUnit());
    }

    /**
     * Prints the details of each ingredient in an iterator.
     *
     * @param it The iterator containing the ingredients getting their details printed
     */
    public void printAllIngredientDetails(Iterator<Ingredient> it) {
        while (it.hasNext()) {
            printIngredientDetails(it.next());
        }
    }

    /**
     * Prints the details of all the ingredients in the recipe.
     *
     * @param it The iterator containing all the ingredients in the recipe.
     */
    public void printAllIngredientsInRecipe(Iterator<Ingredient> it) {
        while (it.hasNext()) {
            printIngredientInRecipeDetails(it.next());
        }
    }

    /**
     * Prints the name, instruction and ingredients of a recipe.
     *
     * @param recipe The recipe whose details are being printed.
     */
    public void printRecipeDetails(Recipe recipe) {
        System.out.println(recipe.getRecipeName());
        System.out.println(recipe.getInstruction());
        printAllIngredientsInRecipe(recipe.getIngredientIterator());
    }

    /**
     * Prints the details of all the recipes in a RecipeBook.
     *
     * @param it The iterator containing all the recipes.
     */
    public void printAllRecipesInRecipeBook(Iterator<Recipe> it) {
        while (it.hasNext()) {
            printRecipeDetails(it.next());
        }
    }

    /**
     * Returns the total cost of all the ingredients in the iterator.
     *
     * @param it The iterator containing the ingredients.
     * @return cost, the total amount of money used to buy the ingredients.
     */
    public double calculateCost(Iterator<Ingredient> it) {
        double cost = 0;
        while (it.hasNext()) {
            cost += it.next().getPrice()*it.next().getAmount();
        }
        return cost;
    }

    public void showStartMenu() {
        System.out.println("Hello User!");
        System.out.println("Welcome to the MealPlanner app.");
        System.out.println("1. Info, 2. Go to FoodStorage, 3. Go to RecipeBook" +
                " 4. Settings, 5. Quit");
    }

    public void showInfo() {
        System.out.println("We are an app designed to help you manage\n " +
                "your groceries. There are many functions for you to explore.\n " +
                "Our goal with this app is to reduce food waste.");
        System.out.println("FoodStorage");
        System.out.println("Add your groceries to keep track of them.\n " +
                "Check if a grocery is about to go out of date, or if it’s \n" +
                "already expired. ");
        System.out.println("RecipeBook");
        System.out.println("In addition to the recipes already pre-installed \n" +
                "you can add your favourite recipes to the RecipeBook. \n" +
                "This feature will tell which recipes you can make based on \n" +
                "what groceries you have, and can even recommend to you a \n" +
                "recipe based on which groceries are about to expire. ");
        System.out.println("1. Back, 2. Quit");

    }

    public void showSettings() {
        System.out.println("The current date is:");
        System.out.println("1. Change date, 2. Back, 3. Quit");
    }

}
