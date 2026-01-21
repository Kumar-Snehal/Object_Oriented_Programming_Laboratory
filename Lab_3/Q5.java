package Lab_3;

import java.util.*;

enum professor_type {
    full,
    associate,
    assitant;
}

abstract class Person {

    private int unique_id;
    private static int id = 1;

    Person() {
        unique_id = id++;
    }

    public int getUnique_id() {
        return unique_id;
    }
}

class Staff extends Person {

    private int office_id;

    Staff(int office_id) {
        super();
        this.office_id = office_id;
    }

    public int getOfficeId() {
        return office_id;
    }
}

class Professor extends Person {

    private professor_type type;
    private int department_id;

    Professor(professor_type type, int department_id) {
        super();
        this.type = type;
        this.department_id = department_id;
    }

    public professor_type getType() {
        return type;
    }

    public int getDepartmentId() {
        return department_id;
    }
}

abstract class Room {

    private int number_id;
    private static int id = 1;

    Room() {
        number_id = id++;
    }

    public int getNumber_id() {
        return number_id;
    }
}

class Classroom extends Room {

    private int number_of_seats;

    Classroom(int number_of_seats) {
        super();
        this.number_of_seats = number_of_seats;
    }

}

class Office extends Room {

    private String department_name;

    Office(String department_name) {
        super();
        this.department_name = department_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

}

class Department {

    private String name;
    private ArrayList<Integer> offices;

    Department(String name) {
        this.name = name;
        this.offices = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getOffices() {
        return offices;
    }
}

public class Q5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.close();
    }
}
