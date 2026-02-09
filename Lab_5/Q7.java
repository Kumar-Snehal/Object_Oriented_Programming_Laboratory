package Lab_5;

import java.util.*;

class Stack<T> {
    private class Node<U> {
        U data;
        Node<U> next;

        public Node(U data) {
            this.data = data;
            next = null;
        }
    }

    Node<T> top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public void push(T item) {
        Node<T> newTop = new Node<T>(item);
        newTop.next = top;
        top = newTop;
        size++;
    }

    public T pop() {
        if (size == 0 || top == null)
            return null;
        T retVal = top.data;
        top = top.next;
        size--;
        return retVal;
    }

    public T peek() {
        return top.data;
    }

    public Boolean isEmpty() {
        return (size == 0);
    }
}

public class Q7 {
    static <T> void sampleTest(T ar[]) {
        int n = ar.length;
        Stack<T> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            System.out.println("+Pushed " + ar[i]);
            st.push(ar[i]);
        }
        for (int i = 0; !st.isEmpty() && i < n / 3; i++) {
            System.out.println("-Popped " + st.pop());
        }
        System.out.println("Stack top: " + st.peek());
    }

    public static void main(String[] args) {
        System.out.println("\nString Stack:");
        sampleTest(new String[] { "Aa", "Bb", "Cc", "Dd", "Ee", "Ff", "Gg", "Hh" });
        System.out.println("\nInteger Stack:");
        sampleTest(new Integer[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 });
    }
}
