

import ui.MainMenu;

/**
 * Entry point of the Airline Reservation System.
 * Starts the application by launching the Main Menu.
 */

public class AirlineReservationApp {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.displayMenu();   // Start the main menu loop
    }
}

//       javac -d bin -cp "lib/ojdbc11.jar" src/*.java src/model/*.java src/service/*.java src/ui/*.java src/util/*.java
//       java -cp "bin;lib/ojdbc11.jar" AirlineReservationApp
   