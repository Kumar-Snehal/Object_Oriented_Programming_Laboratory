package Lab_5;

import java.util.*;

public class Q1 {

    public static void main(String[] args) {
        ArrayList<Integer> ar = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        System.out.println("Using for-loop:");
        for (int i = 0; i < ar.size(); i++) {
            System.out.print(" " + ar.get(i));
        }
        System.out.println();

        System.out.println("\nUsing iterator:");
        Iterator iter = ar.iterator();
        while (iter.hasNext()) {
            System.out.print(" " + iter.next());
        }
        System.out.println();

        System.out.println("\nUsing list iterator:");
        ListIterator Liter = ar.listIterator();
        while (Liter.hasNext()) {
            System.out.print(" " + Liter.next());
        }
        System.out.println();
    }
}
