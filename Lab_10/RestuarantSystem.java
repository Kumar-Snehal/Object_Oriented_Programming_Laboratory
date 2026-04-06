package Lab_10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RestuarantSystem {

    private static Connection con;
    private static Scanner sc;

    static Statement connectToDatabase() throws Exception {
        String url = "jdbc:mysql://localhost:3306/food_db";
        String username = "KumarSnehal";
        String password = "App@123";
        // Load driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Establish connection
        con = DriverManager.getConnection(url, username, password);
        System.out.println("Connected successfully!");
        return con.createStatement();
    }

    static void createTables(Statement stmt) throws Exception {
        String createCustomerTable = "CREATE TABLE IF NOT EXISTS Customers (" +
                "customer_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "customer_name VARCHAR(50), " +
                "contact_number INT(10))";
        String createOrdersTable = "CREATE TABLE IF NOT EXISTS Orders (" +
                "order_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "customer_id INT, " +
                "food_item VARCHAR(50), " +
                "quantity INT, " +
                "status BOOLEAN, " +
                "price DECIMAL(7,2), " +
                "FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) ON DELETE CASCADE)";

        stmt.executeUpdate(createCustomerTable);
        stmt.executeUpdate(createOrdersTable);
        System.out.println("Tables created!");
    }

    static void insert(Statement stmt) {
        System.out.println("Enter Table to Insert (Customers/Orders): ");
        String table = sc.next();
        sc.nextLine();
        String insertQuery = "";
        switch (table) {
            case "Customers":
                System.out.println("Enter Customer Name: ");
                String name = sc.nextLine();
                System.out.println("Enter Contact Number: ");
                int contact = sc.nextInt();
                insertQuery = "INSERT INTO Customers (customer_name, contact_number) VALUES ('"
                        + name + "', " + contact + ")";
                break;
            case "Orders":
                System.out.println("Enter Customer ID: ");
                int customerId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Food Item: ");
                String foodItem = sc.nextLine();
                System.out.println("Enter Quantity: ");
                int quantity = sc.nextInt();
                boolean status = false;
                System.out.println("Enter Price: ");
                double price = sc.nextDouble();
                insertQuery = "INSERT INTO Orders (customer_id, food_item, quantity, status, price) VALUES (" +
                        customerId + ", '" + foodItem + "', " + quantity + ", " + status + ", " + price + ")";
                break;
            default:
                System.out.println("Invalid table!");
                return;
        }
        try {
            stmt.executeUpdate(insertQuery);
            System.out.println("Succesfully inserted!");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void update(Statement stmt) {
        System.out.println("Enter Order ID to Update: ");
        int orderId = sc.nextInt();
        sc.nextLine();
        String updateQuery = "UPDATE Orders SET status = true WHERE order_id = " + orderId;
        try {
            stmt.executeUpdate(updateQuery);
            System.out.println("Order status updated to completed!");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void retreive(Statement stmt) {
        String retrieveQuery = "SELECT o.order_id, c.customer_name, o.food_item, o.quantity, o.price " +
                "FROM Orders o JOIN Customers c ON o.customer_id = c.customer_id and o.status = false";
        try {
            ResultSet rs = stmt.executeQuery(retrieveQuery);
            System.out.println("\n--- Pending Orders ---");
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                String customerName = rs.getString("customer_name");
                String foodItem = rs.getString("food_item");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                System.out.println(
                        orderId + " | " + customerName + " | " + foodItem + " | " + quantity + " | " + price);
            }
            System.out.println("=====================");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void analyze(Statement stmt) {
        System.out.println("Options:");
        System.out.println("1. Bill for each Order");
        System.out.println("2. Total Spending of each Customer");
        System.out.println("3. Customers who spent more than a certain amount");
        int choice = 0;
        choice = sc.nextInt();
        sc.nextLine();
        String query = "";
        switch (choice) {
            case 1:
                query = "SELECT order_id, price * quantity FROM Orders";
                break;
            case 2:
                query = "SELECT c.customer_name, SUM(o.price * o.quantity) AS total_spending "
                        + "FROM Orders o JOIN Customers c ON o.customer_id = c.customer_id "
                        + "GROUP BY c.customer_id";
                break;
            case 3:
                System.out.println("Enter the minimum spending amount: ");
                double minSpending = sc.nextDouble();
                sc.nextLine();
                query = "SELECT c.customer_name, SUM(o.price * o.quantity) AS total_spending "
                        + "FROM Orders o JOIN Customers c ON o.customer_id = c.customer_id "
                        + "GROUP BY c.customer_id "
                        + "HAVING SUM(o.price * o.quantity) > " + minSpending;
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        try {
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("\n--- Analysis Result ---");
            while (rs.next()) {
                if (choice == 1) {
                    int orderId = rs.getInt(1);
                    double bill = rs.getDouble(2);
                    System.out.println("Order ID: " + orderId + " | Bill: " + bill);
                } else {
                    String customerName = rs.getString(1);
                    double totalSpending = rs.getDouble(2);
                    System.out.println("Customer: " + customerName + " | Total Spending: " + totalSpending);
                }
            }
            System.out.println("=====================");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void delete(Statement stmt) {
        System.out.println("Enter Table to Delete from (Customers/Orders): ");
        String table = sc.next();
        sc.nextLine();
        String deleteQuery = "";
        switch (table) {
            case "Customers":
                System.out.println("Enter Customer ID: ");
                int customerId = sc.nextInt();
                sc.nextLine();
                deleteQuery = "DELETE FROM Customers WHERE customer_id = " + customerId;
                break;
            case "Orders":
                System.out.println("Enter Order ID: ");
                int orderId = sc.nextInt();
                sc.nextLine();
                deleteQuery = "DELETE FROM Orders WHERE order_id = " + orderId;
                break;
            default:
                System.out.println("Invalid table!");
                return;
        }
        try {
            stmt.executeUpdate(deleteQuery);
            System.out.println("Successfully deleted!");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void display(Statement stmt) {
        String displayCustomersQuery = "SELECT * FROM Customers";
        String displayOrdersQuery = "SELECT * FROM Orders";
        try {
            ResultSet rsCustomers = stmt.executeQuery(displayCustomersQuery);
            System.out.println("\n--- Customers ---");
            while (rsCustomers.next()) {
                int customerId = rsCustomers.getInt("customer_id");
                String customerName = rsCustomers.getString("customer_name");
                int contactNumber = rsCustomers.getInt("contact_number");
                System.out.println(customerId + " | " + customerName + " | " + contactNumber);
            }
            System.out.println("=====================");
            ResultSet rsOrders = stmt.executeQuery(displayOrdersQuery);
            System.out.println("\n--- Orders ---");
            while (rsOrders.next()) {
                int orderId = rsOrders.getInt("order_id");
                int customerId = rsOrders.getInt("customer_id");
                String foodItem = rsOrders.getString("food_item");
                int quantity = rsOrders.getInt("quantity");
                boolean status = rsOrders.getBoolean("status");
                double price = rsOrders.getDouble("price");
                System.out.println(orderId + " | " + customerId + " | " + foodItem + " | " + quantity + " | " + status
                        + " | " + price);
            }
            System.out.println("=====================");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void optionLoop(Statement stmt) {
        while (true) {
            System.out.println("1. Insert");
            System.out.println("2. Update");
            System.out.println("3. Retreive");
            System.out.println("4. Analyze");
            System.out.println("5. Delete");
            System.out.println("6. Display");
            System.out.println("Else. Exit");

            int choice = 0;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input! Exiting...");
                return;
            }
            switch (choice) {
                case 1:
                    insert(stmt);
                    break;
                case 2:
                    update(stmt);
                    break;
                case 3:
                    retreive(stmt);
                    break;
                case 4:
                    analyze(stmt);
                    break;
                case 5:
                    delete(stmt);
                    break;
                case 6:
                    display(stmt);
                    break;
                default:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        try {
            // Connect to database
            Statement stmt = connectToDatabase();

            // Create Table
            createTables(stmt);

            // Option Loop
            optionLoop(stmt);

            // Close connection
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
