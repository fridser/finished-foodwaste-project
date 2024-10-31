package edu.ntnu.iir.bidata.fridser.data;

import java.util.Calendar;
import java.util.Date;

/**
 * Keeps information of the name, amount, price and expiry date of different ingredients.
 * Is able to check each of these fields
 * Is able to change these fields
 * v.09.10.2024
 * Eriksen F. S.
 */



public class Ingredient {
    private String name;
    private Calendar calendar = Calendar.getInstance();
    private Date expiryDate;
    private double amount;
    private String unit; //grams, liters or stk
    private double price; //price per unit

    /**
     * Creates a new edu.ntnu.iir.bidata.fridser.data.Ingredient object with fields name, expiration date,
     * amount, unit and pricePerUnit.
     * @param name Name of the object
     * @param amount Amount of the object
     * @param unit Grams, liters of stk
     * @param pricePerUnit Price of the object per unit
     */
    public Ingredient(String name,double amount, String unit, double pricePerUnit, int expiryYear, int expiryMonth, int expiryDay){
       setName(name);
       setAmount(amount);
       setUnit(unit);
       setPrice(pricePerUnit);

        calendar.set(Calendar.YEAR, expiryYear);
        calendar.set(Calendar.MONTH, expiryMonth);
        calendar.set(Calendar.DAY_OF_MONTH, expiryDay);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        expiryDate = calendar.getTime();
    }

    /**
     * Return the name of the ingredient.
     *
     * @return name of the ingredient
     */
    public String getName(){
        return this.name;
    }

    /**
     * Changes the name of the object.
     *
     * @param newName New name of ingredient
     */
    public void setName(String newName){
        if ((newName.isBlank())||(newName == null)) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = newName;
    }

    /**
     * Returns the expiration date of the object
     *
     * @return The expiration date of the object
     */
    public Date getExpiryDate(){
        return expiryDate;
    }

    /**
     * Returns the amount of the object
     * @return amount of the object
     */
    public double getAmount(){
        return amount;
    }

    /**
     * Changes the amount of the object
     * @param newAmount The new amount of the object
     */
    public void setAmount(double newAmount){
        if ((amount < 0)||(amount == 0)) {
            throw new IllegalArgumentException("Illegal amount");
        }
        this.amount = newAmount;
    }


    /**
     * Returns the unit of the object
     * @return the unit of the object
     */
    public String getUnit(){
        return unit;
    }

    /**
     * Changes the unit of the object.
     * @param newUnit Grams, liters of stk
     */
    public void setUnit(String newUnit){
        if ((newUnit.isBlank())||(newUnit == null)) {
            throw new IllegalArgumentException("NewUnit cannot be empty");
        }
        this.unit = newUnit;
    }

    /**
     * Returns the price of the object.
     * @return the price of the object
     */
     public double getPrice(){
        return price;
     }

    /**
     * Changes the price of the object.
     * @param newPrice
     */
     public void setPrice(double newPrice){
        if ((newPrice < 0)||(newPrice == 0)) {
            throw new IllegalArgumentException("Illegal newPrice");
        }
        this.price = newPrice;
    }

    /**
     * Subtracts a specified amount from the amount.
     * @param subtractedAmount
     */
    public void reduceAmount(int subtractedAmount){
        if ((subtractedAmount < 0)) {
            throw new IllegalArgumentException("Subtracted amount cannot be less than zero");
        }
        if ((subtractedAmount > this.amount)) {
            throw new IllegalArgumentException("Subtracted amount cannot be more than amount");
        }
        if ((subtractedAmount == 0)) {
            throw new IllegalArgumentException("Subtracted amount cannot be zero");
        }
        this.amount -= subtractedAmount;
    }

    /**
     * Adds a specific amount to amount
     *
     * @param addedAmount specific amount to add
     */
    public void addAmount(double addedAmount){
        if ((addedAmount < 0) || (addedAmount == 0)) {
            throw new IllegalArgumentException("Illegal added amount");
        }
        this.amount += addedAmount;
    }




}
