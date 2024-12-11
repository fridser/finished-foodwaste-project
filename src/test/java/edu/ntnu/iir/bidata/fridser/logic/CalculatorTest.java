package edu.ntnu.iir.bidata.fridser.logic;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the methods in the Calculator class.
 *
 * <ul>
 *  <li>that the total price of the object is calculated correcly</li>
 *  <li>the cost of a list of ingredients are calculated correctly</li>
 * </ul>
 */
public class CalculatorTest {
  Calculator calc;

  /**
   * Executes before each of the test methods.
   *
   * <p>Creates the instance of the calculator to be used for each test. This way
   * we make sure that every single of the tests starts off with a newly created, empty
   * instance of Calculator.</p>
   */
  @BeforeEach
  void createInstanceOfCalculator() {
    this.calc = new Calculator();
  }

  /**
   * Creates an ingredient with an amount and price per unit.
   * Checks that the calculateTotalPrice returns the correct double.
   */
  @Test
  public void calculateTotalPriceOfAnIngredient() {
    Ingredient ingredient1 = new Ingredient("Apple", 3, "Stk",
            4, 2024, 12, 12);
    assertEquals(12, calc.calculateTotalPrice(ingredient1));
  }

  @Test
  public void calculateCostOfIngredients() {
    Ingredient ingredient1 = new Ingredient("Apple", 3, "Stk",
            2, 2024, 12, 12);
    Ingredient ingredient2 = new Ingredient("Orange", 2, "Stk",
            5, 2025, 3, 12);
    Ingredient ingredient3 = new Ingredient("Milk", 0.5, "Litres",
            10, 2025, 1, 1);

    ArrayList<Ingredient> arr = new ArrayList<>();
    arr.add(ingredient1);
    arr.add(ingredient2);
    arr.add(ingredient3);

    assertEquals(21, calc.calculateCost(arr.iterator()));
  }
}
