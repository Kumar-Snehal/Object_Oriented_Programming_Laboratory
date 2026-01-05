import java.util.*;

public class Q6 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a,b;
        System.out.print("Enter 2 Integers: ");
        a=sc.nextInt();
        b=sc.nextInt();
        int dist=Math.abs(a-b);
        System.out.println("Distance: "+dist);
        sc.close();
    }
}
