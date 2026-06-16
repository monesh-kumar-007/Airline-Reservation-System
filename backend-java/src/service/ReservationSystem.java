package service;

import java.sql.*;
import model.*;
import util.*;

public class ReservationSystem {

        // Search Flights
        public void searchFlight(String source, String destination) {
            try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM FLIGHTS WHERE source = ? AND destination = ?")) {

                stmt.setString(1, source);
                stmt.setString(2, destination);
                ResultSet rs = stmt.executeQuery();

                boolean found = false;
                System.out.println("\nAvailable Flights:\n");

                while (rs.next()) {
                    found = true;
                    System.out.println("Flight ID       : " + rs.getInt("flight_id"));
                    System.out.println("Airline         : " + rs.getString("airline"));
                    System.out.println("Departure Time  : " + rs.getString("departure_time"));
                    System.out.println("Seats Available : " + rs.getInt("seats_available"));
                    System.out.println("Price           : ₹" + rs.getInt("price"));
                    System.out.println("Date            : " + rs.getString("flight_date"));
                    System.out.println("----------------------------------------");
                }

                if (!found) {
                    System.out.println("No flights found.....!!!!!!!!");
                }

            } catch (SQLException e) {
                System.out.println("❌ Error fetching flights: " + e.getMessage());
            }
        }

        // Book Flight
        public Bookings bookFlight(int flightId, String passengerName, User user) {
            try (Connection conn = DBUtil.getConnection()) {

                // Step 1: Fetch flight details
                Flight flight = null;
                String checkSql = "SELECT * FROM FLIGHTS WHERE flight_id = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                    checkStmt.setInt(1, flightId);
                    ResultSet rs = checkStmt.executeQuery();

                    if (!rs.next()) {
                        System.out.println("❌ Flight ID not found.");
                        return null;
                    }

                    flight = new Flight(
                        rs.getInt("flight_id"),
                        rs.getString("airline"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getString("flight_date"),
                        rs.getString("departure_time"),
                        rs.getInt("seats_available"),
                        rs.getDouble("price"),
                        rs.getInt("flight_id")
                    );
                }

                // Step 2: Insert booking
                int bookingId = IDGenerator.generateBookingId();
                int seatNo = flight.getSeatsAvailable(); 

                if (flight.getSeatsAvailable() <= 0) {
                    System.out.println("❌ Booking failed. This flight is fully booked!");
                    return null;
                }

                String insertSql = "INSERT INTO BOOKINGS (booking_id, flight_id, passenger_name, user_id, seat_no, status) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, bookingId);
                    insertStmt.setInt(2, flightId);
                    insertStmt.setString(3, passengerName);
                    insertStmt.setInt(4, user.getId());
                    insertStmt.setInt(5, seatNo); // placeholder seat number
                    insertStmt.setString(6, "Confirmed");

                    int rows = insertStmt.executeUpdate();

                    if (rows > 0) {
                        // Optional: decrement seat count
                        String updateSeats = "UPDATE FLIGHTS SET seats_available = seats_available - 1 WHERE flight_id = ?";
                        try (PreparedStatement seatStmt = conn.prepareStatement(updateSeats)) {
                            seatStmt.setInt(1, flightId);
                            seatStmt.executeUpdate();
                        }

                        

                        return new Bookings(
                            bookingId,
                            user,
                            flight,
                            passengerName,
                            seatNo,
                            "Confirmed",
                            flightId
                        );
                    } else {
                        System.out.println("❌ Booking failed. Please try again.");
                    }
                }

            } catch (SQLException e) {
                System.out.println("❌ Booking failed: " + e.getMessage());
            }

            return null;
        }

        // Cancel Booking
        public boolean cancelBooking(int bookingId, User user) {
            try (Connection conn = DBUtil.getConnection()) {
                String selectQuery = "SELECT * FROM BOOKINGS WHERE booking_id = ? AND user_id = ?";
                PreparedStatement stmt = conn.prepareStatement(selectQuery);
                stmt.setInt(1, bookingId);
                stmt.setInt(2, user.getId());
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String status = rs.getString("status");
                    int flightId = rs.getInt("flight_id");

                    if("Cancelled".equalsIgnoreCase(status)){
                        System.out.println("\nBooking is already cancelled....!!");
                        return false;
                    }

                    String updateBooking = "UPDATE BOOKINGS SET status = 'Cancelled' WHERE booking_id = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateBooking);
                    updateStmt.setInt(1, bookingId);
                    updateStmt.executeUpdate();

                    String updateFlight = "UPDATE FLIGHTS SET seats_available = seats_available + 1 WHERE flight_id = ?";
                    PreparedStatement flightStmt = conn.prepareStatement(updateFlight);
                    flightStmt.setInt(1, flightId);
                    flightStmt.executeUpdate();

                    return true;
                }

            } catch (SQLException e) {
                System.out.println("❌ Cancellation failed: " + e.getMessage());
            }

            System.out.println("\nBooking not found or you are not authorized to cancel this booking....!!");
            return false;
        }

        // View Bookings
        public void viewBookings(User user) {
            boolean found = false;

            try (Connection conn = DBUtil.getConnection()) {
                String query = """
                    SELECT B.*, F.flight_id, F.airline, F.source, F.destination, F.flight_date, F.departure_time,
                        F.price, F.seats_available
                    FROM BOOKINGS B
                    JOIN FLIGHTS F ON B.flight_id = F.flight_id
                    WHERE B.user_id = ?
                """;

                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, user.getId());
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Flight flight = new Flight(
                        rs.getInt("flight_id"),
                        rs.getString("airline"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getString("flight_date"),
                        rs.getString("departure_time"),
                        rs.getInt("seats_available"),
                        rs.getDouble("price"),
                        rs.getInt("flight_id")
                    );

                    Bookings booking = new Bookings(
                        rs.getInt("booking_id"),
                        user,
                        flight,
                        rs.getString("passenger_name"),
                        rs.getInt("seat_no"),
                        rs.getString("status"),
                        rs.getInt("flight_id")
                    );

                    booking.displayBookingInfo();
                    found = true;
                }

            } catch (SQLException e) {
                System.out.println("❌ Error viewing bookings: " + e.getMessage());
            }

            if (!found) {
                System.out.println("\nNo bookings found....!!");
            }
        }
}