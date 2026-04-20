package Lab_11;

import java.sql.*;
import java.util.List;
import java.util.concurrent.*;

interface EventListener {
    void onEvent(String event);
}

class LoggerListener implements EventListener {
    @Override
    public void onEvent(String event) {
        System.out.println("[Logger] Logging event: '" + event
                + "' | Thread: " + Thread.currentThread().getName());
        simulateWork(200);
    }

    private void simulateWork(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class DisplayListener implements EventListener {
    @Override
    public void onEvent(String event) {
        System.out.println("[Display] Updating UI for event: '" + event
                + "' | Thread: " + Thread.currentThread().getName());
        simulateWork(300);
    }

    private void simulateWork(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class AnalyticsListener implements EventListener {
    @Override
    public void onEvent(String event) {
        System.out.println("[Analytics] Tracking data for event: '" + event
                + "' | Thread: " + Thread.currentThread().getName());
        simulateWork(400);
    }

    private void simulateWork(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class DatabaseManager {
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
            System.out.println("[Database] Updated Event ID " + id + " to status: " + newStatus);
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

class EventSource {
    private final List<EventListener> listeners = new CopyOnWriteArrayList<>();

    private final DatabaseManager dbManager;

    public EventSource(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void registerListener(EventListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners(String eventName, int eventId) {
        Thread[] threads = new Thread[listeners.size()];
        int i = 0;

        for (EventListener listener : listeners) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    listener.onEvent(eventName);
                }
            });
            threads[i++] = t;
            t.start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Thread t : threads) {
                        t.join();
                    }
                    dbManager.updateEventStatus(eventId, "PROCESSED");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void generateEvent(String eventName) {
        System.out.println("\n>>> Firing Event: " + eventName);

        int eventId = dbManager.insertEvent(eventName, "PENDING");

        notifyListeners(eventName, eventId);
    }
}

public class EventDrivenSystem {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        EventSource eventSource = new EventSource(dbManager);

        eventSource.registerListener(new LoggerListener());
        eventSource.registerListener(new DisplayListener());
        eventSource.registerListener(new AnalyticsListener());

        eventSource.generateEvent("Button_Clicked");
        eventSource.generateEvent("Key_Space_Pressed");
        eventSource.generateEvent("Mouse_Double_Clicked");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dbManager.fetchEventHistory();
    }
}