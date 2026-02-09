package Lab_5;

import java.util.*;

public class Q6 {

    static void PrintList(List l) {
        Iterator it = l.iterator();
        while (it.hasNext())
            System.out.print(" " + it.next());
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> postIds = new ArrayList<>(
                Arrays.asList(1, 2, 3, 4, 5, 0, 2, 3, 1, 3, 5, 78, 6, 3, 2, 43, 65, 34, 2, 4, 5, 6, 23));

        System.out.println("Liked Post IDs:");
        PrintList(postIds);

        LinkedHashSet<Integer> s = new LinkedHashSet<>();
        for (Integer id : postIds) {
            s.add(id);
        }

        List<Integer> uniquePostIds = new ArrayList<>();
        for (Integer it : s) {
            uniquePostIds.add(it);
        }

        System.out.println("Unique Liked Post IDs:");
        PrintList(uniquePostIds);
    }
}
