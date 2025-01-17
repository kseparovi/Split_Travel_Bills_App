package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect() {
        // Replace these variables with your actual database credentials
        String url = "jdbc:mysql://db4free.net:3306/kristina_sep";  // Ensure database exists

        String username = "kristina_sep";  // your username for db4free
        String password = "Amoric456!";  // your password for db4free

        try {
            // Load MySQL JDBC driver (optional in newer versions of JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
            return connection;

        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        }

        return null;  // If connection fails
    }

    // This main method allows you to run this class directly
    public static void main(String[] args) {
        connect();  // Try connecting to the database
    }

    public static Connection getConnection() {
        return connect();
    }
}
