package Lab_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Pair {

    public int first;
    public int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class SortByPair implements Comparator<Pair> {

    public int compare(Pair A, Pair B) {
        if (A.first == B.first) {
            return A.second - B.second;
        } else {
            return A.first - B.first;
        }
    }
}

public class Q2 {

    public static void main(String[] args) {
        File file = new File("Lab_4/numbers.txt");
        try (Scanner sc = new Scanner(file)) {
            ArrayList<Pair> ar = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                int comma = line.indexOf(',');
                int space = line.lastIndexOf(' ');
                if (space == -1) {
                    space = comma;
                }
                int num1 = Integer.parseInt(line.substring(0, comma));
                int num2 = Integer.parseInt(line.substring(space + 1));
                ar.add(new Pair(num1, num2));
            }
            sc.close();
            Collections.sort(ar, new SortByPair());
            for (int i = 0; i < ar.size(); i++) {
                System.out.println(ar.get(i).first + "\t" + ar.get(i).second);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not Found.");
        }
    }
}
