package service;

import java.util.ArrayList;
import java.util.List;    
import model.Bookings;
import model.Flight;
import model.User;
import util.*;

public class ReservationSystem {
    private final List <Flight> flights;
    private final List <Bookings> bookings;

    public ReservationSystem(){
        this.flights = new ArrayList<>();
        this.bookings = new ArrayList<>();

        //int flightId, String airlineName, String source, String destination, String date, String time, int seatsAvailable, double price
        flights.add(new Flight(101, "Indigo", "Chennai", "Delhi", "2025-10-05", "08:00", 20, 4500.0, IDGenerator.generateFlightId()));
        flights.add(new Flight(102, "Air India", "Chennai", "Mumbai", "2025-10-06", "14:00", 15, 5000.0, IDGenerator.generateFlightId()));
        flights.add(new Flight(103, "SpiceJet", "Delhi", "Bangalore", "2025-10-07", "09:30", 10, 6000.0, IDGenerator.generateFlightId()));
        flights.add(new Flight(104, "Vistara", "Mumbai", "Chennai", "2025-10-08", "16:00", 25, 5500.0, IDGenerator.generateFlightId()));

    }


    //Search Flights
    public void searchFlight(String source, String destination){
        System.out.println("\nAvailable Flights : ");
        boolean found = false;
        for(Flight f : flights){
            if(f.getSource().equalsIgnoreCase(source) && f.getDestination().equalsIgnoreCase(destination)){
                f.displayFlightInfo();
                found = true;
            }
        }
        if(!found){
            System.out.println("\nNo flights found.....!!!!!!!!");
        }
    }

    //Book Flight
    public Bookings bookFlight(User user, int flightId, String passengerName){
        for(Flight f : flights){
            int seatNo = f.getSeatsAvailable();
            if(f.getFlightId() == flightId){
                if(f.getSeatsAvailable() > 0){
                    f.setSeatsAvailable(f.getSeatsAvailable() - 1);   // Reduce available seats
                    //int bookingID, User user, Flight flight, String passengerName, int seatNo, String status, int id
                    Bookings booking = new Bookings(IDGenerator.generateBookingId(), user, f, passengerName, seatNo, "Booked", IDGenerator.generateBookingId());

                    bookings.add(booking);
                    
                    System.out.println("\nBooking Successful....!!");
                    return booking;

                }

                else {
                    System.out.println("\nNo seats available....!!");
                    return null;
                }
            }
        }
        System.out.println("\nFlight not found....!!");
        return null;
    }

    //Cancel Booking
    public boolean cancelBooking(int bookingId, User user){
        for(Bookings b: bookings){
            if(b.getBookingID() == bookingId && b.getUser().equals(user)){

                //Free the seat in the flight
                Flight f  = b. getFlight();  //Getting flight info. 
                f.setSeatsAvailable(f.getSeatsAvailable() + 1);   // Increase available seats
                b.setStatus("Cancelled");
               
                return true;

            }
        }
        System.out.println("\nBooking not found or you are not authorized to cancel this booking....!!");
        return false;
    }

    //View Bookings History
    public void viewBookings(User user){
        boolean found = false;
        for(Bookings b : bookings){
            if(!b.getUser().equals(user)){
            } else {
                b.displayBookingInfo();
                found = true;
            }
        }
        if(!found){
            System.out.println("\nNo bookings found....!!");
        }
    }
}