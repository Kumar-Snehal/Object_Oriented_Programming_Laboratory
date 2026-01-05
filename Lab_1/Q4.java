import java.util.*;

public class Q4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n;
        System.out.print("Enter number of Integers: ");
        n=sc.nextInt();
        int ar[]=new int[n];
        for(int i=0;i<n;i++)
            ar[i]=sc.nextInt();
        Arrays.sort(ar);
        double median;
        if(n%2==0)
            median=(ar[n/2-1]+ar[n/2])/2.0;
        else
            median=ar[n/2];
        System.out.println("Median: "+median);
        sc.close();
    }
}
