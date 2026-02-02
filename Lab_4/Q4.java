package Lab_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Q4 {

    public static void main(String[] args) {
        File file = new File("Lab_4/xanadu.txt");
        try (Scanner sc = new Scanner(file)) {
            char ch = args[0].charAt(0);
            int count = 0;
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                for (char c : line.toCharArray()) {
                    if (c == ch) {
                        count++;
                    }
                }
            }
            sc.close();
            System.out.println("Total count of " + ch + ": " + count);
        } catch (FileNotFoundException e) {
            System.out.println("Error: xanadu.txt not found in Lab_4 folder.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: No character provided as argument.");
        }
    }
}
