package Lab_9;

class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds. Current balance: " + balance);
        }
    }

    public void showBalance() {
        System.out.println("Current balance: " + balance);
    }
}

class WithdrawThread implements Runnable {
    private BankAccount account;
    private int amount;
    private int times;
    private static int threadCounter = 0;
    private int threadId;

    public WithdrawThread(BankAccount account, int amount, int times) {
        this.account = account;
        this.amount = amount;
        this.times = times;
        this.threadId = ++threadCounter;
    }

    @Override
    public void run() {
        System.out.println("Thread " + threadId + " attempting to withdraw " + amount + " for " + times + " times.");
        for (int i = 0; i < times; i++) {
            account.withdraw(amount);
        }
    }
}

public class Q2 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        Thread thread1 = new Thread(new WithdrawThread(account, 100, 5));
        Thread thread2 = new Thread(new WithdrawThread(account, 100, 5));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            System.out.println("Final balance:");
            account.showBalance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
