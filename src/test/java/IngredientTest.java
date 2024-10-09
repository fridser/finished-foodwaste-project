import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * create a object with all its fields filled
 * check that these fields have the correct info
 */

public class IngredientTest {

    /**
     * creates an object with all its fields filled
     * checks that these fields return the correct info
     */
    @Test
    public void createInstanceWithValidName(){
        Ingredient ingredient = new Ingredient("Apple", 200, 1, 1);
        assertEquals("Apple",ingredient.getName());
    }

    @Test
    public void createInstanceWithInvalidName(){
        try{
            Ingredient ingredient = new Ingredient("", 2000, 1, 1);
            fail();
        } catch (IllegalArgumentException e){
            assertTrue(true);

        }
    }

}

