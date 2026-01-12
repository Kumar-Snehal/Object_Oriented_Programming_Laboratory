class Student {
    private int marks;

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getMarks() {
        return marks;
    }
}

public class Q4 {
    public static void main(String[] args) {
        Student a = new Student();
        // We cannot access marks directly since its private
        /*
         * a.marks = 90;
         * System.out.println(a.marks);
         */
        a.setMarks(90);
        System.out.println(a.getMarks());
    }
}
