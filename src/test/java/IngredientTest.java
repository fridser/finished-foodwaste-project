import edu.ntnu.iir.bidata.fridser.data.Ingredient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
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

        assertEquals("Apple",ingredient.getName());
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
            ingredient.setName("");
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

}

