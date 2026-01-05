import java.util.*;
public class Q2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String first,last;
        System.out.print("Enter First Name: ");
        first=sc.next();
        System.out.print("Enter Last Name: ");
        last=sc.next();
        System.out.println("Greetings "+first+" "+last+"!");
        sc.close();
    }
}
