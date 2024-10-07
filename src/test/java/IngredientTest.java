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
        Ingredient ingredient = new Ingredient("Apple");
        assertEquals("Apple",ingredient.getName());
    }

    @Test
    public void createInstanceWithInvalidName(){
        try{
            Ingredient ingredient = new Ingredient("");
            fail();
        } catch (IllegalArgumentException e){
            assertTrue(true);

        }
    }

}

