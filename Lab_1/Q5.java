package Lab_1;

import java.util.*;

public class Q5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b, c;
        System.out.print("Enter 3 Integers: ");
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        int max = (a > b) ? (a > c) ? a : c : (b > c) ? b : c;
        System.out.println("Maximum: " + max);
        sc.close();
    }
}
