package Lab_1;

import java.util.*;

public class Q10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r1, c1, r2, c2;
        System.out.print("Enter Dimensions of Matrix A: ");
        r1 = sc.nextInt();
        c1 = sc.nextInt();
        System.out.print("Enter Dimensions of Matrix B: ");
        r2 = sc.nextInt();
        c2 = sc.nextInt();
        if (c1 != r2)
            System.out.println("Incompatible for Multiplication");
        else {
            int A[][] = new int[r1][c1];
            int B[][] = new int[r2][c2];
            System.out.println("Enter Matrix A: ");
            for (int i = 0; i < r1; i++)
                for (int j = 0; j < c1; j++)
                    A[i][j] = sc.nextInt();
            System.out.println("Enter Matrix B: ");
            for (int i = 0; i < r2; i++)
                for (int j = 0; j < c2; j++)
                    B[i][j] = sc.nextInt();
            int res[][] = new int[r1][c2];
            for (int i = 0; i < r1; i++)
                for (int j = 0; j < c2; j++)
                    res[i][j] = 0;
            for (int i = 0; i < r1; i++)
                for (int j = 0; j < c2; j++)
                    for (int k = 0; k < c1; k++)
                        res[i][j] += A[i][k] * B[k][j];
            System.out.println("\nMatrix A: ");
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c1; j++)
                    System.out.print(A[i][j] + " ");
                System.out.println();
            }
            System.out.println("\nMatrix B: ");
            for (int i = 0; i < r2; i++) {
                for (int j = 0; j < c2; j++)
                    System.out.print(B[i][j] + " ");
                System.out.println();
            }
            System.out.println("\nResultant Matrix (A x B): ");
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c2; j++)
                    System.out.print(res[i][j] + " ");
                System.out.println();
            }
        }
        sc.close();
    }
}
