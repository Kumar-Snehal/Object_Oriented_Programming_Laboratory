package Lab_4;

import java.util.InputMismatchException;
import java.util.Scanner;

class ATMAccount {

    private final long AccountNo;
    private double balance;

    static long ANO = 1;

    public ATMAccount() {
        AccountNo = ANO++;
        balance = 0;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount should be non-negative.");
        }
        balance += amount;
        System.out.println("\nAmount deposited.");
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient Balance.");
        }
        balance -= amount;
        System.out.println("\nAmount Withdrawn.");
    }

    public void calculateInstallment(int months) {
        if (months == 0) {
            throw new ArithmeticException("Division by Zero Error.");
        }
        System.out.println("\nInstallment: " + balance / months);
    }

    public void showBalance() {
        System.out.println("\nAccount: " + AccountNo + "\nBalance:" + balance);
    }
}

public class Q5 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            ATMAccount account = new ATMAccount();
            int choice;
            do {
                System.out.println("\nEnter Choice:");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Calculate Installments");
                System.out.println("4. Show Balance");
                System.out.println("#. Exit");
                System.out.print("Enter Choice: ");
                try {
                    choice = sc.nextInt();
                    double amount;
                    int months;
                    switch (choice) {
                        case 1 -> {
                            System.out.print("Enter Amount:");
                            amount = sc.nextDouble();
                            try {
                                account.deposit(amount);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e);
                            } finally {
                            }
                        }
                        case 2 -> {
                            System.out.print("Enter Amount:");
                            amount = sc.nextDouble();
                            try {
                                account.withdraw(amount);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e);
                            } finally {
                            }
                        }
                        case 3 -> {
                            System.out.print("Enter number of Months:");
                            months = sc.nextInt();
                            try {
                                account.calculateInstallment(months);
                            } catch (ArithmeticException e) {
                                System.out.println(e);
                            } finally {
                            }
                        }
                        case 4 ->
                            account.showBalance();
                        default ->
                            choice = 0;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Choice must be Integer.");
                    choice = 0;
                }
            } while (choice != 0);
        }
    }
}
