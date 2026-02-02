package Lab_3;

class A {

    public int val = 100;
    public static int val2 = 20;

    public void changeB(int value, B ref) {
        ref.val = value;
    }

    public void show() {
        System.out.println();
        System.out.println("A:" + val);
        System.out.println("A2:" + val2);
    }
}

class B {

    public int val = 200;

    public void show() {
        A.val2 = 40;
        System.out.println("B:" + val);
    }
}

public class test {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.show();
        b.show();
        a.changeB(50, b);
        a.show();
        b.show();
    }
}
