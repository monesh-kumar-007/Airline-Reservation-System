package model;


//Import Statements


public class Bookings{

    private int bookingID;
    private User user;      //who made the booking
    private Flight flight;  //flight details
    private String passengerName;
    private int seatNo;
    private String status; //Booked, Cancelled, Completed
    private int id;

    public Bookings(int bookingID, User user, Flight flight, String passengerName, int seatNo, String status, int id) {
        this.bookingID = bookingID;
        this.user = user;
        this.flight = flight;
        this.passengerName = passengerName;
        this.seatNo = seatNo;
        this.status = status;
        this.id = id;   
    }
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

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }
    
    //Display Booking Info
    public void displayBookingInfo() {
        System.out.println("\nBooking ID: " + bookingID);
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Seat Number: " + seatNo);
        System.out.println("Status: " + status);
        System.out.println("\nFlight Details:");
        flight.displayFlightInfo();
        System.out.println("Booked by: " + user.getUsername() + " (" + user.getEmail() + ")");
        System.out.println("-----------------------------------");
    }
    
}