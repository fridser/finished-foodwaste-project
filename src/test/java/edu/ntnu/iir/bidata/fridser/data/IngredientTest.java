package edu.ntnu.iir.bidata.fridser.data;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
 *   <li>That the reduce amount method reduces the correct amount</li>
 *   <li>that the add amount method adds the corrrect amount</li>
 * </ul>
 *
 * <b>Negative tests:</b>
 *
 * <ul>
 *   <li>that it is not possible to set negative amount - constructor, nor setAmount()</li>
 *   <li>that it is not possible to create a person with an empty name, or value of name is "null"</li>
 *   <li>that it is not possible to create a person with an empty unit, or value of unit is "null"</li>
 *   <li>that it is not possible to set negative price - constructor, nor setPrice()</li>
 *   <li>That it is not possible to reduce with a negative amount</li>
 *   <li>that it is not possible to reduce with an amount that is bigger than the amount of the object</li>
 *   <li>thst it is not possible to add a negative amount</li>
 * </ul>
 */

public class IngredientTest {


//---------------------------------POSITIVE TESTS-----------------------------------------


  /**
   * creates an object with valid Name
   * Uses set method in constructor
   * checks that the name field returns the correct info
   */
  @Test
  public void createInstanceWithValidName() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
            12.9, 2000, 1, 1);

    assertEquals("Apple", ingredient.getName());
  }


  /**
   * Creates an object with valid amount
   * Uses set method in constructor
   * Checks that the amount field returns the correct info
   */
  @Test
  public void createInstanceWithValidAmount() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
            12.9, 2000, 1, 1);
    assertEquals(2, ingredient.getAmount());
  }

  /**
   * creates an object with valid unit
   * Uses set method in constructor
   * checks that the unit field returns the correct info
   */
  @Test
  public void createInstanceWithValidUnit() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
            12.9, 2000, 1, 1);
    assertEquals("Stk", ingredient.getUnit());
  }

  /**
   * creates an object with valid price
   * Uses set method in constructor
   * checks that the price field returns the correct info
   */
  @Test
  public void createInstanceWithValidPrice() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
            12.9, 2000, 1, 1);
    assertEquals(12.9, ingredient.getPrice());
  }

  /**
   * Creates an instance of ingredient with valid parameters
   * using the constructor meant for recipes.
   * Checks that the name, amount and unit field are correct.
   */
  @Test
  public void createInstanceWithValidParametersUsingRecipeConstructor() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk");

    assertEquals("Apple", ingredient.getName());
    assertEquals(2, ingredient.getAmount());
    assertEquals("Stk", ingredient.getUnit());
  }


  /**
   * Reduces the amount of the object with a valid reduced amount.
   * Checks that the amount is equal to the initial amount minus the reduced
   * amount.
   */
  @Test
  public void reduceValidAmount() {
    Ingredient ingredient = new Ingredient("Apple", 8, "Stk",
            12.9, 2000, 1, 1);
    ingredient.reduceAmount(5);

    assertEquals(3, ingredient.getAmount());
  }

  /**
   * Adds a valid amount to the object. Checks that the amount of the object is
   * equal to the sum of the initial amount and the added amount.
   */
  @Test
  public void addValidAmount() {
    Ingredient ingredient = new Ingredient("Apple", 3, "Stk",
            12.9, 2000, 1, 1);
    ingredient.addAmount(5);

    assertEquals(8, ingredient.getAmount());
  }

  /**
   * Checks if two ingredients with the same name, expiry date,
   * and unit are considered the same.
   * Written with copilot.
   */
  @Test
  public void isSameWithIdenticalIngredients() {
    Ingredient ingredient1 = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 1);
    Ingredient ingredient2 = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 1);
    assertTrue(ingredient1.isSame(ingredient2));
  }

  /**
   * Checks if two ingredients with different names are not
   * considered the same.
   * Written with copilot.
   */
  @Test
  public void isSameWithDifferentNames() {
    Ingredient ingredient1 = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 1);
    Ingredient ingredient2 = new Ingredient("Orange", 2, "Stk",
            12.9, 2023, 1, 1);
    assertFalse(ingredient1.isSame(ingredient2));
  }

  /**
   * Checks if two ingredients with different expiry dates are not
   * considered the same.
   * Written with copilot.
   */
  @Test
  public void isSameWithDifferentExpiryDates() {
    Ingredient ingredient1 = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 1);
    Ingredient ingredient2 = new Ingredient("Apple", 2, "Stk",
            12.9, 2024, 1, 1);
    assertFalse(ingredient1.isSame(ingredient2));
  }

  /**
   * Checks if two ingredients with different units are not
   * considered the same.
   * Written with copilot.
   */
  @Test
  public void isSameWithDifferentUnits() {
    Ingredient ingredient1 = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 1);
    Ingredient ingredient2 = new Ingredient("Apple", 2, "Grams",
            12.9, 2023, 1, 1);
    assertFalse(ingredient1.isSame(ingredient2));
  }

  /**
   * Checks if an ingredient is expired when the current
   * date is after the expiry date.
   * Written with copilot.
   */
  @Test
  public void isExpiredWhenCurrentDateIsAfterExpiryDate() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 1);
    assertTrue(ingredient.isExpired(LocalDate.of(2023, 1, 2)));
  }

  /**
   * Checks if an ingredient is not expired when the current date
   * is before the expiry date.
   * Written with copilot.
   */
  @Test
  public void isExpiredWhenCurrentDateIsBeforeExpiryDate() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 1);
    assertFalse(ingredient.isExpired(LocalDate.of(2022, 12, 31)));
  }

  /**
   * Checks if an ingredient is not expired when the current date
   * is the same as the expiry date.
   * Written with copilot.
   */
  @Test
  public void isExpiredWhenCurrentDateIsSameAsExpiryDate() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 1);
    assertFalse(ingredient.isExpired(LocalDate.of(2023, 1, 1)));
  }

  /**
   * Checks if an ingredient is urgent when the current date
   * is within a week before the expiry date.
   * Written with copilot.
   */
  @Test
  public void isUrgentWhenCurrentDateIsWithinAWeekBeforeExpiryDate() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 8);
    assertTrue(ingredient.isUrgent(LocalDate.of(2023, 1, 2)));
  }

  /**
   * Checks if an ingredient is not urgent when the current date
   * is more than a week before the expiry date.
   * Written with copilot.
   */
  @Test
  public void isUrgentWhenCurrentDateIsMoreThanAWeekBeforeExpiryDate() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 15);
    assertFalse(ingredient.isUrgent(LocalDate.of(2023, 1, 1)));
  }

  /**
   * Checks if an ingredient is not urgent when the current date
   * is after the expiry date.
   * Written with copilot.
   */
  @Test
  public void isUrgentWhenCurrentDateIsAfterExpiryDate() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 1);
    assertFalse(ingredient.isUrgent(LocalDate.of(2023, 1, 2)));
  }

  /**
   * Checks if an ingredient is dire when the current date
   * is the day before the expiry date.
   * Written with copilot.
   */
  @Test
  public void isDireWhenCurrentDateIsDayBeforeExpiryDate() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 2);
    assertTrue(ingredient.isDire(LocalDate.of(2023, 1, 1)));
  }

  /**
   * Checks if an ingredient is not dire when the current date
   * is more than a day before the expiry date.
   * Written with copilot.
   */
  @Test
  public void isDireWhenCurrentDateIsMoreThanDayBeforeExpiryDate() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 3);
    assertFalse(ingredient.isDire(LocalDate.of(2023, 1, 1)));
  }

  /**
   * Checks if an ingredient is dire when the current date
   * is the same as the expiry date.
   * Written with copilot.
   */
  @Test
  public void isDireWhenCurrentDateIsSameAsExpiryDate() {
    Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
            12.9, 2023, 1, 1);
    assertTrue(ingredient.isDire(LocalDate.of(2023, 1, 1)));
  }


//-------------------------------------------NEGATIVE TESTS---------------------------

  /**
   * Creates an object with empty name
   * Checks if an exception is caught
   */
  @Test
  public void createInstanceWithInvalidName() {
    try {
      Ingredient ingredient = new Ingredient("", 2, "Stk",
              12.9, 2000, 1, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);

    }
  }

  /**
   * Creates an object with negative amount
   * Checks if an exception is caught
   */
  @Test
  public void createInstanceWithInvalidAmount() {
    try {
      Ingredient ingredient = new Ingredient("Apple", -2, "Stk",
              12.9, 2000, 1, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);

    }
  }

  /**
   * Creates an object with empty unit
   * Checks if an exception is caught
   */
  @Test
  public void createInstanceWithInvalidUnit() {
    try {
      Ingredient ingredient = new Ingredient("Apple", 2, "",
              12.9, 2000, 1, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);

    }
  }

  /**
   * Creates an object with negative price
   * Checks if an exception is caught
   */
  @Test
  public void createInstanceWithInvalidPrice() {
    try {
      Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
              -12.9, 2000, 1, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);

    }
  }

  /**
   * Creates an object with a date that doesn't exist.
   * Checks if an exception is caught.
   */
  @Test
  public void createInstanceWithInvalidDate() {
    try {
      Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
              12.9, 2000, 2, 30);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);

    }
  }

  /**
   * Tries to create an object with valid parameters and change the name to
   * an empty name.
   * Checks if an exception is caught.
   */
  @Test
  public void changeNameWithInvalidNewName() {
    try {
      Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
              12.9, 2000, 1, 1);
      ingredient.setName("");
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  /**
   * Tries to create an object with valid parameters and change the amount to
   * a negative amount.
   * Checks if an exception is caught.
   */
  @Test
  public void changeAmountWithInvalidNewAmount() {
    try {
      Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
              12.9, 2000, 1, 1);
      ingredient.setAmount(-2);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  /**
   * Tries to create an object with valid parameters and change the unit to
   * an empty unit.
   * Checks if an exception is caught.
   */
  @Test
  public void changeUnitWithInvalidNewUnit() {
    try {
      Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
              12.9, 2000, 1, 1);
      ingredient.setUnit("");
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  /**
   * Tries to create an object with valid parameters and change the price to
   * a negative amount.
   * Checks if an exception is caught.
   */
  @Test
  public void changePriceWithInvalidNewPrice() {
    try {
      Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
              12.9, 2000, 1, 1);
      ingredient.setPrice(-3);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  /**
   * Tries to create an object with valid parameters and change the
   * date to a date that doesn't exist.
   * Checks to see if an exception is caught.
   */
  @Test
  public void setNexExpiryDateWithIllegalDate() {
    try {
      Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
              12.9, 2000, 2, 25);
      ingredient.setExpiryDate(2000, 2, 30);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  /**
   * Tries to create an object with valid parameters and change the year of
   * date to negative.
   * Checks to see if an exception is caught.
   */
  @Test
  public void setNewExpiryDateWithNegativeYear() {
    try {
      Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
              12.9, 2000, 2, 25);
      ingredient.setExpiryDate(-2000, 1, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  /**
   * Tries to reduce the amount of the object with a bigger reduced amount
   * than the initial amount.
   * Checks if an exception is caught.
   */
  @Test
  public void reduceWithMoreAmountThanIsInTheIngredientAmount() {
    try {
      Ingredient ingredient = new Ingredient("Apple", 2, "Stk",
              12.9, 2000, 1, 1);
      ingredient.reduceAmount(5);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  /**
   * Tries to add a negative amount to the objects initial amount.
   * Checks if an exception is caught.
   */
  @Test
  public void addInvalidAmount() {
    try {
      Ingredient ingredient = new Ingredient("Apple", 3, "Stk",
              12.9, 2000, 1, 1);
      ingredient.addAmount(-5);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }


}

