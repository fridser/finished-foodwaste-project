import java.util.ArrayList;


/**
 * can add and remove food in a list
 * keeps track of how many different and the amount of food
 * v.14.10.2024
 * Eriksen F. E.
 */


public class FoodStorage {
    private ArrayList<Ingredient> ingredients;

    /**
     * Creates an instance of FoodStorage object
     */
    public FoodStorage(){
        ingredients = new ArrayList<>();
    }

    /**
     * Adds an ingredient to the array list ingredients
     * @param ingredient
     */
    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    /**
     * returns the size of the ingredients list
     * @return int
     */
    public int getNumberOfIngredients(){
        return this.ingredients.size();
    }

    /**
     * returns the ingredient at a specified index
     * @param index
     * @return Ingredient
     */
    public Ingredient getIngredient(int index){
        return this.ingredients.get(index);
    }

    /**
     * Returns the index of a specified Ingredient
     * @param ingredient
     * @return int
     */
    public int getIndexOfIngredient(Ingredient ingredient){
        return this.ingredients.indexOf(ingredient);
    }

    /**
     * returns ingredients with  specified name
     * @param name
     * @return
     */
    public Ingredient findIngredientByName(String name)
    {
        Ingredient foundIngredient = null;

        int index = 0;
        boolean ingredientNotFound = true;

        while ((index < this.ingredients.size()) && ingredientNotFound )
        {
            Ingredient ingredient = this.ingredients.get(index);


            if (ingredient.getName().equals(name))
            {
                ingredientNotFound = false;
                foundIngredient = ingredient;
            }
            index++;
        }


        return foundIngredient;
    }

}

