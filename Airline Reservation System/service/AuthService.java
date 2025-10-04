//Package declaration
package service;

// Import statements
import java.util.HashMap;
import java.util.Map;
import model.*;
import util.*;

public class AuthService {
    private final Map<String, User> users = new HashMap<>(); // email → User

    // Signup
    public User signUp(String name, String email, String password) {
        // Validate inputs using InputValidator
        if (!InputValidator.isValidName(name)) {
            System.out.println("\n❌ Invalid name. Only letters and spaces allowed.");
            return null;
        }
        if (!InputValidator.isValidEmail(email)) {
            System.out.println("\n❌ Invalid email format.");
            return null;
        }
        if (!InputValidator.isValidPassword(password)) {
            System.out.println("\n❌ Password must be at least 6 characters and contain a number.");
            return null;
        }
        if (users.containsKey(email)) {
            System.out.println("\n❌ Email already registered.");
            return null;
        }

        // If valid → create new user
        //String username, String email,String password, String role, int id
        User newUser = new User( name, email, password, "User", IDGenerator.generateUserId());
        users.put(email, newUser);
        System.out.println("\n✅ Signup successful for " + name);
        return newUser;
    }

    // Login
    public User login(String email, String password) {
        if (!InputValidator.isValidEmail(email)) {
            System.out.println("\n❌ Invalid email format.");
            return null;
        }

        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("\nLogin successful. Welcome " + user.getUsername());
            return user;
        }

        System.out.println("\n❌ Invalid credentials.");
        return null;
    }
}
