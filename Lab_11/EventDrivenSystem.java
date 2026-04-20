package Lab_11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class EventSource extends EventSubject {

    private final DatabaseManager dbManager;
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public EventSource(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public void notifyListeners(String eventName, int eventId) {
        List<Future<?>> futures = new ArrayList<>();

        for (EventListener listener : listeners) {
            Future<?> future = executor.submit(new Runnable() {
                @Override
                public void run() {
                    listener.onEvent(eventName);
                }
            });
            futures.add(future);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Future<?> future : futures) {
                        future.get();
                    }
                    dbManager.updateEventStatus(eventId, "PROCESSED");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void generateEvent(String eventName) {
        System.out.println("\n<<< Firing Event >>>: " + eventName);

        int eventId = dbManager.insertEvent(eventName, "PENDING");

        notifyListeners(eventName, eventId);
    }

    public void shutdown() {
        executor.shutdown();
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

        eventSource.shutdown();
    }
}