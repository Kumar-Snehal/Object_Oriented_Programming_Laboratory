package Lab_9;

class Shared {
    private Boolean chance;
    private Boolean afin, bfin;

    Shared() {
        chance = true;
        afin = false;
        bfin = false;
    }

    public synchronized void printA() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            while (chance != true && !bfin) {
                wait();
            }
            System.out.println("A" + i);
            chance = false;
            notify();
        }
        afin = true;
        System.out.println("A Finished");
    }

    public synchronized void printB() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            while (chance != false && !afin) {
                wait();
            }
            System.out.println("B" + i);
            if (i % 2 == 0)
                chance = true;
            notify();
        }
        bfin = true;
        System.out.println("B Finished");
    }
}

class A implements Runnable {
    private Shared monitor;

    A(Shared mon) {
        monitor = mon;
    }

    @Override
    public void run() {
        try {
            monitor.printA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class B implements Runnable {
    private Shared monitor;

    B(Shared mon) {
        monitor = mon;
    }

    @Override
    public void run() {
        try {
            monitor.printB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class test {
    public static void main(String args[]) {

        Shared monitor = new Shared();

        Runnable a = new A(monitor);
        Runnable b = new B(monitor);
        Thread at = new Thread(a);
        Thread bt = new Thread(b);

        at.start();
        bt.start();

        try {
            at.join();
            bt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
