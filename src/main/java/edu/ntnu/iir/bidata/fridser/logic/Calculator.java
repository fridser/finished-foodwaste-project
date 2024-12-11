package edu.ntnu.iir.bidata.fridser.logic;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;

import java.util.Iterator;

/**
 * Handles the calculations made with the prices of the ingredients.
 */
public class Calculator {

  /**
   * The constructor of the Calculator class:
   */
  public Calculator() {

  }

  /**
   * Calculates the cost of the given ingredients.
   *
   * @param it The iterator containing the ingredients whose cost
   *           is calculated
   * @return double, the cost of the ingredients.
   */
  public double calculateCost(Iterator<Ingredient> it) {
    double cost = 0;
    while (it.hasNext()) {
      cost += calculateTotalPrice(it.next());
    }
    return cost;
  }

  /**
   * Calculates the total price of the given ingredient.
   *
   * @param ingredient The ingredients whose total price is being calculated.
   * @return double, the total price of the ingredient.
   */
  public double calculateTotalPrice(Ingredient ingredient) {
    double totalPrice = ingredient.getPrice() * ingredient.getAmount();
    return totalPrice;
  }
}
