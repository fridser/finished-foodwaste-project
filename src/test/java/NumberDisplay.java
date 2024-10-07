/**
 * Represents a two digit display of a positive integer number
 * the number displayed has a range from 0 up to a defined limit
 *
 * Number display should be possible to tick/increase by 1
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NumberDisplay
{
    private int currentValue;
    private int limit;

    /**
     * Create object NumberDisplay
     * @param limit
     */
    public NumberDisplay(int limit)
    {
        if (limit <= 0){
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        this.limit = limit;
        currentValue = 0;
    }

    /**
     * Returns the current value of the numberdisplay
     * @return
     */
    public int getCurrentValue()
    {
        return this.currentValue;
    }

    /**
     * Returns the current value as a string on the form 03
     * In other words the current value is added with a "0"
     * if the int is less than 10
     */
    public String displayValue()
    {
        if (currentValue < 10)
        {
            return "0" + this.currentValue;
        }
        else
        {
            return "" + this.currentValue;
        }
    }

    /**
     * increments the value of the number display by one
     */
    public void increment()
    {

        //new
        this.currentValue = (this.currentValue + 1) % this.limit;
    }
}