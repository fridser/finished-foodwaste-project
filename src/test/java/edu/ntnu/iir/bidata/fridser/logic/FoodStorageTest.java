package edu.ntnu.iir.bidata.fridser.logic;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the class edu.ntnu.iir.bidata.fridser.logic.FoodStorage
 *
 * <p>The following is tested:</p>
 *
 * <b>Positive tests:</b>
 *
 * <ul>
 *   <li>creation of edu.ntnu.iir.bidata.fridser.logic.FoodStorage object with valid Ingredient being added to ingredients</li>
 *   <li>that the accessor method returning objects at a specific
 *   index returns the correct object</li>
 *   <li>That two Ingredients with the same name amd expiry date gets
 *   added to the list as one object</li>
 *   <li>that two ingredients with same name and different expiry dates
 *   gets addded to the list as two separate objects</li>
 *   <li>that the mutator method deleteIngredient deletes the correct
 *   ingredient</li>
 *   <li>that the accessor method findIngredintByName returns the correct
 *   ingredient</li>
 * </ul>
 *
 * <b>Negative tests:</b>
 *
 * <ul>
 *   <li>Use invalid amount of ingredients</li>
 *   <li>Search for an ingredient with a blank search name</li>
 * </ul>
 */

public class FoodStorageTest {


    //------------------------------POSITIVE TESTS----------------------------------

    /**
     * Creates three valid ingredients, creates a foodstorage and adds
     * the ingredients.Checks that all  the ingredients got added by returning the item at the
     * index.
     *
     */
    @Test
    public void createFoodStorageWithValidIngredients() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Orange",5,"Stk",
                15.2,2025,3,12);
        Ingredient ingredient3 = new Ingredient("Milk",0.5,"Litres",
                25.9,2025,1,1);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);
        fd.addIngredient(ingredient3);

        assertEquals(ingredient1,fd.getIngredient(0));
        assertEquals(ingredient2,fd.getIngredient(1));
        assertEquals(ingredient3,fd.getIngredient(2));

    }

    /**
     * Creates two ingredients with same name and different expiration dates,
     * and adds them to a FoodStorage. Checks that they were added to the list
     * as two separate items. Checks that the getAmountOfIngredients method
     * returns the sum of the two amounts of ingredients.
     */
    @Test
    public void addIngredientsWithTheSameName() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Apple",5,"Stk",
                15.2,2025,3,12);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);

        assertEquals(2,fd.getNumberOfIngredients());
        assertEquals(8,fd.getAmountOfIngredients("Apple"));
    }

    /**
     * Creates two ingredients with same name and same expiration dates,
     * and adds them to a FoodStorage. Checks that they were added to the list
     * as one item. Checks that the amount of this item is the same as the amount
     * of the two ingredients added up.
     */
    @Test
    public void addItemsWithTheSameNameAndExpiryDate() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Apple",5,"Stk",
                15.2,2024,12,12);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);

        assertEquals(1,fd.getNumberOfIngredients());
        assertEquals(8,fd.getIngredient(0).getAmount());
    }

    /**
     * Deletes the specified ingredient. Checks that the ingredient at initial
     * index 1 is now at index 0, and that the list is one less size.
     */
    @Test
    public void deleteValidIngredient() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Orange",5,"Stk",
                15.2,2025,3,12);
        Ingredient ingredient3 = new Ingredient("Milk",0.5,"Litres",
                25.9,2025,1,1);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);
        fd.addIngredient(ingredient3);

        fd.deleteIngredient(ingredient1);

        assertEquals(ingredient2,fd.getIngredient(0));
        assertEquals(2,fd.getNumberOfIngredients());
    }

    /**
     * Adds three ingredients with different names to a food storage.
     * Checks if the findIngredientByName method returns the correct
     * ingredient
     */
    @Test
    public void findIngredientByNameWithOnlyOneInstanceOfTheName() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Orange",5,"Stk",
                15.2,2025,3,12);
        Ingredient ingredient3 = new Ingredient("Milk",0.5,"Litres",
                25.9,2025,1,1);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);
        fd.addIngredient(ingredient3);

        assertEquals(ingredient3,fd.findIngredientByName("Milk"));
    }

    /**
     * Uses a specified amount of a specified ingredient name. This amount
     * is less than the amount of the first ingredient with the specified name.
     * Checks that the specified amount is subtracted from the amount of the first
     * ingredient.
     */
    @Test
    public void useLessAmountThanTheFirstIngredient() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Orange",5,"Stk",
                15.2,2025,3,12);
        Ingredient ingredient3 = new Ingredient("Milk",0.5,"Litres",
                25.9,2025,1,1);
        Ingredient ingredient4 = new Ingredient("Apple",5,"Stk",
                15.2,2025,3,12);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);
        fd.addIngredient(ingredient3);
        fd.addIngredient(ingredient4);

        fd.useIngredient(2,"Apple");

        assertEquals(1,fd.getIngredient(0).getAmount());
    }

    /**
     * Uses a specified amount of a specified ingredient name. This amount is more
     * than the first
     */
    @Test
    public void useMoreAmountThanFirstIngredient() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Orange",5,"Stk",
                15.2,2025,3,12);
        Ingredient ingredient3 = new Ingredient("Milk",0.5,"Litres",
                25.9,2025,1,1);
        Ingredient ingredient4 = new Ingredient("Apple",5,"Stk",
                15.2,2025,3,12);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);
        fd.addIngredient(ingredient3);
        fd.addIngredient(ingredient4);

        fd.useIngredient(5,"Apple");

        assertEquals(3,fd.getAmountOfIngredients("Apple"));
        assertEquals(3,fd.getNumberOfIngredients());
    }

    /**
     * Checks that the use ingredient method prioritizes items
     * with lower expiration dates.
     */
    @Test
    public void useIngredientsWithDifferentDates() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Apple",2,"Stk",
                25.9,2023,3,12);
        Ingredient ingredient3 = new Ingredient("Apple",5,"Stk",
                15.2,2025,1,1);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);
        fd.addIngredient(ingredient3);

        fd.useIngredient(5,"Apple");

        assertEquals(ingredient1,fd.getIngredient(0));
        assertEquals(ingredient3,fd.getIngredient(1));
        assertEquals(5,fd.getAmountOfIngredients("Apple"));

    }

    /**
     * Tries to use more of an ingredient than there are in a foodstorage.
     * Asserts false. Checks that the ingredients haven't been used by using getAmountOfIngredients.
     */
    @Test
    public void useTooMuchOfIngredient() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Apple",5,"Stk",
                15.2,2025,3,12);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);

        assertFalse(fd.useIngredient(10,"Apple"));
        assertEquals(8,fd.getAmountOfIngredients("Apple"));
    }

    /**
     * Deletes all ingredients with the same name. Checks that the ingredients
     * with the specified name are removed and the others remain.
     * Written with copilot.
     */
    @Test
    public void deleteAllIngredientsWithSameName() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Apple",5,"Stk",
                15.2,2025,3,12);
        Ingredient ingredient3 = new Ingredient("Milk",0.5,"Litres",
                25.9,2025,1,1);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);
        fd.addIngredient(ingredient3);

        fd.deleteAllIngredientWithSameName("Apple");

        assertEquals(1, fd.getNumberOfIngredients());
        assertEquals(ingredient3, fd.getIngredient(0));
    }

    /**
     * Adds multiple ingredients and checks if the getIngredientList method
     * returns an iterator containing all the ingredients.
     * Written with copilot.
     */
    @Test
    public void getIngredientListWithMultipleIngredients() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Orange",5,"Stk",
                15.2,2025,3,12);
        Ingredient ingredient3 = new Ingredient("Milk",0.5,"Litres",
                25.9,2025,1,1);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);
        fd.addIngredient(ingredient3);

        Iterator<Ingredient> it = fd.getIngredientList();
        assertTrue(it.hasNext());
        assertEquals(ingredient1, it.next());
        assertEquals(ingredient2, it.next());
        assertEquals(ingredient3, it.next());
        assertFalse(it.hasNext());
    }

    /**
     * Checks if the getIngredientList method returns an empty iterator
     * when no ingredients are added.
     * Written with copilot.
     */
    @Test
    public void getIngredientListWithNoIngredients() {
        FoodStorage fd = new FoodStorage();
        Iterator<Ingredient> it = fd.getIngredientList();
        assertFalse(it.hasNext());
    }

    /**
     * Adds multiple ingredients and checks if the sortAlphabetically method
     * sorts them in alphabetical order.
     * Written with copilot.
     */
    @Test
    public void sortAlphabeticallyWithMultipleIngredients() {
        Ingredient ingredient1 = new Ingredient("Banana",3,"Stk",
                12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Apple",5,"Stk",
                15.2,2025,3,12);
        Ingredient ingredient3 = new Ingredient("Milk",0.5,"Litres",
                25.9,2025,1,1);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);
        fd.addIngredient(ingredient3);

        fd.sortAlphabetically();

        assertEquals(ingredient2, fd.getIngredient(0));
        assertEquals(ingredient1, fd.getIngredient(1));
        assertEquals(ingredient3, fd.getIngredient(2));
    }

    /**
     * Adds multiple ingredients with different expiry dates and checks if the getExpiredIngredients method
     * returns only the expired ingredients.
     * Written with copilot.
     */
    @Test
    public void getExpiredIngredientsWithMultipleIngredients() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2022,12,12);
        Ingredient ingredient2 = new Ingredient("Orange",5,"Stk",
                15.2,2025,3,12);
        Ingredient ingredient3 = new Ingredient("Milk",0.5,"Litres",
                25.9,2021,1,1);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);
        fd.addIngredient(ingredient3);

        Iterator<Ingredient> it = fd.getExpiredIngredients(LocalDate.of(2023, 1, 1));

        assertTrue(it.hasNext());
        assertEquals(ingredient1, it.next());
        assertEquals(ingredient3, it.next());
        assertFalse(it.hasNext());

    }

    /**
     * Checks if the getExpiredIngredients method returns an empty iterator
     * when no ingredients are expired.
     * Written with copilot.
     */
    @Test
    public void getExpiredIngredientsWithNoExpiredIngredients() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Orange",5,"Stk",
                15.2,2025,3,12);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);

        Iterator<Ingredient> it = fd.getExpiredIngredients(LocalDate.of(2023, 1, 1));
        assertFalse(it.hasNext());
    }

    /**
     * Adds multiple ingredients with different expiry dates and checks if the
     * removeExpiredIngredients method removes only the expired ingredients.
     * Written with copilot.
     */
    @Test
    public void removeExpiredIngredientsWithMultipleIngredients() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2022,12,12);
        Ingredient ingredient2 = new Ingredient("Orange",5,"Stk",
                15.2,2025,3,12);
        Ingredient ingredient3 = new Ingredient("Milk",0.5,"Litres",
                25.9,2021,1,1);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);
        fd.addIngredient(ingredient3);

        fd.removeExpiredIngredients(LocalDate.of(2023, 1, 1));

        assertEquals(1, fd.getNumberOfIngredients());
        assertEquals(ingredient2, fd.getIngredient(0));
    }

    /**
     * Adds multiple ingredients with the same expiry date and checks if the
     * removeExpiredIngredients method removes all of them when they are expired.
     * Written with copilot.
     */
    @Test
    public void removeExpiredIngredientsWithSameExpiryDate() {
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                12.3,2022,12,12);
        Ingredient ingredient2 = new Ingredient("Orange",5,"Stk",
                15.2,2022,12,12);
        Ingredient ingredient3 = new Ingredient("Milk",0.5,"Litres",
                25.9,2022,12,12);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);
        fd.addIngredient(ingredient3);

        fd.removeExpiredIngredients(LocalDate.of(2023, 1, 1));

        assertEquals(0, fd.getNumberOfIngredients());
    }


    //----------------------NEGATIVE TESTS------------------------------------

    /**
     * Tries to use a negative amount of an ingredient
     * Checks if an exception is caught
     */
    @Test
    public void useIngredientWithNegativeAmount() {
        try{
            Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                    12.3,2024,12,12);
            Ingredient ingredient2 = new Ingredient("Apple",5,"Stk",
                    15.2,2025,3,12);

            FoodStorage fd = new FoodStorage();

            fd.addIngredient(ingredient1);
            fd.addIngredient(ingredient2);

            fd.useIngredient(-3,"Apple");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);

        }
    }


    /**
     * Tries to use an ingredient with a blank name
     * Checks to see if an exception is caught
     */
    @Test
    public void useIngredientWithInvalidName() {
        try{
            Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                    12.3,2024,12,12);
            Ingredient ingredient2 = new Ingredient("Apple",5,"Stk",
                    15.2,2025,3,12);

            FoodStorage fd = new FoodStorage();

            fd.addIngredient(ingredient1);
            fd.addIngredient(ingredient2);

            fd.useIngredient(3,"");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);

        }
    }

    /**
     * Tries to find an ingredient with a blank name.
     * Checks to see if an exception is caught
     */
    @Test
    public void findIngredientWithInvalidName() {
        try{
            Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",
                    12.3,2024,12,12);
            Ingredient ingredient2 = new Ingredient("Apple",5,"Stk",
                    15.2,2025,3,12);

            FoodStorage fd = new FoodStorage();

            fd.addIngredient(ingredient1);
            fd.addIngredient(ingredient2);

            fd.findIngredientByName("");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);

        }
    }

    /**
     * Tries to get an ingredient with an index greater than the list size.
     * Checks to see if an exception is caught.
     * Written with copilot.
     */
    @Test
    public void getIngredientWithIndexGreaterThanSize() {
        try {
            FoodStorage fd = new FoodStorage();
            fd.getIngredient(1);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}
