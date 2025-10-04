package model;

public class Flight {
    private int flightId;
    private String airlineName;
    private String source;
    private String destination;
    private String date;
    private String time;
    private int seatsAvailable;
    private double price;
    private final int id;
  

    // Constructor
    public Flight(int flightId, String airlineName, String source, String destination, String date, String time, 
                                                                        int seatsAvailable, double price, int id) {
        this.flightId = flightId;
        this.airlineName = airlineName;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.seatsAvailable = seatsAvailable;
        this.price = price;
        this.id = id;
       
    }

    // Getters & Setters
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public void displayFlightInfo() {
        System.out.println("\nFlight ID: " + flightId);
        System.out.println("Airline: " + airlineName);
        System.out.println("From: " + source + " To: " + destination);
        System.out.println("Date: " + date + " | Time: " + time);
        System.out.println("ID: " + id);
        System.out.println("Seats Available: " + seatsAvailable);
        System.out.println("Price: ₹" + price);
        System.out.println("-----------------------------------");
    }

}
    
