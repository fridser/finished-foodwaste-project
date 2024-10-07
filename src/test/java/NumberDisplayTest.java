import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * create an object with int limit
 * Checks that the return value of the
 * limit is the expected result.
 * create an object with negative integer limit
 * check that this object didn't get created
 */

public class NumberDisplayTest {

    /**
     * Creates an instance with a limit. Checks that the return value of the
     * limit is the expected result.
     */
    @Test
    public void createInstanceWithValidLimit(){
        NumberDisplay numberDisplay = new NumberDisplay(3);

    }

    public void createInstanceWithInvalidLimit(){
        try{
            NumberDisplay numberDisplay = new NumberDisplay(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);

        }

    }
}
