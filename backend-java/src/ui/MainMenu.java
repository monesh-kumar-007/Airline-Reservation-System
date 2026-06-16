//Package Declaration
package ui;


//Import Statements
import java.util.Scanner;
import model.User;
import service.AuthService;
import service.ReservationSystem;


public class MainMenu {

    private final AuthService authService;
    private final Scanner sc;
    
    public MainMenu() {
        authService = new AuthService();
        sc = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("------------------------------------------------");
            System.out.println("\n         Airline Reservation System         \n");
            System.out.println("------------------------------------------------\n");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit\n");
            System.out.print("Choose an option: ");
            String input = sc.nextLine();
            int choice;
            
            try {
                choice = Integer.parseInt(input);
            } 
            catch (NumberFormatException e) {
                System.out.println("\n⚠️ Invalid input! Please enter a number (1 <-> 3).");
                continue; // restart loop
            }

            switch (choice) {
                case 1 -> login();
                case 2 -> signUp();
                case 3 -> {
                    System.out.println("\nExiting...");
                    System.exit(0);
                }
                default -> System.out.println("\nInvalid choice.... Please try again....!!");
            }
        }
    }

    public void login() {
        System.out.println("Enter your E-mail✉️ : ");
        String email = sc.nextLine();
        System.out.println("Enter your password🔑 : ");
        String password = sc.nextLine();

        User user = authService.login(email, password);

        if (user != null) {
            System.out.println("\nLogin Successfull....");

            // Returning to Dashboard
            ReservationSystem reservation = new ReservationSystem();
            UserDashboard dashboard = new UserDashboard(user, reservation);
            dashboard.displayDashboard();
        } 
        else {
            System.out.println("\nLogin Failed.... Please try again....!!");
        }
    }

    public void signUp() {
        System.out.println("Enter your Name : ");
        String username = sc.nextLine();
        System.out.println("Enter your E-mail✉️ : ");
        String email = sc.nextLine();
        System.out.println("Create a password🔑 : ");
        String password = sc.nextLine();
        
        User user = authService.signUp(username, email, password);
        if (user != null) {
            System.out.println("\nRegistration Successfull....");
        } 
        else {
            System.out.println("\nRegistration Failed.... Please try again....!!");
        }
    }
}