package Lab_1;

import java.util.*;

public class Q9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.print("Enter number: ");
        n = sc.nextInt();
        sc.close();
        int sumofcubes = 0;
        int decoy = n;
        int length = 0;
        while (decoy != 0) {
            length++;
            decoy /= 10;
        }
        decoy = n;
        while (decoy != 0) {
            sumofcubes += Math.pow(decoy % 10, length);
            decoy /= 10;
        }
        if (sumofcubes == n)
            System.out.println("Number is Armstrong Number");
        else
            System.out.println("Number is not Armstrong Number");
    }
}
