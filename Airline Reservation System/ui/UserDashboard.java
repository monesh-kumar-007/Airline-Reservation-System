package ui;


// Import statements
import java.util.Scanner;
import model.Bookings;
import model.User;
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
            int choice = sc.nextInt();
            sc.nextLine();


            switch(choice){
                case 1 -> searchFlight();
                case 2 -> bookFlight(user);
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
        System.out.println("\nEnter Source : ");
        String src = sc.nextLine();
        System.out.println("Enter Destination : ");
        String dest = sc.nextLine();

        reservation.searchFlight(src, dest);
    }

    private void bookFlight(User user) {
        System.out.println("\nEnter Flight ID to book : ");
        int flightId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Passenger Name : ");
        String passengerName = sc.nextLine();

        Bookings booking = reservation.bookFlight(user, flightId, passengerName);

        if (booking != null) {
            System.out.println("\nBooking Successful ....!! ✅");
            booking.displayBookingInfo();
        } 
        else {
            System.out.println("\nBooking Failed ....!! ❌");
        }
    }


    private void cancelBooking(User user){
        System.out.println("\nEnter Booking ID to cancel : ");
        int bookingId = sc.nextInt();
        sc.nextLine();

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