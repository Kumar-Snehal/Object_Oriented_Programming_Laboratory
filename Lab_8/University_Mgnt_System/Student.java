package University_Mgnt_System;

import java.util.*;

class Student {
    private String name;
    private List<Enrollment> enrollments = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void addEnrollment(Enrollment e) {
        enrollments.add(e);
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
}