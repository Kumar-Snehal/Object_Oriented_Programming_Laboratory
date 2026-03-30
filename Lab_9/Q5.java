package Lab_9;

class SharedResource {

    public SharedResource() {
    }

    public synchronized void print(String ThreadName) {
        System.out.println(ThreadName + " Thread is using the shared resource.");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Worker implements Runnable {
    private SharedResource resource;
    private String threadName;
    private int times;

    public Worker(SharedResource resource, String threadName, int times) {
        this.resource = resource;
        this.threadName = threadName;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            resource.print(threadName);
        }
    }
}

public class Q5 {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        int times = 10; // Number of times each thread will use the resource

        Thread thread1 = new Thread(new Worker(resource, "HighPriority", times));
        Thread thread2 = new Thread(new Worker(resource, "NormPriority", times));
        Thread thread3 = new Thread(new Worker(resource, "LowPriority", times));

        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.NORM_PRIORITY);
        thread3.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
