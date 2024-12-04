package edu.ntnu.iir.bidata.fridser.data;

import java.time.DateTimeException;
import java.time.LocalDate;


/**
 * Represents a food item.
 *
 * <ul>
 *   <li>Keeps information of the name,
 *   amount, unit, price and expiry date of different ingredients.</li>
 *   <li>Is able to check each of these fields.</li>
 *   <li>Is able to change these fields.</li>
 *   <li> Is able to increase or reduce the amount field.</li>
 *   <li>is able to compare the expiration date of the object to another date </li>
 *   <li>and see if the object is expired, expires within two days or expires within
 *   a week.</li>
 *   <li>can compare the object to another object, is considered the same if the name,
 *   unit and expiration date are the same.</li>
 * </ul>
 *
 */


public class Ingredient {
  private String ingredientName;
  private LocalDate expiryDate;
  private double amount;
  private String unit; //grams, litres or stk
  private double price; //price per unit

  /**
   * Creates a new edu.ntnu.iir.bidata.fridser.data.Ingredient object with fields name,
   * expiration date,amount, unit and pricePerUnit.
   *
   * @param name         Name of the object
   * @param amount       Amount of the object
   * @param unit         Grams, liters of stk
   * @param pricePerUnit Price of the object per unit
   */
  public Ingredient(String name, double amount, String unit, double pricePerUnit,
                    int expiryYear, int expiryMonth, int expiryDay) {
    setIngredientName(name);
    setAmount(amount);
    setUnit(unit);
    setPrice(pricePerUnit);
    setExpiryDate(expiryYear, expiryMonth, expiryDay);

  }

  /**
   * The constructor used in recipes.
   *
   * @param name   The name of the ingredient
   * @param amount The amount of the ingredient
   * @param unit   Either Grams, Liters or Stk
   */
  public Ingredient(String name, double amount, String unit) {
    setIngredientName(name);
    setAmount(amount);
    setUnit(unit);
    setPrice(1);
    setExpiryDate(2000, 1, 1);
  }

  /**
   * Return the name of the ingredient.
   *
   * @return name of the ingredient
   */
  public String getIngredientName() {
    return this.ingredientName;
  }

  /**
   * Changes the name of the object.
   *
   * @param newName New name of ingredient
   */
  public void setIngredientName(String newName) {
    if ((newName.isBlank()) || (newName == null) || (newName.isEmpty())) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    this.ingredientName = newName;
  }

  /**
   * Returns the expiration date of the object.
   *
   * @return The expiration date of the object
   */
  public LocalDate getExpiryDate() {
    return expiryDate;
  }

  /**
   * Returns the amount of the object.
   *
   * @return amount of the object
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Changes the amount of the object.
   *
   * @param newAmount The new amount of the object.
   */
  public void setAmount(double newAmount) {
    if ((newAmount < 0) || (newAmount == 0)) {
      throw new IllegalArgumentException("Illegal amount");
    }
    this.amount = newAmount;
  }

  public void setExpiryDate(int expiryYear, int expiryMonth, int expiryDay) {
    if (expiryYear < 0) {
      throw new IllegalArgumentException("Year cannot be negative");
    }
    try {
      expiryDate = LocalDate.of(expiryYear, expiryMonth, expiryDay);
    } catch (DateTimeException e) {
      throw new IllegalArgumentException("Invalid date");
    }

  }


  /**
   * Returns the unit of the object.
   *
   * @return the unit of the object
   */
  public String getUnit() {
    return unit;
  }

  /**
   * Changes the unit of the object.
   *
   * @param newUnit Grams, Litres or Stk
   */
  public void setUnit(String newUnit) {
    if ((newUnit == "Grams") || (newUnit == "Stk") || (newUnit == "Litres")
            || (newUnit == "ts")) {
      this.unit = newUnit;
    } else {
      throw new IllegalArgumentException("Unit must be either Grams, Stk, Litres or ts");
    }
  }

  /**
   * Returns the price of the object.
   *
   * @return the price of the object
   */
  public double getPrice() {
    return price;
  }

  /**
   * Changes the price of the object.
   *
   * @param newPrice The new price of the ingredient.
   */
  public void setPrice(double newPrice) {
    if ((newPrice < 0) ) {
      throw new IllegalArgumentException("Price cannot be less than zero");
    }
    this.price = newPrice;
  }

  /**
   * Subtracts a specified amount from the amount.
   *
   * @param subtractedAmount
   */
  public void reduceAmount(double subtractedAmount) {
    if ((subtractedAmount < 0)) {
      throw new IllegalArgumentException("Subtracted amount cannot be less than zero");
    }
    if ((subtractedAmount >= this.amount)) {
      throw new IllegalArgumentException("Subtracted amount cannot be more than" +
              "or equal to amount");
    }
    if ((subtractedAmount == 0)) {
      throw new IllegalArgumentException("Subtracted amount cannot be zero");
    }
    this.amount -= subtractedAmount;
  }

  /**
   * Adds a specific amount to amount.
   *
   * @param addedAmount Specific amount to add.
   */
  public void addAmount(double addedAmount) {
    if ((addedAmount < 0) || (addedAmount == 0)) {
      throw new IllegalArgumentException("Illegal added amount");
    }
    this.amount += addedAmount;
  }

  /**
   * Returns true if the ingredient is expired, false if it isn't.
   *
   * @param currentDate The current date the expiration date of the ingredient is being compared to
   * @return boolean, true if the ingredient is expired.
   */
  public boolean isExpired(LocalDate currentDate) {
    boolean expired = false;
    if (currentDate.isAfter(this.expiryDate)) {
      expired = true;
    }
    return expired;
  }

  /**
   * Returns true if the ingredient expires within a week.
   *
   * @param currentDate The current date the ingredient is being compared to.
   * @return boolean, true if the ingredient expires within a week.
   */
  public boolean isUrgent(LocalDate currentDate) {
    boolean urgent = false;
    if (isExpired(currentDate)) {
      urgent = false;
    } else if (currentDate.isAfter(expiryDate.minusDays(7))) {
      urgent = true;
    }
    return urgent;
  }

  /**
   * Returns true if the ingredient expires the next day.
   *
   * @param currentDate The date the ingredient is being compared to.
   * @return boolean, true if the ingredient expires the next day.
   */
  public boolean isDire(LocalDate currentDate) {
    boolean dire = false;
    if (isExpired(currentDate)) {
      dire = false;
    } else if (currentDate.isAfter(expiryDate.minusDays(2))) {
      dire = true;
    }
    return dire;
  }

  /**
   * Checks if another ingredient has the same name, expiry date and unit
   * as this ingredient.
   * If they do it returns true.
   *
   * @param ingredient Ingredient to be compared to
   * @return boolean, True if the ingredients have the same name and expiry date
   */
  public boolean isSame(Ingredient ingredient) {
    if ((ingredient.getIngredientName().equalsIgnoreCase(this.ingredientName)) &&
            (ingredient.getExpiryDate().equals(this.expiryDate)) && (ingredient.getUnit().equals(this.unit))) {
      return true;
    } else {
      return false;
    }
  }


}
