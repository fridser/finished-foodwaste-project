import java.util.ArrayList;


/**
 * can add and remove food in a list
 * keeps track of how many different and the amount of food
 * v.09.10.2024
 * Eriksen F. E.
 */


public class FoodStorage {
    private ArrayList<Ingredient> ingredients;

    public FoodStorage(){
        ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

}

