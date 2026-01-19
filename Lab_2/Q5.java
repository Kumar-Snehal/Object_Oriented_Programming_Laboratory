package Lab_2;

class Subject {
    // static to store total counts and marks
    static private int totalMarks = 0;
    static private int totalSubs = 0;
    // instance to store individual subjects
    private String name;
    private int marks;

    public Subject() {
        marks = 0;
        name = "NA";
        totalSubs++;
    }

    public Subject(String name, int marks) {
        this.name = name;
        this.marks = marks;
        totalMarks += marks;
        totalSubs++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(int marks) {
        totalMarks -= this.marks;
        this.marks = marks;
        totalMarks += marks;
    }

    // instance function can access both instance and static variable
    public void display() {
        System.out.println();
        System.out.println(name + " Score / Total Score:");
        System.out.println(marks + "/" + totalMarks);
    }

    // static function can only access static variables
    public static void dispAvg() {
        System.out.println();
        System.out.println("Total Subjects: " + totalSubs);
        System.out.println("Average Marks: " + (double) totalMarks / totalSubs);
    }
}

public class Q5 {
    public static void main(String[] args) {
        Subject English = new Subject();
        English.setName("English");
        English.setMarks(89);
        English.display();
        Subject Maths = new Subject("Maths", 92);
        Maths.display();
        Subject Science = new Subject("Science", 96);
        // instance function is called by instance
        Science.display();
        // static function needs to be called statically
        Subject.dispAvg();
        Subject History = new Subject("History", 100);
        History.display();
        Subject.dispAvg();
    }
}
