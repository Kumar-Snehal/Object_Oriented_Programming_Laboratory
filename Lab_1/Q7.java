package Lab_1;

import java.util.*;

public class Q7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.print("Enter number of terms: ");
        n = sc.nextInt();
        double sum = 0;
        double facto = 1;
        for (int i = 1; i <= n; i++) {
            facto *= i;
            sum += i / facto;
        }
        System.out.println("Sum of Series 1/1! + 2/2! + ... + N/N! = " + sum);
        sc.close();
    }
}
