package edu.ntnu.iir.bidata.fridser.logic;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Converts iterators to ArrayLists.
 */
public class IteratorConverter {

  /**
   * The constructor of the IteratorConverter class.
   */
  public IteratorConverter() {

  }

  /**
   * Converts an iterator of ingredients into an arraylist. Necessary for
   * the useRecipe method in the FoodStorage to work.
   *
   * @param it The iterator getting converted into an arraylist.
   * @return ArrayList, The converted arraylist.
   */
  public ArrayList<Ingredient> turnIteratorIntoArrayList(Iterator<Ingredient> it) {
    ArrayList<Ingredient> arrayList = new ArrayList<>();
    while (it.hasNext()) {
      arrayList.add(it.next());
    }
    return arrayList;
  }
}
