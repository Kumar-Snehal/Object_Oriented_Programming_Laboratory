package Lab_5;

public class Q2 {

    static <T> void genericPrint(T ar[]) {
        System.out.println("\nGeneric Display:");
        for (T it : ar) {
            System.out.print(" " + it);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] a = {1, 2, 3};
        String[] b = {"A", "B", "C"};
        genericPrint(a);
        genericPrint(b);
    }
}
