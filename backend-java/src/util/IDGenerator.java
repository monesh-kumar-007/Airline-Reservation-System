package util;

import java.util.Random;

public class IDGenerator {
    private static final Random random = new Random();

        // Generates random ID for user, flight, booking.................

        
        // ID - (1000  <-> 9999)
    public static int generateUserId() {
        return 1000 + random.nextInt(9000);
    }

    // ID - (2000  <-> 9999)
    public static int generateBookingId() {
       return 5000 + random.nextInt(5000);
    }
}
