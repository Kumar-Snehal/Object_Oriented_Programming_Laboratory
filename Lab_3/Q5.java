package Lab_3;

import java.util.*;

enum professor_type {
    full,
    associate,
    assistant;
}

abstract class Persons {

    private String name;
    private int unique_id;
    private static int id = 1;

    public Persons(String name) {
        unique_id = id++;
        this.name = name;
    }

    public int getUnique_id() {
        return unique_id;
    }

    public String getName() {
        return name;
    }

    abstract public void Display();
}

class Staff extends Persons {

    private Office office;

    public Staff(String name, Office office) {
        super(name);
        this.office = office;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    @Override
    public void Display() {
        System.out.print("Unique ID: " + getUnique_id() + ", ");
        System.out.print("Name: " + getName() + ", ");
        System.out.print("Office ID: " + office.getNumber_id() + "\n");
    }
}

class Professor extends Persons {

    private professor_type type;
    private Department department;

    public Professor(String name, professor_type type, Department department) {
        super(name);
        this.type = type;
        this.department = department;
    }

    public professor_type getType() {
        return type;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setType(professor_type type) {
        this.type = type;
    }

    @Override
    public void Display() {
        System.out.print("Unique ID: " + getUnique_id() + ", ");
        System.out.print("Name: " + getName() + ", ");
        System.out.print("Type: " + type + ", ");
        System.out.print("Department: " + department.getName() + "\n");
    }
}

abstract class Room {

    private int number_id;
    private static int id = 1;

    public Room() {
        number_id = id++;
    }

    public int getNumber_id() {
        return number_id;
    }

    abstract public void Display();
}

class Classroom extends Room {

    private int number_of_seats;

    public Classroom(int number_of_seats) {
        super();
        this.number_of_seats = number_of_seats;
    }

    public int getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(int number_of_seats) {
        this.number_of_seats = number_of_seats;
    }

    @Override
    public void Display() {
        System.out.print("Number ID: " + getNumber_id() + ", ");
        System.out.print("Number of Seats: " + number_of_seats + "\n");
    }
}

class Office extends Room {

    private Department department;

    public Office(Department department) {
        super();
        this.department = department;
        if (department != null) {
            department.addOffice(this);
        }
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public void Display() {
        System.out.print("Number ID: " + getNumber_id() + ", ");
        System.out.print("Department: " + department.getName() + "\n");
    }
}

class Department {

    private String name;
    private ArrayList<Office> offices;

    public Department(String name) {
        this.name = name;
        this.offices = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Office> getOffices() {
        return offices;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addOffice(Office office) {
        if (!offices.contains(office)) {
            offices.add(office);
        }
    }

    public void Display() {
        System.out.print("Department Name: " + name + ", ");
        System.out.print("Number of Offices: " + offices.size() + "\n");
    }
}

public class Q5 {

    static void Display(Department[] departments, Office[] offices, Classroom[] classrooms, Professor[] professors, Staff[] staffs) {
        System.out.println("Departments:");
        for (Department dept : departments) {
            dept.Display();
        }
        System.out.println("\nOffices:");
        for (Office office : offices) {
            office.Display();
        }
        System.out.println("\nClassrooms:");
        for (Classroom classroom : classrooms) {
            classroom.Display();
        }
        System.out.println("\nProfessors:");
        for (Professor professor : professors) {
            professor.Display();
        }
        System.out.println("\nStaff:");
        for (Staff staff : staffs) {
            staff.Display();
        }
    }

    public static void main(String[] args) {
        Department cs = new Department("Computer Science and Engineering");
        Department ee = new Department("Electrical Engineering");
        Department ece = new Department("Electronics and Communication Engineering");

        Office cs1 = new Office(cs);
        Office cs2 = new Office(cs);
        Office cs3 = new Office(cs);
        Office ee1 = new Office(ee);
        Office ee2 = new Office(ee);
        Office ece1 = new Office(ece);

        Classroom roomA = new Classroom(60);
        Classroom roomB = new Classroom(120);
        Classroom roomC = new Classroom(240);

        Professor p1 = new Professor("Prof. Alice", professor_type.full, cs);
        Professor p2 = new Professor("Dr. Bob", professor_type.assistant, ee);
        Professor p3 = new Professor("Dr. Charlie", professor_type.associate, ece);
        Professor p4 = new Professor("Dr. Delta", professor_type.associate, cs);
        Professor p5 = new Professor("Dr. Echo", professor_type.assistant, ee);

        Staff s1 = new Staff("Mr. Frank", cs1);
        Staff s2 = new Staff("Ms. Grace", ee1);
        Staff s3 = new Staff("Mr. Henry", ece1);
        Staff s4 = new Staff("Ms. Irene", cs2);

        Department[] departments = {cs, ee, ece};
        Office[] offices = {cs1, cs2, cs3, ee1, ee2, ece1};
        Classroom[] classrooms = {roomA, roomB, roomC};
        Professor[] professors = {p1, p2, p3, p4, p5};
        Staff[] staffs = {s1, s2, s3, s4};

        Display(departments, offices, classrooms, professors, staffs);
    }
}
