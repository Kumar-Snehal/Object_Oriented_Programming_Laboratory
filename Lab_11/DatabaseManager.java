package Lab_11;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lab11";
    private static final String DB_USER = "KumarSnehal";
    private static final String DB_PASSWORD = "App@123";

    private Connection connection;

    public DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            createTableIfNotExists();
        } catch (Exception e) {
            System.out.println("Database connection failed. Make sure MySQL is running and credentials are correct.");
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS Events (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "status VARCHAR(50))";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int insertEvent(String eventName, String status) {
        String sql = "INSERT INTO Events (name, status) VALUES "
                + "('" + eventName + "', '" + status + "')";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateEventStatus(int id, String newStatus) {
        String sql = "UPDATE Events SET status = '" + newStatus + "' WHERE id = " + id;
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("\n[Database] Updated Event ID " + id + " to status: " + newStatus);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchEventHistory() {
        String sql = "SELECT id, name, status FROM Events";
        System.out.println("\n=== Event History from Database ===");
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Status: %s%n",
                        rs.getInt("id"), rs.getString("name"), rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===================================\n");
    }
}
