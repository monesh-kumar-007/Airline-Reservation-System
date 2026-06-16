package service;

import java.sql.*;
import model.User;
import util.*;

public class AuthService {

    // Signup
    public User signUp(String name, String email, String password) {
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

        try (Connection conn = DBUtil.getConnection()) {
            // Check if email already exists
            String checkQuery = "SELECT * FROM USERS WHERE email = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                System.out.println("\n❌ Email already registered.");
                return null;
            }

            // ✅ Generate user ID
            int userId = IDGenerator.generateUserId();

            // ✅ Insert new user
            String insertQuery = "INSERT INTO USERS (id, username, email, password, role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setInt(1, userId);
            insertStmt.setString(2, name);
            insertStmt.setString(3, email);
            insertStmt.setString(4, password);
            insertStmt.setString(5, "User");

            insertStmt.executeUpdate();
            System.out.println("\n✅ Signup successful for " + name);

            // ✅ Return User object with correct ID type
            return new User(name, email, password, "User", userId);

        } catch (SQLException e) {
            System.out.println("❌ Database error during signup: " + e.getMessage());
            return null;
        }
    }

    // Login
    public User login(String email, String password) {
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
             "SELECT * FROM USERS WHERE email = ? AND password = ?")) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getString("role"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getInt("id")
                );

            } else {
                System.out.println("❌ Invalid email or password.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("❌ Database error during login: " + e.getMessage());
            return null;
        }
    }
}