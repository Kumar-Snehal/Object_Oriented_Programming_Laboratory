class NumberHolder {
    public int anInt;
    public float aFloat;

    public NumberHolder() {
        anInt = 0;
        aFloat = 0;
    }

    public NumberHolder(int anInt, float aFloat) {
        this.anInt = anInt;
        this.aFloat = aFloat;
    }

    public void printNumbers() {
        System.out.println(anInt + ", " + aFloat);
    }
}

public class Q1 {
    public static void main(String args[]) {
        NumberHolder A = new NumberHolder();
        NumberHolder B = new NumberHolder(1, 1.2f);
        System.out.print("Instance A: ");
        A.printNumbers();
        System.out.print("Instance B: ");
        B.printNumbers();
    }
}