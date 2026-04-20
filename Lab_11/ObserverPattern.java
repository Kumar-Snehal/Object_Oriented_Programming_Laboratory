package Lab_11;

import java.util.*;
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

abstract class EventSubject {
    protected final List<EventListener> listeners = new CopyOnWriteArrayList<>();

    public void registerListener(EventListener listener) {
        listeners.add(listener);
    }

    public abstract void notifyListeners(String eventName, int eventId);
}
