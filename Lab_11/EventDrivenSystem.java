package Lab_11;

import java.sql.*;
import java.util.List;
import java.util.concurrent.*;

// ==========================================
// Part A: Basic Observer Implementation
// ==========================================

// 1. EventListener Interface (Observer)
interface EventListener {
    void onEvent(String event);
}

// ==========================================
// Part C: Multiple Listeners
// ==========================================

class LoggerListener implements EventListener {
    @Override
    public void onEvent(String event) {
        System.out.println("[Logger] Logging event: '" + event + "' | Thread: " + Thread.currentThread().getName());
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
        System.out.println(
                "[Display] Updating UI for event: '" + event + "' | Thread: " + Thread.currentThread().getName());
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
        System.out.println(
                "[Analytics] Tracking data for event: '" + event + "' | Thread: " + Thread.currentThread().getName());
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

// ==========================================
// Part E: Database Layer (JDBC)
// ==========================================

class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lab11";
    private static final String DB_USER = "KumarSnehal";
    private static final String DB_PASSWORD = "App@123";

    private Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            createTableIfNotExists();
        } catch (SQLException e) {
            System.err.println("Database connection failed. Make sure MySQL is running and credentials are correct.");
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS Events (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "status VARCHAR(50))";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insertEvent(String eventName, String status) {
        String sql = "INSERT INTO Events (name, status) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, eventName);
            pstmt.setString(2, status);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Return the generated event ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateEventStatus(int id, String newStatus) {
        String sql = "UPDATE Events SET status = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("[Database] Updated Event ID " + id + " to status: " + newStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fetchEventHistory() {
        String sql = "SELECT id, name, status FROM Events";
        System.out.println("\n--- Event History from Database ---");
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Status: %s%n",
                        rs.getInt("id"), rs.getString("name"), rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------\n");
    }
}

// ==========================================
// Part A & B: Event Source & Multithreading
// ==========================================

class EventSource {
    // Thread-safe list to avoid ConcurrentModificationException
    private final List<EventListener> listeners = new CopyOnWriteArrayList<>();

    // ExecutorService for multithreading (Part B)
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private final DatabaseManager dbManager;

    public EventSource(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void registerListener(EventListener listener) {
        listeners.add(listener);
    }

    public void generateEvent(String eventName) {
        System.out.println("\n>>> Firing Event: " + eventName);

        // 1. Insert into DB with "PENDING" status
        int eventId = dbManager.insertEvent(eventName, "PENDING");

        // Array to track the completion of all listener tasks
        CompletableFuture<?>[] futures = new CompletableFuture<?>[listeners.size()];
        int i = 0;

        // 2. Notify Listeners concurrently
        for (EventListener listener : listeners) {
            futures[i++] = CompletableFuture.runAsync(() -> {
                listener.onEvent(eventName);
            }, executor);
        }

        // 3. Update status dynamically once all listeners have finished processing this
        // event
        CompletableFuture.allOf(futures).thenRun(() -> {
            dbManager.updateEventStatus(eventId, "PROCESSED");
        });
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}

// ==========================================
// Part D: Simulate Events
// ==========================================

public class EventDrivenSystem {
    public static void main(String[] args) {
        // Initialize Database Manager
        DatabaseManager dbManager = new DatabaseManager();

        // Initialize Event Source
        EventSource eventSource = new EventSource(dbManager);

        // Register Listeners
        eventSource.registerListener(new LoggerListener());
        eventSource.registerListener(new DisplayListener());
        eventSource.registerListener(new AnalyticsListener());

        // Simulate events firing in quick succession
        eventSource.generateEvent("Button_Clicked");
        eventSource.generateEvent("Key_Space_Pressed");
        eventSource.generateEvent("Mouse_Double_Clicked");

        // Wait a brief moment to allow async threads to finish processing
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Fetch the event history from the database to verify status updates
        dbManager.fetchEventHistory();

        // Clean up threads
        eventSource.shutdown();
    }
}