package Lab_3;

interface FIFO {
    int Front();

    void Enqueue(int element);

    void Dequeue();

    void PrintQueue();
}

class LLQ implements FIFO {
    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    private Node front, rear;

    public LLQ() {
        front = rear = null;
    }

    @Override
    public int Front() {
        if (front != null)
            return front.data;
        return 0;
    }

    @Override
    public void Enqueue(int element) {
        if (front == null) {
            front = new Node(element);
            rear = front;
        } else {
            rear.next = new Node(element);
            rear = rear.next;
        }
    }

    @Override
    public void Dequeue() {
        if (front != null) {
            if (rear == front)
                rear = null;
            front = front.next;
        }
    }

    @Override
    public void PrintQueue() {
        System.out.print("Queue:");
        Node temp = front;
        while (temp != null) {
            System.out.print(" " + temp.data);
            temp = temp.next;
        }
        System.out.println();
    }
}

class ArrQ implements FIFO {
    private final int maxLen = 100;
    private final int arr[] = new int[maxLen];
    private int rear;

    public ArrQ() {
        rear = -1;
    }

    @Override
    public int Front() {
        if (rear == -1)
            return 0;
        return arr[0];
    }

    @Override
    public void Enqueue(int element) {
        if (rear == maxLen - 1) {
            System.out.println("Error: Queue Capacity Full");
        } else {
            arr[++rear] = element;
        }
    }

    @Override
    public void Dequeue() {
        for (int i = 0; i < rear; i++)
            arr[i] = arr[i++];
        rear--;
    }

    @Override
    public void PrintQueue() {
        System.out.print("Queue:");
        for (int i = 0; i <= rear; i++)
            System.out.print(" " + arr[i]);
        System.out.println();
    }
}

public class Q4 {
    public static void main(String[] args) {
        System.out.println("\nLinked List Queue:");
        LLQ q1 = new LLQ();
        q1.Enqueue(1);
        q1.Enqueue(2);
        q1.PrintQueue();
        System.out.print("After enqueing 3, ");
        q1.Enqueue(3);
        q1.PrintQueue();
        System.out.print("After dequeing, ");
        q1.Dequeue();
        System.out.println("Front: " + q1.Front());
        q1.PrintQueue();

        System.out.println("\nArray Queue:");
        ArrQ q2 = new ArrQ();
        q2.Enqueue(4);
        q2.Enqueue(5);
        q2.PrintQueue();
        System.out.print("After enqueing 6, ");
        q2.Enqueue(6);
        q2.PrintQueue();
        System.out.print("After dequeing, ");
        q2.Dequeue();
        System.out.println("Front: " + q2.Front());
        q2.PrintQueue();

    }
}
