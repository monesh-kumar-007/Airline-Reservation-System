package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    // 1. The connection URL using your default Oracle port (1521)
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    
    // 2. Get your database username and paste it
    private static final String USER = "PASTE_YOUR_DATABASE_USERNAME";
    
    // 3. 🔒 SECURE: Reads the password from your local computer's environment settings
    // Before running the application, set your password in the terminal
    // For PowerShell - $env:DB_PASSWORD="system123"
    // For CommandPrompt - set DB_PASSWORD=system123
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("❌ Oracle JDBC Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
