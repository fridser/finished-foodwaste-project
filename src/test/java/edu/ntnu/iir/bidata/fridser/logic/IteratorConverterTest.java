package edu.ntnu.iir.bidata.fridser.logic;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the class edu.ntnu.iir.bidata.fridser.logic.IteratorConverter.
 * Checks if the method for turning an iterator into a
 */
public class IteratorConverterTest {

  @Test
  public void createArrayListFromIteratorWithMultipleIngredients() {
    ArrayList<Ingredient> oldList = new ArrayList<>();

    Ingredient ingredient1 = new Ingredient("Apple", 3, "Stk",
            12.3, 2024, 12, 12);
    Ingredient ingredient2 = new Ingredient("Orange", 5, "Stk",
            15.2, 2025, 3, 12);
    Ingredient ingredient3 = new Ingredient("Milk", 0.5, "Litres",
            25.9, 2025, 1, 1);

    oldList.add(ingredient1);
    oldList.add(ingredient2);
    oldList.add(ingredient3);

    Iterator<Ingredient> it = oldList.iterator();
    IteratorConverter ic = new IteratorConverter();
    ArrayList<Ingredient> newList = ic.turnIteratorIntoArrayList(it);

    assertEquals(oldList, newList);
  }
}
