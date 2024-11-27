package edu.ntnu.iir.bidata.fridser.ui;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;
import edu.ntnu.iir.bidata.fridser.logic.FoodStorage;
import edu.ntnu.iir.bidata.fridser.logic.Recipe;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

public class UserInterface {
    private LocalDate currentDate;
    private FoodStorage fd;

    private static int INFO = 1;
    private static int FOODSTORAGE = 2;
    private static int RECIPEBOOK = 3;
    private static int SETTINGS = 4;
    private static int QUIT = 5;

    public UserInterface() {
        init();
    }

    public void start() {
        boolean finished = false;

        while (!finished) {
            showStartMenu();
            int userinput = getInput();

            switch (userinput) {

                case INFO:
                    info();
                    break;

                case FOODSTORAGE:
                    foodStorageMenu();
                    break;

                case RECIPEBOOK:
                    recipeBookMenu();
                    break;

                case SETTINGS:
                    settings();

                case QUIT:
                    quitProgram();
                    finished = true;
                    break;

                default:
                    System.out.println("Please enter a valid menu choice");

            }


        }
    }

    public void init() {
        currentDate = LocalDate.now();
        fd = new FoodStorage();

    }

    private int getInput(){
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        input.nextLine();
        return choice;
    }

    public void info() {
        boolean finished = false;

        while (!finished) {
            showInfo();
            int userinput = getInput();

            switch (userinput) {

                case 1:
                    finished = true;
                    break;

                default:
                    System.out.println("Please enter a valid menu choice");

            }
        }
    }

    public void settings() {
        boolean finished = false;

        while (!finished) {
            showSettings();
            int userinput = getInput();

            switch (userinput) {

                case 1:
                    changeCurrentDate();
                    break;

                case 2:
                    finished = true;
                    break;

                default:
                    System.out.println("Please enter a valid menu choice");

            }
        }
    }

    public void changeCurrentDate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the year you want:");
        int year = input.nextInt();
        input.nextLine();
        System.out.println("Please enter the month you want as a number" +
                "between 1 and 12:");
        int month = input.nextInt();
        input.nextLine();
        System.out.println("Please enter the day of the month you want" +
                "a a whole number:");
        int day = input.nextInt();

        try {
            currentDate = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            System.out.println("Date entered is not valid. Press 1 to try" +
                    "again.");
        }
    }


    /**
     * Prints the information about the ingredient
     *
     * @param ingredient The ingredient for whom the details get printed
     */
    public void printIngredientDetails(Ingredient ingredient) {
        System.out.println("Name: " + ingredient.getIngredientName());
        System.out.println(" Amount: " + ingredient.getAmount() +" " + ingredient.getUnit());
        System.out.println(" Price: " + ingredient.getPrice() + " per " + ingredient.getUnit());
        System.out.println(" Expiration date: " + ingredient.getExpiryDate().toString());
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
        int index = 1;
        while (it.hasNext()) {
            System.out.println(index + ".");
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
        System.out.println("1. Back");

    }

    public void showSettings() {
        System.out.println("The current date is:" + currentDate);
        System.out.println("1. Change date, 2. Back");
    }

    public void quitProgram() {
        System.out.println("Don't let the door hit you on the way out!");
    }

    public void showFoodStorage() {
        System.out.println("Welcome to the FoodStorage!");
        System.out.println("");
    }



}
