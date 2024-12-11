package edu.ntnu.iir.bidata.fridser.logic;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecipeBookTest {

  RecipeBook rp;

  /**
   * Executes before each of the test methods.
   *
   * <p>Creates the instance of the recipe book to be used for each test. This way
   * we make sure that every single of the tests starts off with a newly created, empty
   * instance of RecipeBook.</p>
   */
  @BeforeEach
  void createInstanceOfRecipeBook() {
    this.rp = new RecipeBook();
  }

//-------------------------------POSITIVE TESTS-----------------------------------

  /**
   * Creates an instance of RecipeBook and adds valid recipes to it.
   * Checks that the get method returns the correct recipes.
   */
  @Test
  public void createRecipeBookWithMultipleRecipes() {

    Recipe recipe1 = new Recipe("Fruit Salad",
            "Cut fruits and add it to a bowl");
    Recipe recipe2 = new Recipe("Pasta with tomato sauce",
            "Boil water and cook pasta for 10 min. Drain and add tomato sauce");

    rp.addRecipe(recipe1);
    rp.addRecipe(recipe2);

    assertEquals(recipe1, rp.getRecipe("Fruit Salad"));
    assertEquals(recipe2, rp.getRecipe("Pasta with tomato sauce"));
  }

  /**
   * Deletes a recipe from the recipeList. Checks that the recipe is deleted.
   */
  @Test
  public void deleteRecipeFromRecipeBook() {

    Recipe recipe1 = new Recipe("Fruit Salad",
            "Cut fruits and add it to a bowl");
    Recipe recipe2 = new Recipe("Pasta with tomato sauce",
            "Boil water and cook pasta for 10 min. Drain and add tomato sauce");

    rp.addRecipe(recipe1);
    rp.addRecipe(recipe2);

    rp.deleteRecipe("Fruit Salad");

    assertFalse(rp.containsKey(recipe1.getRecipeName()));
  }

  /**
   * Creates two recipes and adds ingredients to them. Creates a foodstorage
   * with enough ingredients to only make one of them.
   * Checks that the getPossibleRecipes method only returns the recipe that
   * it is possible to make.
   */
  @Test
  public void getThePossibleRecipe() {

    Recipe recipe1 = new Recipe("Fruit Salad",
            "Cut fruits and add it to a bowl");
    Recipe recipe2 = new Recipe("Pasta with tomato sauce",
            "Boil water and cook pasta for 10 min. Drain and add tomato sauce");

    rp.addRecipe(recipe1);
    rp.addRecipe(recipe2);

    FoodStorage fd = new FoodStorage();

    Ingredient apple = new Ingredient("Apple", 2, "Stk");
    Ingredient orange = new Ingredient("Orange", 3, "Stk");
    Ingredient pasta = new Ingredient("Pasta", 100, "Grams");
    Ingredient tomatoSauce = new Ingredient("Tomato sauce", 0.2, "Litres");

    Ingredient apple1 = new Ingredient("Apple", 3, "Stk",
            12.9, 2023, 1, 3);
    Ingredient orange1 = new Ingredient("Orange", 4, "Stk",
            15.0, 2023, 1, 4);

    recipe1.addIngredient(apple);
    recipe1.addIngredient(orange);

    recipe2.addIngredient(pasta);
    recipe2.addIngredient(tomatoSauce);

    fd.addIngredient(apple1);
    fd.addIngredient(orange1);

    Iterator<Recipe> it = rp.getPossibleRecipes(fd);

    assertTrue(it.hasNext());
    assertEquals(recipe1, it.next());
    assertFalse(it.hasNext());
  }

  /**
   * Adds two recipes to the RecipeBook with different dire values based
   * on the ingredients in the FoodStorage and the current date.
   * Checks that the recipe returned by the recommendRecipe method is
   * the recipe with the highest dire value.
   */
  @Test
  public void recommendRecipeWithDifferentDireValues() {

    Recipe recipe1 = new Recipe("Fruit Salad",
            "Cut fruits and add it to a bowl");
    Recipe recipe2 = new Recipe("Pasta with tomato sauce",
            "Boil water and cook pasta for 10 min. Drain and add tomato sauce");

    rp.addRecipe(recipe1);
    rp.addRecipe(recipe2);

    FoodStorage fd = new FoodStorage();

    Ingredient apple = new Ingredient("Apple", 2, "Stk");
    Ingredient orange = new Ingredient("Orange", 3, "Stk");
    Ingredient pasta = new Ingredient("Pasta", 100, "Grams");
    Ingredient tomatoSauce = new Ingredient("Tomato sauce", 0.2, "Litres");

    Ingredient apple1 = new Ingredient("Apple", 3, "Stk",
            12.9, 2024, 1, 1);
    Ingredient orange1 = new Ingredient("Orange", 4, "Stk",
            15.0, 2024, 1, 4);
    Ingredient pasta1 = new Ingredient("Pasta", 100, "Stk",
            15.0, 2024, 1, 5);
    Ingredient tomatoSauce1 = new Ingredient("Tomato sauce", 0.5, "Litres", 15.2,
            2024, 1, 8);

    recipe1.addIngredient(apple);
    recipe1.addIngredient(orange);

    recipe2.addIngredient(pasta);
    recipe2.addIngredient(tomatoSauce);

    fd.addIngredient(apple1);
    fd.addIngredient(orange1);
    fd.addIngredient(pasta1);
    fd.addIngredient(tomatoSauce1);

    LocalDate currentDate = LocalDate.of(2024, 1, 1);

    Recipe recommendedRecipe = rp.recommendRecipe(currentDate, fd);
    assertEquals(recipe1, recommendedRecipe);

  }

  /**
   * Adds two recipes to the RecipeBook with same dire values and
   * different urgent values based on the ingredients in the FoodStorage
   * and the current date.
   * Checks that the recipe returned by the recommendRecipe method is
   * the recipe with the highest urgent value.
   */
  @Test
  public void recommendRecipeWithSameDireValueAndDifferentUrgentValues() {

    Recipe recipe1 = new Recipe("Fruit Salad",
            "Cut fruits and add it to a bowl");
    Recipe recipe2 = new Recipe("Pasta with tomato sauce",
            "Boil water and cook pasta for 10 min. Drain and add tomato sauce");

    rp.addRecipe(recipe1);
    rp.addRecipe(recipe2);

    FoodStorage fd = new FoodStorage();

    Ingredient apple = new Ingredient("Apple", 2, "Stk");
    Ingredient orange = new Ingredient("Orange", 3, "Stk");
    Ingredient pasta = new Ingredient("Pasta", 100, "Grams");
    Ingredient tomatoSauce = new Ingredient("Tomato sauce", 0.2, "Litres");

    Ingredient apple1 = new Ingredient("Apple", 3, "Stk",
            12.9, 2024, 1, 8);
    Ingredient orange1 = new Ingredient("Orange", 4, "Stk",
            15.0, 2024, 1, 4);
    Ingredient pasta1 = new Ingredient("Pasta", 100, "Stk",
            15.0, 2024, 1, 5);
    Ingredient tomatoSauce1 = new Ingredient("Tomato sauce", 0.5, "Litres", 15.2,
            2024, 1, 6);

    recipe1.addIngredient(apple);
    recipe1.addIngredient(orange);

    recipe2.addIngredient(pasta);
    recipe2.addIngredient(tomatoSauce);

    fd.addIngredient(apple1);
    fd.addIngredient(orange1);
    fd.addIngredient(pasta1);
    fd.addIngredient(tomatoSauce1);

    LocalDate currentDate = LocalDate.of(2024, 1, 1);

    Recipe recommendedRecipe = rp.recommendRecipe(currentDate, fd);
    assertEquals(recipe2, recommendedRecipe);
  }




//---------------------------------NEGATIVE TESTS-----------------------------

  /**
   * Tries to add two recipes with the same name to the RecipeBook.
   * Checks to see if an exception is caught.
   */
  @Test
  public void addRecipeWithNameThatAlreadyExists() {
    try {

      Recipe recipe1 = new Recipe("Fruit Salad",
              "Cut fruits and add it to a bowl");
      Recipe recipe2 = new Recipe("Fruit Salad",
              "Cut fruits, add fruit bits and cream to a bowl");

      rp.addRecipe(recipe1);
      rp.addRecipe(recipe2);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  /**
   * Tries to get a recipe that doesn't exist in the recipe book.
   * Checks to see if an exception is caught.
   */
  @Test
  public void getRecipeWhichDoesntExist() {
    try {
      rp.getRecipe("Pasta");
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  /**
   * Tries to remove a recipe that doesn't exist in the recipe book.
   * Checks to see if an exception is caught.
   */
  @Test
  public void removeRecipeThatDoesntExist() {
    try {
      rp.deleteRecipe("Pasta");
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
}
