package model;


public class Bookings {

    private int bookingID;
    private User user;           // Who made the booking
    private Flight flight;       // Flight details
    private String passengerName;
    private int seatNo;
    private String status;       // Booked, Cancelled, Completed
    private int flightDbId;

    // Constructor
    public Bookings(int bookingID, User user, Flight flight, String passengerName, int seatNo, String status, int flightDbId) {
        this.bookingID = bookingID;
        this.user = user;
        this.flight = flight;
        this.passengerName = passengerName;
        this.seatNo = seatNo;
        this.status = status;
        this.flightDbId = flightDbId;
    }

    // Default constructor
    public Bookings() {
    }

    // Getters
    public int getBookingID() {
        return bookingID;
    }

    public User getUser() {
        return user;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public String getStatus() {
        return status;
    }

    public int getFlightDbId() {
        return flightDbId;
    }

    // Setters
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFlightDbId(int flightDbId) {
        this.flightDbId = flightDbId;
    }

    // Display Booking Info
    public void displayBookingInfo() {
        System.out.println("\nBooking ID: " + bookingID);
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Seat Number: " + seatNo);
        System.out.println("Status: " + status);
        System.out.println("\nFlight Details:");

        if (flight != null) {
            flight.displayBookedFlightInfo();
        } else {
            System.out.println("⚠️ Flight details not available.");
        }

        if (user != null) {
            System.out.println("Booked by: " + user.getUsername() + " (" + user.getEmail() + ")");
        } else {
            System.out.println("Booked by: [Guest]");
        }

        System.out.println("-----------------------------------");
    }
}