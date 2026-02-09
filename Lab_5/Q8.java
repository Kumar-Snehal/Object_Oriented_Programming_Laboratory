package Lab_5;

import java.util.*;

class Repository<K, V> {
    private Map<K, V> mpp;

    public Repository() {
        mpp = new HashMap<K, V>();
    }

    public void add(K key, V value) {
        mpp.put(key, value);
    }

    public void remove(K key) {
        mpp.remove(key);
    }

    public V find(K key) {
        return mpp.get(key);
    }

    // cannot directly return mpp.values(), because that is linked to the map, hence
    // storing in another structure and returning, to ensure the point of calling
    // function has an effect
    public ArrayList<V> findAll() {
        ArrayList<V> retval = new ArrayList<>();
        for (V it : mpp.values()) {
            retval.add(it);
        }
        return retval;
    }
}

public class Q8 {
    public static void main(String[] args) {
        Repository<String, Integer> repo = new Repository<>();
        System.out.print("\nAdding:");
        System.out.print(" (Snehal,2009)");
        repo.add("Snehal", 2009);
        System.out.print(" (Atharva,2011)");
        repo.add("Atharva", 2011);
        System.out.print(" (Aditya,2001)");
        repo.add("Aditya", 2001);
        System.out.print(" (dummy,-1)");
        repo.add("dummy", -1);

        System.out.print("\nAll values:");
        Collection<Integer> l = repo.findAll();
        for (Integer it : l) {
            System.out.print(" " + it);
        }
        System.out.println();

        System.out.println("\nFinding value of \"dummy\":" + repo.find("dummy"));

        System.out.println("\nRemoving \"dummy\".");
        repo.remove("dummy");

        System.out.print("\nAll values after removal:");
        l = repo.findAll();
        for (Integer it : l) {
            System.out.print(" " + it);
        }
        System.out.println();
    }
}
