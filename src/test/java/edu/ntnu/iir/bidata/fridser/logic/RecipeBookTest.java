package edu.ntnu.iir.bidata.fridser.logic;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;
import org.junit.jupiter.api.Test;

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
}
