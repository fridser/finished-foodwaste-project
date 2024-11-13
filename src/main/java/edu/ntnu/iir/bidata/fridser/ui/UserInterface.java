package edu.ntnu.iir.bidata.fridser.ui;

import edu.ntnu.iir.bidata.fridser.data.Ingredient;

import java.sql.SQLOutput;

public class UserInterface {

    public void start() {
        Ingredient ingredient = new Ingredient("Apple", 2, "Kg",
                12.9,2000,1,1);
        printDetails(ingredient);

    }

    public void init() {

    }

    public void printDetails(Ingredient ingredient) {
        System.out.println("Name: " + ingredient.getIngredientName());
        System.out.println("Amount: " + ingredient.getAmount() +" " + ingredient.getUnit());
        System.out.println("Price: " + ingredient.getPrice() + " per " + ingredient.getUnit());
        System.out.println("Expiration date: " + ingredient.getExpiryDate().toString());
    }
}
