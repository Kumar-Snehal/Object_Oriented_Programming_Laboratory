package Lab_9;

class Buffer {
    private int size;
    private int[] arr;
    private int head = 0;
    private int tail = 0;
    private int count = 0;

    public Buffer(int size) {
        this.size = size;
        this.arr = new int[size];
    }

    public synchronized void produce(int value) throws InterruptedException {
        // While buffer is full, release the lock and wait
        while (count == size) {
            System.out.println("Buffer full. Producer waiting...");
            wait();
        }

        arr[tail] = value;
        tail = (tail + 1) % size;
        count++;
        System.out.println("Produced: " + value);

        // Wake up the consumer
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while (count == 0) {
            System.out.println("Buffer empty. Consumer waiting...");
            wait();
        }

        int value = arr[head];
        head = (head + 1) % size;
        count--;
        System.out.println("Consumed: " + value);

        // Wake up the producer
        notify();
        return value;
    }
}

class Producer implements Runnable {
    private Buffer buffer;
    private int times;

    public Producer(Buffer buffer, int times) {
        this.buffer = buffer;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            int value = (int) (Math.random() * 100);
            try {
                buffer.produce(value);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private Buffer buffer;
    private int times;

    public Consumer(Buffer buffer, int times) {
        this.buffer = buffer;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            try {
                int value = buffer.consume();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Q3 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);

        int times = 10; // Number of items to produce/consume

        Runnable producer = new Producer(buffer, times);
        Runnable consumer = new Consumer(buffer, times);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
