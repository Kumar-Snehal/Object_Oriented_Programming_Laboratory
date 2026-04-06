package Lab_10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/db_name";
        String username = "appuser"; // change if needed
        String password = "App@123"; // use your password
        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully!");

            Statement stmt = con.createStatement();
            // 1. Create Table
            String createTable = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(50), " +
                    "marks INT)";
            stmt.executeUpdate(createTable);
            System.out.println("Table created!");
            // 2. Insert 2 Rows
            stmt.executeUpdate("INSERT INTO students(name, marks) VALUES('Martin', 85)");
            stmt.executeUpdate("INSERT INTO students(name, marks) VALUES('John', 90)");
            System.out.println("2 rows inserted!");
            // 3. Update a Row
            stmt.executeUpdate("UPDATE students SET marks = 95 WHERE name = 'Martin'");
            System.out.println("Row updated!");
            // 4. Select and Display Data
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            System.out.println("\n--- Student Data ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int marks = rs.getInt("marks");
                System.out.println(id + " | " + name + " | " + marks);
            }
            // Close connection
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}