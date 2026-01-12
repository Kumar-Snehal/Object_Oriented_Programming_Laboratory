import java.util.Scanner;

class BankAccount {
    private int AccountNumber;
    private String AccountHolderName;
    private double Balance;

    public BankAccount() {
        AccountNumber = 0;
        AccountHolderName = "NA";
        Balance = 0.0;
    }

    public BankAccount(int AccountNumber, String AccountHolderName) {
        this.AccountNumber = AccountNumber;
        this.AccountHolderName = AccountHolderName;
        Balance = 0;
    }

    public void Deposit(Double amount) {
        System.out.println("Depositing " + amount + " to " + AccountHolderName);
        Balance += amount;
    }

    public void Withdraw(Double amount) {
        if (amount > Balance) {
            System.out.println(AccountHolderName + " has Insufficient Balance");
            return;
        }
        System.out.println("Withdrawing " + amount + " from " + AccountHolderName);
        Balance -= amount;
    }

    public void CheckBalance() {
        System.out.println("Account Number: " + AccountNumber);
        System.out.println("Account Holder Name: " + AccountHolderName);
        System.out.println("Balance: " + Balance);
    }
}

public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("OOP Lab Bank");
        System.out.print("Enter A/C No.: ");
        int AcNo = sc.nextInt();
        System.out.print("Enter Name: ");
        sc.nextLine();
        String AcName = sc.nextLine();
        BankAccount ac1 = new BankAccount(AcNo, AcName);
        System.out.println("\nOptions:\na. Deposit\nb. Withdraw\nc. Check Balance\nElse exit");
        char choice;
        do {
            System.out.print("\nEnter choice: ");
            choice = sc.next().charAt(0);
            double amt;
            switch (choice) {
                case 'a':
                    System.out.print("Enter amount: ");
                    amt = sc.nextDouble();
                    ac1.Deposit(amt);
                    break;
                case 'b':
                    System.out.print("Enter amount: ");
                    amt = sc.nextDouble();
                    ac1.Withdraw(amt);
                    break;
                case 'c':
                    ac1.CheckBalance();
                    break;
                default:
                    System.out.println("Bye");
            }
        } while (choice == 'a' || choice == 'b' || choice == 'c');
        sc.close();
    }
}
