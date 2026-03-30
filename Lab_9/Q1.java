package Lab_9;

class MyThread extends Thread {
    private String param;

    public MyThread(String param) {
        this.param = param;
    }

    @Override
    public void run() {
        for (int i = 0; i < param.length(); i++) {
            System.out.println(param.charAt(i));
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class MyRunnable implements Runnable {
    private String param;

    public MyRunnable(String param) {
        this.param = param;
    }

    @Override
    public void run() {
        for (int i = 0; i < param.length(); i++) {
            System.out.println(param.charAt(i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class Q1 {
    public static void main(String[] args) {
        MyRunnable runnableA = new MyRunnable("12345");
        Thread threadA = new Thread(runnableA);
        Thread threadB = new MyThread("ABCDE");
        threadA.start();
        threadB.start();
    }
}
