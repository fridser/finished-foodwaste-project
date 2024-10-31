package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.fridser.ui.UserInterface;

/**
 * The main starting point of your application. Let this class create the
 * instance of your main-class that starts your application.
 */
public class Main {

    static UserInterface ui;

    public static void main(String[] args) {
        ui = new UserInterface();
        ui.init();
        ui.start();


    }
}
