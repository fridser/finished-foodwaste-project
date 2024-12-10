package edu.ntnu.iir.bidata.fridser.ui;

import java.util.Scanner;

/**
 * Takes input from the user and returns it in a
 * form that the UserInterface can use.
 */
public class InputParser {
  private Scanner input;

  /**
   * Constructor of the InputParser class.
   */
  public InputParser() {
    input = new Scanner(System.in);
  }


  /**
   * Scans inputs that is supposed to be an integer.
   * Returns an integer if the input is an integer, else prints
   * out a message and gives the user another chance to input correctly.
   *
   * @return int, the user input.
   */
  public int getIntInput() {
    int choice = -1;
    boolean finished = false;
    while (!finished) {
      if (input.hasNextInt()) {
        choice = input.nextInt();
        finished = true;
      } else {
        System.out.println("Please enter a positive whole number. \n"
                + "idiot");
      }
      input.nextLine();
    }
    return choice;
  }

  /**
   * Scans inputs that is supposed to be a double.
   * Returns a double if the input is a double, else prints
   * out a message and gives the user another chance to input correctly.
   *
   * @return double, the user input.
   */
  public double getDoubleInput() {
    double choice = -1;
    boolean finished = false;
    while (!finished) {
      if (input.hasNextDouble()) {
        choice = input.nextDouble();
        finished = true;
      } else {
        System.out.println("Please enter a number. ");
      }
      input.nextLine();
    }
    return choice;
  }

  /**
   * Scans inputs that is supposed to be a string.
   * Returns the user input as a String.
   *
   * @return String, the user input.
   */
  public String getStringInput() {
    String choice = null;
    boolean finished = false;
    while (!finished) {
      if (input.hasNextLine()) {
        choice = input.nextLine();
        finished = true;
      } else {
        System.out.println("An unexpected error has occurred. Please be"
                + " better.");
      }
    }
    return choice;
  }
}
