package Lab_4;

public class Q1 {
    static long factorial(int num) {
        if (num <= 0)
            throw new IllegalArgumentException();
        if (num == 1)
            return 1;
        else
            return num * factorial(num - 1);
    }

    public static void main(String[] args) {
        try {
            String arg1 = args[0];
            int num = Integer.parseInt(arg1);
            long ans = factorial(num);
            System.out.println("Factorial of " + num + ": " + ans);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array Index Out of Bounds");
        } catch (NumberFormatException e) {
            System.out.println("Error: Number Format Exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Illegal Argument Exception");
        }
    }
}
