package edu.ntnu.iir.bidata.fridser.logic;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecipeBookTest {

//-------------------------------POSITIVE TESTS-----------------------------------

  /**
   * Creates an instance of RecipeBook and adds valid recipes to it.
   * Checks that the get method returns the correct recipes.
   */
  @Test
  public void createRecipeBookWithMultipleRecipes() {
    RecipeBook rp = new RecipeBook();

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
    RecipeBook rp = new RecipeBook();

    Recipe recipe1 = new Recipe("Fruit Salad",
            "Cut fruits and add it to a bowl");
    Recipe recipe2 = new Recipe("Pasta with tomato sauce",
            "Boil water and cook pasta for 10 min. Drain and add tomato sauce");

    rp.addRecipe(recipe1);
    rp.addRecipe(recipe2);

    rp.deleteRecipe("Fruit Salad");

    assertFalse(rp.containsRecipe(recipe1));
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
    RecipeBook rb = new RecipeBook();

    Recipe recipe1 = new Recipe("Fruit Salad",
            "Cut fruits and add it to a bowl");
    Recipe recipe2 = new Recipe("Pasta with tomato sauce",
            "Boil water and cook pasta for 10 min. Drain and add tomato sauce");

    rb.addRecipe(recipe1);
    rb.addRecipe(recipe2);

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

    Iterator<Recipe> it = rb.getPossibleRecipes(fd);

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
    RecipeBook rb = new RecipeBook();

    Recipe recipe1 = new Recipe("Fruit Salad",
            "Cut fruits and add it to a bowl");
    Recipe recipe2 = new Recipe("Pasta with tomato sauce",
            "Boil water and cook pasta for 10 min. Drain and add tomato sauce");

    rb.addRecipe(recipe1);
    rb.addRecipe(recipe2);

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

    Recipe recommendedRecipe = rb.recommendRecipe(currentDate, fd);
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
    RecipeBook rb = new RecipeBook();

    Recipe recipe1 = new Recipe("Fruit Salad",
            "Cut fruits and add it to a bowl");
    Recipe recipe2 = new Recipe("Pasta with tomato sauce",
            "Boil water and cook pasta for 10 min. Drain and add tomato sauce");

    rb.addRecipe(recipe1);
    rb.addRecipe(recipe2);

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

    Recipe recommendedRecipe = rb.recommendRecipe(currentDate, fd);
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
      RecipeBook rb = new RecipeBook();

      Recipe recipe1 = new Recipe("Fruit Salad",
              "Cut fruits and add it to a bowl");
      Recipe recipe2 = new Recipe("Fruit Salad",
              "Cut fruits, add fruit bits and cream to a bowl");

      rb.addRecipe(recipe1);
      rb.addRecipe(recipe2);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
}
