package Lab_6;

class Stack<T> {
    private class Node<U> {
        U data;
        Node<U> next;

        public Node(U data) {
            this.data = data;
            next = null;
        }
    }

    private Node<T> top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public void push(T item) {
        Node<T> newTop = new Node<>(item);
        newTop.next = top;
        top = newTop;
        size++;
    }

    public T pop() {
        if (size == 0 || top == null) {
            return null;
        }
        T retVal = top.data;
        top = top.next;
        size--;
        return retVal;
    }

    public T peek() {
        if(top==null){
            return null;
        }
        return top.data;
    }

    public Boolean isEmpty() {
        return (size == 0);
    }
}