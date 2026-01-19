package Lab_1;

import java.util.*;

public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b, c;
        System.out.print("Enter 3 Integers: ");
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        int prod = a * b * c;
        System.out.println("a * b * c = " + a + " * " + b + " * " + c + " = " + prod);
        sc.close();
    }
}
