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
    private int size, front, rear;

    public ArrQ() {
        front = 0;
        rear = 0;
        size = 0;
    }

    @Override
    public int Front() {
        if (size == 0)
            return 0;
        return arr[front];
    }

    @Override
    public void Enqueue(int element) {
        if (size == maxLen) {
            System.out.println("Error: Queue Capacity Full");
        } else {
            size++;
            arr[rear++] = element;
            if (rear == maxLen)
                rear = 0;
        }
    }

    @Override
    public void Dequeue() {
        if (size > 0) {
            size--;
            front++;
            if(front==maxLen)
                front=0;
        }
    }

    @Override
    public void PrintQueue() {
        System.out.print("Queue:");
        for (int i = 0; i < size; i++)
            System.out.print(" " + arr[(front+i)%maxLen]);
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
