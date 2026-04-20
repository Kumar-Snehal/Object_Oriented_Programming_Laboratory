package Lab_11;

class EventSource extends EventSubject {

    private final DatabaseManager dbManager;

    public EventSource(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
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