package Lab_5;

import java.util.*;

public class Q3 {

    public static void main(String[] args) {
        HashMap<String, Integer> mp = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();
        sc.close();

        String cleanSentence = sentence.replaceAll("[\\p{Punct}]", "");
        StringTokenizer st = new StringTokenizer(cleanSentence);
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            if (mp.containsKey(word)) {
                mp.put(word, mp.get(word) + 1);
            } else {
                mp.put(word, 1);
            }
        }

        System.out.println("Frequency Count:");
        for (String key : mp.keySet()) {
            System.out.println(key + ": " + mp.get(key));
        }
    }
}
