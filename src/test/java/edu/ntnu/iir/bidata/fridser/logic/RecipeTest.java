package edu.ntnu.iir.bidata.fridser.logic;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

//------------------------------POSITIVE TESTS--------------------------------


    /**
     * Creates an instance of recipe with valid parametres.
     * Checks that the correct name and instruction is returned.
     */
    @Test
    public void createRecipeWithValidParameters() {
        Recipe recipe = new Recipe("Pasta",
                "Boil water and cook pasta for 10 minutes");
        assertEquals("Pasta", recipe.getRecipeName());
        assertEquals("Boil water and cook pasta for 10 minutes", recipe.getInstruction());
    }

    /**
     * Adds an ingredient to a recipe and checks if the ingredient is added to the
     * FoodStorage in the recipe containing the Ingredients.
     */
    @Test
    public void addIngredientToRecipe() {
        Recipe recipe = new Recipe("Fruit Salad",
                "Cut fruits and add it to a bowl");
        Ingredient ingredient = new Ingredient("Apple", 2, "Stk");
        recipe.addIngredient(ingredient);

        assertEquals(ingredient, recipe.getIngredientIterator().next());
    }

    /**
     * Adds two ingredients to a recipe and checks if both ingredients are
     * added to the list.
     */
    @Test
    public void addMultipleIngredientsToRecipe() {
        Recipe recipe = new Recipe("Fruit Salad",
                "Cut fruits and add it to a bowl");

        Ingredient apple = new Ingredient("Apple", 2, "Stk");
        Ingredient orange = new Ingredient("Orange", 3, "Stk");

        recipe.addIngredient(apple);
        recipe.addIngredient(orange);

        assertEquals(apple, recipe.getIngredient("Apple"));
        assertEquals(orange, recipe.getIngredient("Orange"));
    }

    /**
     * Creates a recipe and a FoodStorage with the same ingredients
     * and the same amount of the ingredients.
     * Checks to see that the recipe can be used.
     */
    @Test
    public void seeIfRecipeCanBeUsedIfThereAreEnoughIngredients() {
        Recipe recipe = new Recipe("Fruit Salad",
                "Cut fruits and add it to a bowl");
        FoodStorage fd = new FoodStorage();

        Ingredient apple = new Ingredient("Apple", 2, "Stk");
        Ingredient orange = new Ingredient("Orange", 3, "Stk");

        recipe.addIngredient(apple);
        recipe.addIngredient(orange);
        fd.addIngredient(apple);
        fd.addIngredient(orange);

        assertTrue(recipe.canUseRecipe(fd));
    }

    /**
     * Creates a recipe and a FoodStorage, the foodstorage is missing
     * an ingredient in the recipe.
     * Checks that the recipe can not be made.
     */
    @Test
    public void seeIfRecipeCanBeUsedIfAnIngredientIsMissing() {
        Recipe recipe = new Recipe("Fruit Salad",
                "Cut fruits and add it to a bowl");
        FoodStorage fd = new FoodStorage();

        Ingredient apple = new Ingredient("Apple", 2, "Stk");
        Ingredient orange = new Ingredient("Orange", 3, "Stk");

        recipe.addIngredient(apple);
        recipe.addIngredient(orange);
        fd.addIngredient(apple);

        assertFalse(recipe.canUseRecipe(fd));
    }

    /**
     * Creates a recipe and a foodstorage. The foodstorage doesn't
     * have enough of each ingredient to make each recipe.
     * Checks that the recipe cannot be made.
     */
    @Test
    public void seeIfRecipeCanBeUsedIfThereIsNotEnoughIngredients() {
        Recipe recipe = new Recipe("Fruit Salad",
                "Cut fruits and add it to a bowl");
        FoodStorage fd = new FoodStorage();

        Ingredient apple = new Ingredient("Apple", 2, "Stk");
        Ingredient orange = new Ingredient("Orange", 3, "Stk");

        Ingredient apple1 = new Ingredient("Apple", 1, "Stk");
        Ingredient orange1 = new Ingredient("Orange", 2, "Stk");

        recipe.addIngredient(apple);
        recipe.addIngredient(orange);

        fd.addIngredient(apple1);
        fd.addIngredient(orange1);

        assertFalse(recipe.canUseRecipe(fd));
    }

    /**
     * Checks if the ingredient iterator returns all added ingredients.
     * Written with copilot.
     */
    @Test
    public void getIngredientIteratorReturnsAllIngredients() {
        Recipe recipe = new Recipe("Fruit Salad",
                "Cut fruits and add it to a bowl");
        Ingredient apple = new Ingredient("Apple", 2, "Stk");
        Ingredient orange = new Ingredient("Orange", 3, "Stk");
        recipe.addIngredient(apple);
        recipe.addIngredient(orange);

        Iterator<Ingredient> iterator = recipe.getIngredientIterator();
        assertTrue(iterator.hasNext());
        assertEquals(apple, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(orange, iterator.next());
        assertFalse(iterator.hasNext());
    }

    /**
     * Checks if getUrgentValue returns the correct count of ingredients
     * expiring within a week.
     * Written with copilot.
     */
    @Test
    public void getUrgentValueReturnsCorrectCount() {
        Recipe recipe = new Recipe("Fruit Salad",
                "Cut fruits and add it to a bowl");
        FoodStorage fd = new FoodStorage();
        LocalDate currentDate = LocalDate.of(2023, 1, 1);

        Ingredient apple = new Ingredient("Apple", 2, "Stk",
                12.9, 2023, 1, 5);
        Ingredient orange = new Ingredient("Orange", 3, "Stk",
                15.0, 2023, 1, 10);
        Ingredient banana = new Ingredient("Banana", 1, "Stk",
                10.0, 2023, 1, 15);

        recipe.addIngredient(apple);
        recipe.addIngredient(orange);
        recipe.addIngredient(banana);

        fd.addIngredient(apple);
        fd.addIngredient(orange);
        fd.addIngredient(banana);

        assertEquals(1, recipe.getUrgentValue(currentDate, fd));
    }

    /**
     * Checks if getUrgentValue returns zero when no ingredients
     * are expiring within a week.
     * Written with copilot.
     */
    @Test
    public void getUrgentValueReturnsZeroWhenNoIngredientsExpiring() {
        Recipe recipe = new Recipe("Fruit Salad",
                "Cut fruits and add it to a bowl");
        FoodStorage fd = new FoodStorage();
        LocalDate currentDate = LocalDate.of(2023, 1, 1);

        Ingredient apple = new Ingredient("Apple", 2, "Stk",
                12.9, 2023, 1, 15);
        Ingredient orange = new Ingredient("Orange", 3, "Stk",
                15.0, 2023, 1, 20);

        recipe.addIngredient(apple);
        recipe.addIngredient(orange);

        fd.addIngredient(apple);
        fd.addIngredient(orange);

        assertEquals(0, recipe.getUrgentValue(currentDate, fd));
    }

    /**
     * Checks if getDireValue returns the correct count of ingredients
     * expiring within a day.
     * Written with copilot.
     */
    @Test
    public void getDireValueReturnsCorrectCount() {
        Recipe recipe = new Recipe("Fruit Salad",
                "Cut fruits and add it to a bowl");
        FoodStorage fd = new FoodStorage();
        LocalDate currentDate = LocalDate.of(2023, 1, 1);

        Ingredient apple = new Ingredient("Apple", 2, "Stk",
                12.9, 2023, 1, 1);
        Ingredient orange = new Ingredient("Orange", 3, "Stk",
                15.0, 2023, 1, 2);
        Ingredient banana = new Ingredient("Banana", 1, "Stk",
                10.0, 2023, 1, 3);

        recipe.addIngredient(apple);
        recipe.addIngredient(orange);
        recipe.addIngredient(banana);

        fd.addIngredient(apple);
        fd.addIngredient(orange);
        fd.addIngredient(banana);

        assertEquals(2, recipe.getDireValue(currentDate, fd));
    }

    /**
     * Checks if getDireValue returns zero when no ingredients
     * are expiring within a day.
     * Written with copilot.
     */
    @Test
    public void getDireValueReturnsZeroWhenNoIngredientsExpiring() {
        Recipe recipe = new Recipe("Fruit Salad",
                "Cut fruits and add it to a bowl");
        FoodStorage fd = new FoodStorage();
        LocalDate currentDate = LocalDate.of(2023, 1, 1);

        Ingredient apple = new Ingredient("Apple", 2, "Stk",
                12.9, 2023, 1, 3);
        Ingredient orange = new Ingredient("Orange", 3, "Stk",
                15.0, 2023, 1, 4);

        recipe.addIngredient(apple);
        recipe.addIngredient(orange);

        fd.addIngredient(apple);
        fd.addIngredient(orange);

        assertEquals(0, recipe.getDireValue(currentDate, fd));
    }

    /**
     * Checks that the getLackingIngredients methods returns the correct
     * amount of each ingredient.
     */
    @Test
    public void getLackingIngredientsWithAllIngredientsLacking() {
        Recipe recipe = new Recipe("Fruit Salad",
                "Cut fruits and add it to a bowl");
        FoodStorage fd = new FoodStorage();

        Ingredient apple = new Ingredient("Apple", 2, "Stk");
        Ingredient orange = new Ingredient("Orange", 3, "Stk");

        Ingredient apple1 = new Ingredient("Apple", 1, "Stk",
                12.9, 2023, 1, 3);
        Ingredient orange1 = new Ingredient("Orange", 2, "Stk",
                15.0, 2023, 1, 4);

        recipe.addIngredient(apple);
        recipe.addIngredient(orange);

        fd.addIngredient(apple1);
        fd.addIngredient(orange1);

        Iterator<Ingredient> iterator = recipe.getLackingIngredients(fd);

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next().getAmount());
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next().getAmount());
        assertFalse(iterator.hasNext());
    }




//-----------------------------------NEGATIVE TESTS--------------------


    /**
     * Tries to create an instance of recipe with an empty name.
     * Checks to see if an exception is caught.
     */
    @Test
    public void createRecipeWithEmptyName() {
        try {
            Recipe recipe = new Recipe("",
                    "Boil water and cook pasta");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * Tries to create an instance of recipe with empty instruction.
     * Checks to see if an exception is caught.
     */
    @Test
    public void createRecipeWithEmptyInstruction() {
        try {
            Recipe recipe = new Recipe("Pasta", "");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * Tries to set the recipe name to an empty string.
     * Checks if an exception is caught.
     * Written with copilot.
     */
    @Test
    public void setRecipeNameToEmptyString() {
        Recipe recipe = new Recipe("Pasta",
                "Boil water and cook pasta for 10 minutes");
        try {
            recipe.setRecipeName("");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * Tries to set the recipe instruction to an empty string.
     * Checks if an exception is caught..
     * Written with copilot.
     */
    @Test
    public void setInstructionToEmptyString() {
        Recipe recipe = new Recipe("Pasta",
                "Boil water and cook pasta for 10 minutes");
        try {
            recipe.setInstruction("");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }


}
