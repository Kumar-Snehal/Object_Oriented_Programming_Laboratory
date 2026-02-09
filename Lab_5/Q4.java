package Lab_5;

import java.util.*;

class Employee {

    public String name;
    public String department;

    public Employee() {
        name = "";
        department = "";
    }

    public Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }
}

public class Q4 {

    public static void main(String[] args) {
        List<Employee> empDetails = new ArrayList<>();

        empDetails.add(new Employee("Ramesh", "Accounts"));
        empDetails.add(new Employee("Suresh", "Legal"));
        empDetails.add(new Employee("Ram", "Finance"));
        empDetails.add(new Employee("Shyam", "IT"));
        empDetails.add(new Employee("Raju", "Accounts"));
        empDetails.add(new Employee("Karan", "Legal"));
        empDetails.add(new Employee("Arjun", "IT"));

        System.out.println("\nEmployees:");
        for (Employee emp : empDetails) {
            System.out.println(emp.name + ", " + emp.department);
        }

        Map<String, List<Employee>> mp = new HashMap<>();
        for (Employee emp : empDetails) {
            if (!mp.containsKey(emp.department)) {
                mp.put(emp.department, new ArrayList<>());
            }
            mp.get(emp.department).add(emp);
        }

        System.out.println("\nEmployees department-wise:");
        for (String key : mp.keySet()) {
            System.out.print(key + ":");
            for (Employee emp : mp.get(key)) {
                System.out.print(", " + emp.name);
            }
            System.out.println();
        }
    }
}
