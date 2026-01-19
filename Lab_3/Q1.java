package Lab_3;

class Person {
    protected String name;
    protected int age;

    Person() {
        name = "NA";
        age = 0;
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void display() {
        System.out.println();
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

class Employee extends Person {
    protected double salary;

    Employee() {
        super();
        salary = 0;
    }

    Employee(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Salary: " + salary);
    }
}

class Manager extends Employee {
    private String department;

    Manager() {
        super();
        department = "NA";
    }

    Manager(String name, int age, double salary, String department) {
        super(name, age, salary);
        this.department = department;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Department: " + department);
    }
}

public class Q1 {
    public static void main(String[] args) {
        Person p = new Person("Snehal", 20);
        Employee e = new Employee("Atharva", 21, 125000);
        Manager m = new Manager("Aditya", 21, 200000, "CP");
        p.display();
        e.display();
        m.display();
    }
}