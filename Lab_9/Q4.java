package Lab_9;

class Counter {
    private int count;

    public Counter() {
        count = 0;
    }

    // Synchronized method to increment the count
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class Incrementer implements Runnable {
    private Counter counter;
    private int times;

    public Incrementer(Counter counter, int times) {
        this.counter = counter;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            counter.increment();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Q4 {
    public static void main(String[] args) {
        Counter counter = new Counter();

        int times = 100; // Number of items to increment

        Thread incrementerThread1 = new Thread(new Incrementer(counter, times));
        Thread incrementerThread2 = new Thread(new Incrementer(counter, times));

        incrementerThread1.start();
        incrementerThread2.start();

        try {
            incrementerThread1.join();
            incrementerThread2.join();
            System.out.println("Final count: " + counter.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
