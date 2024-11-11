import edu.ntnu.iir.bidata.fridser.data.Ingredient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the class edu.ntnu.iir.bidata.fridser.data.Ingredient
 *
 * <p>The following is tested:</p>
 *
 * <b>Positive tests:</b>
 *
 * <ul>
 *   <li>creation of edu.ntnu.iir.bidata.fridser.data.Ingredient object with valid name , amount, unit, price and expiryDate</li>
 *   <li>that the accessor-method returning full name returns correct name</li>
 *   <li>that the accessor-method returning amount returns correct amount</li>
 *   <li>that the accessor-method returning unit returns correct unit </li>
 *   <li>that the accessor-method returning price returns correct price </li>
 *   <li>that setting the amount results in the correct amount being set</li>
 *   <li>that setting the name with valid string results in the name being set.</li>
 *   <li>that setting the unit results in the correct unit being set</li>
 *   <li>that setting the price results in the correct price being set</li>
 * </ul>
 *
 * <b>Negative tests:</b>
 *
 * <ul>
 *   <li>that it is not possible to set negative amount - constructor, nor setAmount()</li>
 *   <li>that it is not possible to create a person with an empty name, or value of name is "null"</li>
 *   <li>that it is not possible to create a person with an empty unit, or value of unit is "null"</li>
 *   <li>that it is not possible to set negative price - constructor, nor setPrice()</li>
 * </ul>
 */

public class IngredientTest {

    /**
     * creates an object with valid Name
     * Uses set method in constructor
     * checks that the name field returns the correct info
     */
    @Test
    public void createInstanceWithValidName(){
        Ingredient ingredient = new Ingredient("Apple", 2, "Kg", 12.9,2000,1,1);

        assertEquals("Apple",ingredient.getIngredientName());
    }

    /**
     * Creates an object with empty name
     * checks if an exception is caught
     */
    @Test
    public void createInstanceWithInvalidName(){
        try{
            Ingredient ingredient = new Ingredient("", 2, "Kg", 12.9,2000, 1, 1);
            fail();
        } catch (IllegalArgumentException e){
            assertTrue(true);

        }
    }

    /**
     * Creates an object with valid amount
     * Uses set method in constructor
     * Checks that the amount field returns the correct info
     */
    @Test
    public void createInstanceWithValidAmount(){
        Ingredient ingredient = new Ingredient("Apple", 2, "Kg", 12.9,2000,1,1);
        assertEquals(2,ingredient.getAmount());
    }

    /**
     * Creates an object with negative amount
     * Checks if an exception is caught
     */
    @Test
    public void createInstanceWithInvalidAmount(){
        try{
            Ingredient ingredient = new Ingredient("Apple", -2, "Kg", 12.9,2000, 1, 1);
            fail();
        } catch (IllegalArgumentException e){
            assertTrue(true);

        }
    }

    /**
     * creates an object with valid unit
     * Uses set method in constructor
     * checks that the unit field returns the correct info
     */
    @Test
    public void createInstanceWithValidUnit(){
        Ingredient ingredient = new Ingredient("Apple", 2, "Kg", 12.9,2000,1,1);
        assertEquals("Kg",ingredient.getUnit());
    }

    /**
     * Creates an object with empty unit
     * checks if an exception is caught
     */
    @Test
    public void createInstanceWithInvalidUnit(){
        try{
            Ingredient ingredient = new Ingredient("Apple", 2, "", 12.9,2000, 1, 1);
            fail();
        } catch (IllegalArgumentException e){
            assertTrue(true);

        }
    }

    /**
     * creates an object with valid price
     * Uses set method in constructor
     * checks that the price field returns the correct info
     */
    @Test
    public void createInstanceWithValidPrice(){
        Ingredient ingredient = new Ingredient("Apple", 2, "Kg", 12.9,2000,1,1);
        assertEquals(12.9,ingredient.getPrice());
    }

    /**
     * Creates an object with negative price
     * checks if an exception is caught
     */
    @Test
    public void createInstanceWithInvalidPrice(){
        try{
            Ingredient ingredient = new Ingredient("Apple", 2, "Kg", -12.9,2000, 1, 1);
            fail();
        } catch (IllegalArgumentException e){
            assertTrue(true);

        }
    }



    /**
     * Tries to create an object with valid parameters and change the name to
     * an empty name
     * checks if an exception is caught
     */
    @Test
    public void changeNameWithInvalidNewName(){
        try{
            Ingredient ingredient = new Ingredient("Apple", 2, "Kg", 12.9,2000,1,1);
            ingredient.setIngredientName("");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }



    /**
     * Tries to create an object with valid parameters and change the amount to
     * a negative amount
     * checks if an exception is caught
     */
    @Test
    public void changeAmountWithInvalidNewAmount(){
        try{
            Ingredient ingredient = new Ingredient("Apple", 2, "Kg", 12.9,2000,1,1);
            ingredient.setAmount(-2);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }



    /**
     * Tries to create an object with valid parameters and change the unit to
     * an empty unit
     * checks if an exception is caught
     */
    @Test
    public void changeUnitWithInvalidNewUnit(){
        try{
            Ingredient ingredient = new Ingredient("Apple", 2, "Kg", 12.9,2000,1,1);
            ingredient.setUnit("");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }



    /**
     * Tries to create an object with valid parameters and change the price to
     * a negative amount
     * checks if an exception is caught
     */
    @Test
    public void changePriceWithInvalidNewPrice(){
        try{
            Ingredient ingredient = new Ingredient("Apple", 2, "Kg", 12.9,2000,1,1);
            ingredient.setPrice(-3);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * Reduces the amount of the object with a valid reduced amount.
     * Checks that the amount is equal to the initial amount minus the reduced
     * amount.
     */
    @Test
    public void reduceValidAmount(){
        Ingredient ingredient = new Ingredient("Apple", 8, "Kg", 12.9,2000,1,1);
        ingredient.reduceAmount(5);

        assertEquals(3,ingredient.getAmount());
    }

    /**
     * Tries to reduce the amount of the object with a bigger reduced amount
     * than the initial amount.
     * Checks if an exception is caught.
     */
    @Test
    public void reduceWithMoreAmountThanIsInTheIngredientAmount(){
        try{
            Ingredient ingredient = new Ingredient("Apple", 2, "Kg", 12.9,2000,1,1);
            ingredient.reduceAmount(5);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * Adds a valid amount to the object. Checks that the amount of the object is
     * equal to the sum of the initial amount and the added amount.
     */
    @Test
    public void addValidAmount(){
        Ingredient ingredient = new Ingredient("Apple", 3, "Kg", 12.9,2000,1,1);
        ingredient.addAmount(5);

        assertEquals(8,ingredient.getAmount());
    }

    /**
     * Tries to add a negative amount to the objects initial amount.
     * Checks if an exception is caught.
     */
    @Test
    public void addInvalidAmount(){
        try{
            Ingredient ingredient = new Ingredient("Apple", 3, "Kg", 12.9,2000,1,1);
            ingredient.addAmount(-5);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }


}

