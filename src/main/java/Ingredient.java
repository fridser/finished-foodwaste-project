/**
 * keeps information of the name amount and expiry date of foods
 * is able to check each of these fields
 * is able to change these fields
 * v.07.10.2024
 * Eriksen F. S.
 */



public class Ingredient {
    private String name;

    /**
     * Creates a new Ingredient object with field name.
     */
    public Ingredient(String name){
        if (name.isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }

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


}
