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

    /**
     * Creates a new Ingredient object with fields name and expiration date.
     */
    public Ingredient(String name, int expiryYear, int expiryMonth, int expiryDay){
        if (name.isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }
        calendar.set(Calendar.YEAR, expiryYear);
        calendar.set(Calendar.MONTH, expiryMonth);
        calendar.set(Calendar.DAY_OF_MONTH, expiryDay);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        expiryDate = calendar.getTime();

        this.name = name;


    }

    /**
     * Returnd the name of the ingredient.
     */
    public String getName(){
        return name;
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
