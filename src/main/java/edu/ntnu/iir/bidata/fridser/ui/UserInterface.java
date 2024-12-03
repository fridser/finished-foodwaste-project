package edu.ntnu.iir.bidata.fridser.ui;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;
import edu.ntnu.iir.bidata.fridser.logic.FoodStorage;
import edu.ntnu.iir.bidata.fridser.logic.Recipe;
import edu.ntnu.iir.bidata.fridser.logic.RecipeBook;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Handles everything connected to the display the user interacts with.
 * <ul>
 *    <li>Prints out what the user sees as String</li>
 *    <li>takes input from the user</li>
 *    <li>does actions based on the input</li>
 *    <li> </li>
 *    <li> </li>
 *    <li></li>
 *    <li></li>
 *    <li></li>
 *    <li></li>
 *    <li></li>
 *    <li></li>
 *  </ul>
 */
public class UserInterface {
  private LocalDate currentDate; //The date that the expiry dates in the ingredients are compared to
  private FoodStorage fd;
  private RecipeBook rp;

  private static final int INFO = 1;
  private static final int FOODSTORAGE = 2;
  private static final int RECIPEBOOK = 3;
  private static final int SETTINGS = 4;
  private static final int QUIT = 5;
  private static final int PRINT = 1;
  private static final int ADD = 2;
  private static final int EDIT = 3;
  private static final int NAME = 1;
  private static final int AMOUNT = 2;
  private static final int UNIT = 3;

  /**
   * Creates an instance if the UserInterface.
   */
  public UserInterface() {
    init();
  }

  /**
   * Shows the relevant information about the start menu, and
   * processes the userinput by sending the user to the info menu, the
   * Foodstorage menu, the Recipebook menu, the settings menu or allows the
   * user to quit the program.
   */
  public void start() {

    addExampleRecipes();
    boolean finished = false;
    int i = 0;

    while (!finished) {
      showStartMenu();
      int userinput = getIntInput();

      switch (userinput) {

        case INFO:
          info();
          break;

        case FOODSTORAGE:
          foodStorage();
          break;

        case RECIPEBOOK:
          recipeBook();
          break;

        case SETTINGS:
          settings();
          break;

        case QUIT:
          quitProgram();
          finished = true;
          break;

        default:
          if (i < 3) {
            System.out.println("Please enter a valid menu choice \n" +
                    " ");
          } else {
            System.out.println("Please be better. \n" +
                    " ");
          }
          i++;
      }
    }
  }

  /**
   * Adds two recipes with ingredients to the RecipeBook.
   */
  public void addExampleRecipes() {
    Recipe tinePancake = new Recipe("Tine Pancake", "Melt butter. " +
            "Mix flour and salt, then add half of the milk and stir until all the clumps " +
            "have disappeared. Add the melted butter, then add the rest of the milk and the eggs. " +
            "Let rest for 20 minutes, then fry them in a frying pan.");
    Ingredient butter = new Ingredient("Butter", 9, "ts");
    Ingredient flour = new Ingredient("Flour", 0.3, "Litres");
    Ingredient salt = new Ingredient("Salt", 0.5, "ts");
    Ingredient milk = new Ingredient("Milk", 0.6, "Litres");
    Ingredient eggs = new Ingredient("Egg", 3, "Stk");
    tinePancake.addIngredient(butter);
    tinePancake.addIngredient(flour);
    tinePancake.addIngredient(salt);
    tinePancake.addIngredient(milk);
    tinePancake.addIngredient(eggs);

    Recipe fruitSalad =new Recipe("Fruit Salad", "Cut all the fruits " +
            "up and add them to a bowl");
    Ingredient banana = new Ingredient("Banana", 2, "Stk");
    Ingredient grapes = new Ingredient("Grapes", 500, "Grams");
    Ingredient raspBerry = new Ingredient("Raspberry", 200, "Grams");
    Ingredient orange = new Ingredient("Orange", 1, "Stk");
    Ingredient apple = new Ingredient("Apple", 2, "Stk");
    fruitSalad.addIngredient(banana);
    fruitSalad.addIngredient(grapes);
    fruitSalad.addIngredient(raspBerry);
    fruitSalad.addIngredient(orange);
    fruitSalad.addIngredient(apple);

    rp.addRecipe(tinePancake);
    rp.addRecipe(fruitSalad);
  }

  /**
   * Initializes the UserInterface.
   */
  public void init() {
    currentDate = LocalDate.now();
    fd = new FoodStorage();
    rp = new RecipeBook();

  }

  /**
   * Scans inputs that is supposed to be an integer.
   * Returns an integer if the input is an integer, else prints
   * out a message and gives the user another chance to input correctly.
   *
   * @return int, the user input.
   */
  private int getIntInput() {
    Scanner input = new Scanner(System.in);
    int choice = -1;
    boolean finished = false;
    while (!finished) {
      if (input.hasNextInt()){
        choice = input.nextInt();
        finished = true;
      } else {
        System.out.println("Please enter a positive whole number. \n" +
                "idiot");
      }
      input.nextLine();
    }
    return choice;
  }

  /**
   * Scans inputs that is supposed to be a double.
   * Returns a double if the input is a double, else prints
   * out a message and gives the user another chance to input correctly.
   *
   * @return double, the user input.
   */
  private double getDoubleInput() {
    Scanner input = new Scanner(System.in);
    double choice = -1;
    boolean finished = false;
    while (!finished) {
      if (input.hasNextDouble()){
        choice = input.nextDouble();
        finished = true;
      } else {
        System.out.println("Please enter a number. ");
      }
      input.nextLine();
    }
    return choice;
  }

  /**
   * Scans inputs that is supposed to be a string.
   * Returns the user input as a String.
   *
   * @return String, the user input.
   */
  private String getStringInput() {
    Scanner input = new Scanner(System.in);
    String choice = null;
    boolean finished = false;
    while (!finished) {
      if (input.hasNextLine()){
        choice = input.nextLine();
        finished = true;
      } else {
        System.out.println("An unexpected error has occurred. Please be" +
                " better.");
      }
    }
    return choice;
  }

  /**
   * Shows the relevant information about the info menu, and processes
   * the users choice by sending them to the start menu.
   */
  public void info() {
    boolean finished = false;
    int i = 0;

    while (!finished) {
      showInfo();
      int userinput = getIntInput();

      switch (userinput) {

        case 1:
          finished = true;
          break;

        default:
          if (i < 3) {
            System.out.println("Please enter a valid menu choice \n" +
                    " ");
          } else {
            System.out.println("Please be better. \n" +
                    " ");
          }
          i++;

      }
    }
  }

  /**
   * Shows the relevant information about the info menu, and processes
   * the users choice by either initializing the process of changing the
   * current date or sending the user back to the start menu.
   */
  public void settings() {
    boolean finished = false;
    int i = 0;

    while (!finished) {
      showSettings();
      int userinput = getIntInput();

      switch (userinput) {

        case 1:
          changeCurrentDate();
          break;

        case 2:
          finished = true;
          break;

        default:
          if (i < 3) {
            System.out.println("Please enter a valid menu choice \n" +
                    " ");
          } else {
            System.out.println("Please stop being an idiot. \n" +
                    " ");
          }
          i++;
      }
    }
  }


  /**
   * Shows the relevant information about the info menu, and processes
   * the users choice by printing all the ingredients in the foodstorage,
   * adding an ingredient to the foodstorage, editing an ingredient, showing
   * all the expired ingredients, deleting all the expired ingredients or going
   * back to the start menu.
   */
  public void foodStorage() {
    boolean finished = false;
    int i = 0;

    while (!finished) {
      showFoodStorage();
      int userinput = getIntInput();

      switch (userinput) {

        case PRINT:
          if (fd.getNumberOfIngredients() > 0) {
            fd.sortAlphabetically();
            printAllIngredients(fd.getIngredientList());
          } else {
            System.out.println("You do not have any ingredients yet. \n" +
                    "Get a job. \n" +
                    " ");
          }
          break;

        case ADD:
          getConfirmation();
          break;

        case EDIT:
          if (fd.getNumberOfIngredients() > 0) {
            editIngredient();
          } else {
            System.out.println("You do not have any ingredients yet. \n" +
                    "Get a job. \n" +
                    " ");
          }
          break;

        case 4:
          if (fd.getNumberOfIngredients() > 0) {
            fd.sortAlphabetically();
            printAllIngredients(fd.getExpiredIngredients(currentDate));
          } else {
            System.out.println("You do not have any ingredients yet. \n" +
                    " Get a job. \n" +
                    " ");
          }
          break;

        case 5:
          deleteExpiredIngredients();
          break;

        case 6:
          finished = true;
          break;

        default:
          if (i < 3) {
            System.out.println("Please enter a valid menu choice \n" +
                    " ");
          } else {
            System.out.println("Please be better. \n" +
                    " ");
          }
          i++;
      }
    }
  }

  /**
   * Prints all the ingredients in the foodstorage with a number
   * attached, returns the int the user entered if it is less than the number
   * of ingredients in the foodstorage.
   *
   * @return int, the number attached to the ingredient the user wants to edit.
   */
  public int chooseIngredient() {
    boolean finished = false;
    int i = 0;

    while (!finished) {
      fd.sortAlphabetically();
      showChooseIngredient();
      i = getIntInput();
      if ((i <= fd.getNumberOfIngredients()) && (i != 0)) {
        finished = true;
      } else {
        System.out.println("Please enter one of the options " +
                "on the screen. \n" +
                " ");
      }
    }
    return i - 1;
  }


  /**
   * Uses the int from the chooseIngredient method to get the ingredient
   * from the FoodStorage using the index chosen by the user. Processes the user
   * input to edit any of the fields of the ingredient, delete the ingredient
   * or
   */
  public void editIngredient() {
    boolean finished = false;
    int index = 0;
    int i = chooseIngredient();
    Ingredient ingredient = null;
    try {
      ingredient = fd.getIngredient(i);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      finished = true;
    }

    while (!finished) {
      System.out.println("Chosen ingredient details: ");
      printIngredientDetails(ingredient);
      showEditIngredient();

      int userinput = getIntInput();

      switch (userinput) {

        case NAME:
          editIngredientName(ingredient);
          break;

        case AMOUNT:
          editIngredientAmount(ingredient);
          break;

        case UNIT:
          String unit = chooseunit();
          try {
            ingredient.setUnit(unit);
          } catch (IllegalArgumentException e) {
            System.out.println("Invalid unit. Please try again. \n" +
                    " ");
          }
          break;

        case 4:
          editIngredientPrice(ingredient);
          break;

        case 5:
          editIngredientExpirationDate(ingredient);
          break;

        case 6:
          boolean success = fd.deleteIngredientWithIndex(i);
          if (success) {
            System.out.println("Successfully deleted ingredient");
            finished = true;
          } else {
            System.out.println("Something went wrong. Please be better \n" +
                    " ");
          }
          break;

        case 7:
          finished = true;
          break;

        default:
          if (index < 3) {
            System.out.println("Please enter a valid menu choice \n" +
                    " ");
          } else {
            System.out.println("Please be better. \n" +
                    " ");
          }
          index++;

      }
    }
  }

  public void editIngredientExpirationDate(Ingredient ingredient) {
    System.out.println("Please enter the new year the ingredient expires: \n");
    int year = getIntInput();
    if ((year < 99) && (year > 69)) {
      year += 1900;
    } else if ((year >= 0) && (year < 70)) {
      year += 2000;
    }
    System.out.println("Please enter the new month the ingredient expires as a whole " +
            "number between 1 and 12: \n");
    int month = getIntInput();
    System.out.println("Please enter the new day of the month the ingredient expires" +
            "as a whole number: \n");
    int day = getIntInput();
    try {
      ingredient.setExpiryDate(year, month, day);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.out.println("Please try again");
    }
  }

  public void editIngredientName(Ingredient ingredient) {
    System.out.println("Please enter the new name of the ingredient");
    String name = getStringInput();
    try {
      ingredient.setIngredientName(name);
    } catch (IllegalArgumentException e) {
      System.out.println("Name was invalid. Please try again");
    }
  }

  public void editIngredientAmount(Ingredient ingredient) {
    System.out.println("Please enter the new amount of the ingredient");
    double amount = getDoubleInput();
    try {
      ingredient.setAmount(amount);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid amount. Please try again");
    }
  }

  public void editIngredientPrice(Ingredient ingredient) {
    System.out.println("Please enter the new price of the ingredient");
    double price = getDoubleInput();
    try {
      ingredient.setPrice(price);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid price. Please try again");
    }
  }


  public void getConfirmation() {
    boolean finished = false;

    while (!finished) {
      System.out.println("WARNING: You cannot exit the process of adding" +
              "an ingredient before you have finished all the steps. Are you" +
              "sure you want to proceed? \n" +
              "1. Yes, 2. No \n");
      ;
      int userinput = getIntInput();

      switch (userinput) {

        case 1:
          addIngredient();
          finished = true;
          break;

        case 2:
          finished = true;
          break;

        default:
          System.out.println("Please enter a valid menu choice");

      }
    }
  }

  public void addIngredient() {
    String name;
    double amount;
    double price;
    while (true) {
      System.out.println("Please enter the name of ingredient: \n");
      name = getStringInput();
      if ((name.isBlank()) || (name.isEmpty())) {
        System.out.println("Please try again.");
      } else {
        break;
      }
    }
    while (true) {
      System.out.println("Please enter the amount of the ingredient: \n");
      amount = getDoubleInput();
      if (amount <= 0) {
        System.out.println("Amount cannot be less than or equal to zero.");
      } else {
        break;
      }
    }
    String unit = chooseunit();
    while (true) {
      System.out.println("Please enter the price of the ingredient per unit: \n");
      price = getDoubleInput();
      if (price < 0) {
        System.out.println("Please suck a lemon.");
      } else {
        break;
      }
    }
    System.out.println("Please enter the year the ingredient expires: \n");
    int year = getIntInput();
    if ((year < 99) && (year > 69)) {
      year += 1900;
    } else if ((year > 0) && (year < 70)) {
      year += 2000;
    }
    System.out.println("Please enter the month the ingredient expires as a whole " +
            "number between 1 and 12: \n");
    int month = getIntInput();
    System.out.println("Please enter the day of the month the ingredient expires" +
            "as a whole number: \n");
    int day = getIntInput();

    try {
      Ingredient ingredient = new Ingredient(name, amount, unit, price,
              year, month, day);
      fd.addIngredient(ingredient);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.out.println("Go suck a lemon.");
    }
  }

  public String chooseunit() {
    boolean finished = false;
    String unit = "";

    while (!finished) {
      System.out.println("Please choose a unit: \n" +
              "1. Grams, 2. Litres, 3. Stk, 4. ts \n");
      ;
      int userinput = getIntInput();

      switch (userinput) {

        case 1:
          unit = "Grams";
          finished = true;
          break;

        case 2:
          unit = "Litres";
          finished = true;
          break;

        case 3:
          unit = "Stk";
          finished = true;
          break;

        case 4:
          unit = "ts";
          finished = true;
          break;

        default:
          System.out.println("Please enter a valid menu choice");

      }
    }
    return unit;
  }

  public void deleteExpiredIngredients() {
    System.out.println("Deleting expired ingredients ...");
    double cost = fd.calculateCostOfExpiredIngredients(currentDate);
    fd.removeExpiredIngredients(currentDate);
    System.out.println("Expired ingredients deleted! \n" +
            "Money wasted: " + cost);
  }

  public void changeCurrentDate() {
    System.out.println("Please enter the year you want: \n");
    int year = getIntInput();
    if ((year < 99) && (year > 69)) {
      year += 1900;
    } else if ((year > 0) && (year < 70)) {
      year += 2000;
    }
    System.out.println("Please enter the month you want as a number" +
            "between 1 and 12: \n");
    int month = getIntInput();
    System.out.println("Please enter the day of the month you want" +
            "a a whole number: \n");
    int day = getIntInput();

    try {
      currentDate = LocalDate.of(year, month, day);
    } catch (DateTimeException e) {
      System.out.println("Date entered is not valid. Press 1 to try" +
              "again.");
    }
  }


  public void recipeBook() {
    boolean finished = false;

    while (!finished) {
      showRecipeBook();
      int userinput = getIntInput();

      switch (userinput) {

        case PRINT:
          if (rp.getAmountOfRecipes() > 0) {
            printRecipes(rp.getRecipeIterator());
          } else {
            System.out.println("You do not have any recipes yet. \n" +
                    " ");
          }
          break;

        case ADD:
          confirmAddRecipe();
          break;

        case EDIT:
          if (rp.getAmountOfRecipes() > 0) {
            editRecipe();
          } else {
            System.out.println("You do not have any recipes yet. \n" +
                    " ");
          }
          break;

        case 4:
          printUsableRecipes();
          break;

        case 5:
          useRecipe();
          break;

        case 6:
          if (fd.getNumberOfIngredients() > 0) {
            System.out.println("Based on the expiration date of the ingredients" +
                    "you have in storage we will recommend you make:");
            printRecipeDetails(rp.recommendRecipe(currentDate, fd));
          }
          break;

        case 7:
          finished = true;
          break;

        default:
          System.out.println("Please enter a valid menu choice");

      }
    }
  }

  public String chooseRecipe() {
    boolean finished = false;
    String name = null;

    while (!finished) {
      printRecipes(rp.getRecipeIterator());
      System.out.println("Please write the name of the recipe you " +
              "want to use: \n");
      String userinput = getStringInput();
      if (rp.containsKey(userinput)) {
        name = userinput;
        finished = true;
      } else {
        System.out.println("Please write one of the recipes " +
                "on the screen.");
      }
    }
    return name;

  }

  public void editRecipe() {
    boolean finished = false;
    String name = chooseRecipe();
    Recipe recipe = rp.getRecipe(name);

    while (!finished) {
      System.out.println("Chosen recipe details: ");
      printRecipeDetails(recipe);
      showEditRecipe();

      int userinput = getIntInput();

      switch (userinput) {

        case NAME:
          editRecipeName(name);
          break;

        case 2:
          editRecipeInstruction(name);
          break;

        case 3:
          addIngredientsToRecipe(name);
          break;

        case 4:
          editIngredientInRecipe(name);
          break;

        case 5:
          try {
            rp.deleteRecipe(name);
          } catch (IllegalArgumentException e) {
            System.out.println("An unexpected error has occurred.");
          }
          finished = true;
          break;

        case 6:
          finished = true;
          break;

        default:
          System.out.println("Please enter a valid menu choice");

      }
    }
  }

  public void editIngredientInRecipe(String recipeName) {
    boolean finished = false;
    String name = chooseIngredientInRecipe(recipeName);
    Ingredient ingredient = rp.getRecipe(recipeName).getIngredient(name);

    while (!finished) {
      System.out.println("Chosen ingredient details: ");
      printIngredientInRecipeDetails(ingredient);
      showEditIngredientInRecipe();

      int userinput = getIntInput();

      switch (userinput) {

        case NAME:
          editIngredientInRecipeName(recipeName, name);
          break;

        case AMOUNT:
          editIngredientInRecipeAmount(recipeName, name);
          break;

        case UNIT:
          editIngredientInRecipeUnit(recipeName, name);
          break;

        case 4:
          try {
            rp.getRecipe(recipeName).deleteIngredient(name);
          } catch (IllegalArgumentException e) {
            System.out.println("An unexpected error has occurred.");
          }
          finished = true;
          break;

        case 5:
          finished = true;
          break;

        default:
          System.out.println("Please enter a valid menu choice");

      }
    }
  }

  public void editIngredientInRecipeName(String recipeName, String ingredientName) {
    System.out.println("Please enter the new name of the ingredient: \n");
    String name = getStringInput();

    try {
      rp.getRecipe(recipeName).getIngredient(ingredientName).setIngredientName(name);
    } catch (IllegalArgumentException e) {
      System.out.println("The name entered was invalid. Please try" +
              " again.");
    }
  }

  public void editIngredientInRecipeAmount(String recipeName, String ingredientName) {
    System.out.println("Please enter the new amount of the ingredient: \n");
    double amount = getDoubleInput();

    try {
      rp.getRecipe(recipeName).getIngredient(ingredientName).setAmount(amount);
    } catch (IllegalArgumentException e) {
      System.out.println("The amount entered was invalid. Please try" +
              " again.");
    }
  }

  public void editIngredientInRecipeUnit(String recipeName, String ingredientName) {
    String unit = chooseunit();
    try {
      rp.getRecipe(recipeName).getIngredient(ingredientName).setUnit(unit);
    } catch (IllegalArgumentException e) {
      System.out.println("An unexpected error has occurred. Please try" +
              "again.");
    }
  }

  public String chooseIngredientInRecipe(String recipeName) {
    boolean finished = false;
    String name = null;

    while (!finished) {
      printAllIngredientsInRecipe(rp.getRecipe(recipeName).getIngredientIterator());
      System.out.println("Please write the name of the ingredient you " +
              "want to edit: \n");
      String userinput = getStringInput();
      if (rp.getRecipe(recipeName).containsKey(userinput)) {
        name = userinput;
        finished = true;
      } else {
        System.out.println("Please write one of the ingredients " +
                "on the screen.");
      }
    }
    return name;
  }

  public void editRecipeName(String recipeName) {
    System.out.println("Please enter the new name of the recipe: \n");
    String name = getStringInput();

    try {
      rp.getRecipe(recipeName).setRecipeName(name);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.out.println("Please try" +
              " again.");
    }
  }

  public void editRecipeInstruction(String recipeName) {
    System.out.println("Please enter the new description of the recipe: \n");
    String description = getStringInput();

    try {
      rp.getRecipe(recipeName).setInstruction(description);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.out.println("Please try" +
              " again.");
    }
  }

  public void printUsableRecipes() {
    System.out.println("You are able to make these recipes with " +
            "the ingredients you have in storage:");
    printRecipes(rp.getPossibleRecipes(fd));
  }

  public void useRecipe() {
    printUsableRecipes();
    System.out.println("Please write the name of the recipe you " +
            "want to use: \n");
    String name = getStringInput();
    try {
      boolean success = fd.useRecipe(rp.getRecipe(name).getIngredientIterator());
      if (success) {
        System.out.println("Recipe was used successfully!");
      } else {
        System.out.println("You do not have enough ingredients in your" +
                " FoodStorage to make this recipe. \n" +
                " These are the ingredients you lack: ");
        printAllIngredientsInRecipe(rp.getRecipe(name).getLackingIngredients(fd));
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.out.println("Suck a lemon.");
    }
  }

  public void confirmAddRecipe() {
    boolean finished = false;

    while (!finished) {
      System.out.println("WARNING: You cannot exit the process of adding" +
              " a recipe before you have finished all the steps. Are you" +
              " sure you want to proceed? \n" +
              "1. Yes, 2. No \n");
      ;
      int userinput = getIntInput();

      switch (userinput) {

        case 1:
          addRecipe();
          finished = true;
          break;

        case 2:
          finished = true;
          break;

        default:
          System.out.println("Please enter a valid menu choice");

      }
    }
  }

  public void addRecipe() {
    System.out.println("Please enter the name of the recipe: \n");
    String name = getStringInput();
    System.out.println("Please enter the instruction on how to make" +
            " the recipe: \n");
    String instruction = getStringInput();


    try {
      Recipe recipe = new Recipe(name, instruction);
      rp.addRecipe(recipe);
    } catch (IllegalArgumentException e) {
      System.out.println("One of the options entered was invalid. Please try" +
              " again.");
    }
    addIngredientsToRecipe(name);
  }

  public void addIngredientsToRecipe(String recipeName) {
    boolean finished = false;

    while (!finished) {
      System.out.println("Current recipe details: ");
      printRecipeDetails(rp.getRecipe(recipeName));
      System.out.println("Do you want to add another ingredient to " +
              "the recipe? \n" +
              "1. Yes, 2. No \n");
      ;
      int userinput = getIntInput();

      switch (userinput) {

        case 1:
          addRecipeIngredient(recipeName);
          break;

        case 2:
          finished = true;
          break;

        default:
          System.out.println("Please enter a valid menu choice");

      }
    }
  }

  public void addRecipeIngredient(String recipeName) {
    String name;
    double amount;
    while (true) {
      System.out.println("Please enter the name of ingredient: \n");
      name = getStringInput();
      if (name.isBlank() || name.isEmpty()) {
        System.out.println("Please try again");
      } else {
        break;
      }
    }
    while (true) {
      System.out.println("Please enter the amount of the ingredient: \n");
      amount = getDoubleInput();
      if (amount <= 0) {
        System.out.println("The amount cannot be less than or equal to zero.");
      } else {
        break;
      }
    }
    String unit = chooseunit();


    try {
      Ingredient ingredient = new Ingredient(name, amount, unit);
      rp.getRecipe(recipeName).addIngredient(ingredient);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }


  /**
   * Prints the information about the ingredient
   *
   * @param ingredient The ingredient for whom the details get printed
   */
  public void printIngredientDetails(Ingredient ingredient) {
    System.out.println("Name: " + ingredient.getIngredientName());
    System.out.println(" Amount: " + ingredient.getAmount() + " " + ingredient.getUnit());
    System.out.println(" Price: " + ingredient.getPrice() + " per " + ingredient.getUnit());
    System.out.println(" Expiration date: " + ingredient.getExpiryDate().toString());
    System.out.println(" ");
  }

  /**
   * Prints the name, amount and unit of the ingredient of the recipe.
   *
   * @param ingredient The ingredient in the recipe
   *                   whose details are being printed
   */
  public void printIngredientInRecipeDetails(Ingredient ingredient) {
    System.out.println("Name: " + ingredient.getIngredientName() +
            " Amount: " + ingredient.getAmount() + " " + ingredient.getUnit());
  }

  /**
   * Prints the details of each ingredient in an iterator.
   *
   * @param it The iterator containing the ingredients getting their details printed
   */
  public void printAllIngredients(Iterator<Ingredient> it) {
    while (it.hasNext()) {
      printIngredientDetails(it.next());
    }
    System.out.println(" ");
  }

  /**
   * Prints all the ingredients in the FoodStorage with a number
   * that the user selects to select the ingredient they want to edit.
   *
   * @param it The iterator containing all the ingredients in the recipe.
   */
  public void printIngredientsForEdit(Iterator<Ingredient> it) {
    int index = 1;
    while (it.hasNext()) {
      Ingredient i = it.next();
      System.out.println(index + ". " + i.getIngredientName() + " " +
              i.getAmount() + " " + i.getUnit() + " " + i.getPrice() +
              " per " + i.getUnit() + " " + i.getExpiryDate().toString());
      index ++;
    }
    System.out.println(" ");
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
    System.out.println(" ");
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
    System.out.println(" ");
  }

  /**
   * Prints the details of all the recipes in a RecipeBook.
   *
   * @param it The iterator containing all the recipes.
   */
  public void printRecipes(Iterator<Recipe> it) {
    while (it.hasNext()) {
      printRecipeDetails(it.next());
    }
    System.out.println(" ");
  }


  /**
   * Prints out the start menu.
   */
  public void showStartMenu() {
    System.out.println("-------------START MENU----------\n" +
            "Hello User!\n");
    System.out.println("Welcome to the MealPlanner app.\n");
    System.out.println("1. Info, 2. Go to FoodStorage, 3. Go to RecipeBook" +
            " 4. Settings, 5. Quit \n" +
            "------------------------------");
  }

  /**
   * Prints out information to the user about the program.
   */
  public void showInfo() {
    System.out.println("We are an app designed to help you manage \n " +
            "your groceries. There are many functions for you to explore.\n " +
            "Our goal with this app is to reduce food waste.");
    System.out.println("FoodStorage");
    System.out.println("Add your groceries to keep track of them. \n " +
            "Check if a grocery is about to go out of date, or if it’s \n" +
            "already expired. ");
    System.out.println("RecipeBook");
    System.out.println("In addition to the recipes already pre-installed \n" +
            "you can add your favourite recipes to the RecipeBook. \n" +
            "This feature will tell which recipes you can make based on \n" +
            "what groceries you have, and can even recommend to you a \n" +
            "recipe based on which groceries are about to expire. ");
    System.out.println("1. Back \n");

  }

  /**
   * Prints out the menu for changing the settings.
   */
  public void showSettings() {
    System.out.println("The current date is:" + currentDate);
    System.out.println("1. Change date, 2. Back\n");
  }

  /**
   * Prints the message to the user when they quit the program.
   */
  public void quitProgram() {
    System.out.println("Don't let the door hit you on the way out!");
  }


  /**
   * Prints out the FoodStorgage menu.
   */
  public void showFoodStorage() {
    System.out.println("--------FOODSTORAGE MENU---------\n" +
            "Welcome to the FoodStorage!\n");
    System.out.println("1. Print ingredients \n" +
            "2. Add ingredient \n" +
            "3. Edit ingredient \n" +
            "4. Get expired ingredients \n" +
            "5. Delete expired ingredients \n" +
            "6. Back\n" +
            "------------------------------ \n" +
            " ");
  }


  /**
   * Prints out the RecipeBook menu.
   */
  public void showRecipeBook() {
    System.out.println("-------RECIPEBOOK MENU----------\n" +
            "Welcome to the RecipeBook! \n" +
            "1. Print recipes \n" +
            "2. Add recipe \n" +
            "3. Edit recipe \n" +
            "4. Get possible recipes \n" +
            "5. Use recipe \n" +
            "6. Recommend recipe \n" +
            "7. Back \n" +
            " ------------------------\n" +
            " ");
  }

  /**
   * Prints out the information used for choosing which
   * ingredient in the foodstorage the user wants to edit.
   */
  public void showChooseIngredient() {
    System.out.println("Please choose the ingredient you want to edit:");
    printIngredientsForEdit(fd.getIngredientList());
    System.out.println(" \n" +
            " ");
  }

  /**
   * Prints out information for editing an ingredient in the foodstorage.
   */
  public void showEditIngredient() {
    System.out.println("How do you want to edit the ingredient?\n" +
            "1. Edit name \n" +
            "2. Edit amount \n" +
            "3. Edit unit \n" +
            "4. Edit price \n" +
            "5. Edit expiration date \n" +
            "6. Delete ingredient \n" +
            "7. DONE \n" +
            " \n" +
            "");
  }

  /**
   * Prints out information bout editing a recipe.
   */
  public void showEditRecipe() {
    System.out.println("How do you want to edit the recipe? \n" +
            "1. Edit name \n" +
            "2. Edit instruction \n" +
            "3. Add ingredient \n" +
            "4. Edit ingredient \n" +
            "5. Delete Recipe \n" +
            "6. DONE \n" +
            " \n" +
            " \n" +
            "");
  }

  /**
   * Prints out the menu for editing ingredients in a recipe.
   */
  public void showEditIngredientInRecipe() {
    System.out.println("How do you want to edit the ingredient? \n" +
            "1. Edit Name \n" +
            "2. Edit amount \n" +
            "3. Edit unit \n" +
            "4. Delete ingredient \n" +
            "5. DONE \n" +
            "  \n" +
            " \n" +
            "");
  }


}
