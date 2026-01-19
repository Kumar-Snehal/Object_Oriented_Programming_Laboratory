package Lab_2;

import java.util.*;

class Garments {
    // private data members since we are using Set and Display Function for
    // accessing
    private String GCode, GType, GFabric;
    private int GSize;
    private float GPrice;

    public Garments() {
        GCode = GType = GFabric = "NA";
        GPrice = GSize = 0;
    }

    // outside of the class no function should be able to call Assign other than
    // SetValue hence private
    private void Assign() {
        if (GType.equalsIgnoreCase("Trouser"))
            GPrice = 1300;
        else if (GType.equalsIgnoreCase("Shirt"))
            GPrice = 1100;
        if (!GFabric.equalsIgnoreCase("COTTON"))
            GPrice = GPrice - 0.1f * GPrice;
    }

    // SetValue should be able to be called from outside the class hence public
    public void SetValue() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter GCode: ");
        GCode = sc.next();
        System.out.print("Enter GType: ");
        GType = sc.next();
        System.out.print("Enter GFabric: ");
        GFabric = sc.next();
        System.out.print("Enter GSize: ");
        GSize = sc.nextInt();
        Assign();
        sc.close();
    }

    // Display should be able to be called from outside the class hence public
    public void Display() {
        System.out.println("GCode: " + GCode);
        System.out.println("GType: " + GType);
        System.out.println("GSize: " + GSize);
        System.out.println("GFabric: " + GFabric);
        System.out.println("GPrice: " + GPrice);
    }

}

public class Q2 {
    public static void main(String[] args) {
        Garments cloth = new Garments();
        cloth.SetValue();
        cloth.Display();
    }
}