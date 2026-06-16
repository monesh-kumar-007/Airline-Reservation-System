package ui;

// Import statements
import java.util.Scanner;
import model.*;
import service.ReservationSystem;


public class UserDashboard{
    private final User user;
    private final  ReservationSystem reservation;
    private final Scanner sc;

    public UserDashboard(User user, ReservationSystem reservation) {
        this.user = user;
        this.reservation = reservation;
        this.sc = new Scanner(System.in);
    }

    public void displayDashboard() {
        while(true) {
            System.out.println("\n--------- User Dashboard --------\n");
            System.out.println("1. View Available Flights");
            System.out.println("2. Book a Flight");
            System.out.println("3. View My Bookings");
            System.out.println("4. Cancel a Booking");
            System.out.println("5. Logout");
            System.out.print("\nChoose an option: ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number between 1 and 5.");
                continue; // go back to the menu
            }


            switch(choice){
                case 1 -> searchFlight();
                case 2 -> {
                    try {
                        bookFlight(user);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                case 3 -> viewBookings(user);
                case 4 -> cancelBooking(user);
                case 5 -> {
                    System.out.println("\nLogging out...");
                    return; // Exit the dashboard
                }
                default -> System.out.println("\nInvalid choice.... Please try again....!!");
            }
        }
    }

    private void searchFlight() {
        String src = null;
        String dest = null;
        try {
            System.out.println("\nEnter Source : ");
            src = sc.nextLine();
            System.out.println("Enter Destination : ");
            dest = sc.nextLine();

            if(src.isEmpty() || dest.isEmpty() || src.isBlank() || dest.isBlank()) {
                System.out.println("\nSource and Destination cannot be empty. Please try again.");
                return;
            }   
            else if (src.equalsIgnoreCase(dest)) {
                System.out.println("\nSource and Destination cannot be the same. Please try again.");
                return;
            }
            else if((src + dest).matches("^[0-9]+$")) {
                System.out.println("\nSource and Destination cannot be numeric. Please try again.");
                return;
            }
            else if(!src.matches("^[a-zA-Z\\s.-]+$") || !dest.matches("^[a-zA-Z\\s.-]+$")) {
                System.out.println("\nSource and Destination must contain only letters, spaces, dots, or hyphens. Please try again.");
                return;
            }
            else {
                src = src.trim();
                dest = dest.trim();
            }

        } catch (Exception e) {
            System.out.println("Enter valid locations.. Please try again");
        }

        reservation.searchFlight(src, dest);
    }

    private void bookFlight(User user) throws InterruptedException {
        int flightId;
        String passengerName;
        try {
            System.out.println("\nEnter Flight ID to book : ");
            flightId = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Passenger Name : ");
            passengerName = sc.nextLine();
        } catch (Exception e) {
            System.out.println("\nInvalid format. Please try again.");
            sc.nextLine(); // clear the buffer
            return;
        }

        Bookings booking = reservation.bookFlight(flightId, passengerName, user);

        if (booking != null) {
            System.out.print("\nBooking");

            try {
                for (int i = 0; i < 10; i++) {
                System.out.print("-");
                Thread.sleep(400);

                }
                System.out.print("> Booked ✔️");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Thread.sleep(500);
            System.out.println("\nBooked Successful ....!! ✅");

            // ✅ Show booking details first
            booking.displayBookingInfo();
            System.out.println("\nBooking ID : " + booking.getBookingID());

            // ✅ Then print final success message
            System.out.println("\n✅ Booked Flight Ticket Successful for " + passengerName + " on flight " + flightId);
        } 
        else {
            System.out.println("\nBooking Failed ....!! ❌");
        }
    }


    private void cancelBooking(User user){
        System.out.println("\nEnter Booking ID to cancel : ");
        int bookingId;
        
        try {
            bookingId = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("\nInvalid format. Please try again.");
            sc.nextLine(); // clear the buffer
            return;
        }

        boolean cancelled = reservation.cancelBooking(bookingId, user);
        
        if(cancelled){
            System.out.println("\nBooking Cancelled✅....!!");
        } else {
            System.out.println("\nCancellation Failed❌....!!");
        }
    }

    private void viewBookings(User user){
        reservation.viewBookings(user);
    }
}