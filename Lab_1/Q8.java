package Lab_1;

import java.util.*;

public class Q8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.print("Enter number: ");
        n = sc.nextInt();
        int factofor = 1;
        int factowhile = 1;
        for (int i = 1; i <= n; i++) {
            factofor *= i;
        }
        while (n > 0) {
            factowhile *= n;
            n--;
        }
        System.out.println("Factorial using for lopp: " + factofor);
        System.out.println("Factorial using while loop: " + factowhile);
        sc.close();
    }
}
