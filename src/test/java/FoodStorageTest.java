import edu.ntnu.iir.bidata.fridser.data.Ingredient;
import edu.ntnu.iir.bidata.fridser.logic.FoodStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the class edu.ntnu.iir.bidata.fridser.logic.FoodStorage
 *
 * <p>The following is tested:</p>
 *
 * <b>Positive tests:</b>
 *
 * <ul>
 *   <li>creation of edu.ntnu.iir.bidata.fridser.logic.FoodStorage object with valid Ingredient being added to ingredients</li>
 *   <li>that the accessor method returning objects at a specific index returns the correct object</li>
 *   <li>That two Ingredients with the same name amd expiry date gets added to the list as one object</li>
 *   <li>that two ingredients with same name and different expiry dates gets addded to the list as two separate objects</li>
 *   <li></li>
 * </ul>
 *
 * <b>Negative tests:</b>
 *
 * <ul>
 *   <li></li>
 *   <li></li>
 * </ul>
 */

public class FoodStorageTest {


    /**
     * Creates three valid ingredients, creates a foodstorage and adds
     * the ingredients.Checks that all  the ingredients got added by returning the item at the
     * index.
     *
     */
    @Test
    public void createFoodStorageWithValidIngredients(){
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Orange",5,"Stk",15.2,2025,3,12);
        Ingredient ingredient3 = new Ingredient("Milk",0.5,"Litres",25.9,2025,1,1);

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
    public void addIngredientsWithTheSameName(){
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Apple",5,"Stk",15.2,2025,3,12);

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
    public void addItemsWithTheSameNameAndExpiryDate(){
        Ingredient ingredient1 = new Ingredient("Apple",3,"Stk",12.3,2024,12,12);
        Ingredient ingredient2 = new Ingredient("Apple",5,"Stk",15.2,2024,12,12);

        FoodStorage fd = new FoodStorage();

        fd.addIngredient(ingredient1);
        fd.addIngredient(ingredient2);

        assertEquals(1,fd.getNumberOfIngredients());
        assertEquals(8,fd.getIngredient(0).getAmount());
    }
}
