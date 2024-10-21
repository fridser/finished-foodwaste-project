import java.util.Calendar;
import java.util.Date;

/**
 * keeps information of the name amount and expiry date of foods
 * is able to check each of these fields
 * is able to change these fields
 * v.09.10.2024
 * Eriksen F. S.
 */



public class Ingredient {
    private String name;
    private Calendar calendar = Calendar.getInstance();
    private Date expiryDate;
    private float amount;
    private String unit;
    private double price;

    /**
     * Creates a new Ingredient object with fields name, expiration date, amount, unit and
     * pricePerUnit.
     */
    public Ingredient(String name,float amount, String unit, double pricePerUnit, int expiryYear, int expiryMonth, int expiryDay){

        if ((name.isBlank())||(name == null)){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if ((amount < 0)||(amount == 0)){
            throw new IllegalArgumentException("Illegal amount");
        }
        if ((unit.isBlank())||(unit == null)){
            throw new IllegalArgumentException("Unit cannot be empty");
        }
        if ((pricePerUnit < 0)||(pricePerUnit == 0)) {
            throw new IllegalArgumentException("Illegal pricePerUnit");
        }

        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.price = pricePerUnit;

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
     */
    public String getName(){
        return this.name;
    }

    /**
     * Changes the name of the object.
     */
    public void setName(String newName){
        this.name = newName;
    }

    /**
     * returns the expiration date of the object
     * @return
     */
    public Date getExpiryDate(){
        return expiryDate;
    }




}
