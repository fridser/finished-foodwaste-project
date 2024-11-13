package edu.ntnu.iir.bidata.fridser.ui;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;

import java.sql.SQLOutput;
import java.util.Iterator;

public class UserInterface {

    public void start() {
        Ingredient ingredient = new Ingredient("Apple", 2, "Kg",
                12.9,2000,1,1);
        printDetails(ingredient);

    }

    public void init() {

    }

    /**
     * Prints the information about the ingredient
     *
     * @param ingredient The ingredient for whom the details get printed
     */
    public void printDetails(Ingredient ingredient) {
        System.out.println("Name: " + ingredient.getIngredientName());
        System.out.println("Amount: " + ingredient.getAmount() +" " + ingredient.getUnit());
        System.out.println("Price: " + ingredient.getPrice() + " per " + ingredient.getUnit());
        System.out.println("Expiration date: " + ingredient.getExpiryDate().toString());
    }

    /**
     * Prints the details of each ingredient in an iterator.
     *
     * @param it The iterator containing the ingredients getting their details printed
     */
    public void printAllIngredientDetails(Iterator<Ingredient> it) {
        while (it.hasNext()) {
            printDetails(it.next());
        }
    }

    /**
     * Returns the total cost of all the ingredients in the iterator.
     *
     * @param it The iterator containing the ingredients.
     * @return cost, the total amount of money used to buy the ingredients.
     */
    public double calculateCost(Iterator<Ingredient> it) {
        double cost = 0;
        while (it.hasNext()) {
            cost += it.next().getPrice()*it.next().getAmount();
        }
        return cost;
    }
}
