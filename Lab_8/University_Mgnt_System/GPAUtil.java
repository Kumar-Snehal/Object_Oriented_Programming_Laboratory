package University_Mgnt_System;

class GPAUtil {

    public static double calculateGPA(Student student) {
        double total = 0;
        int credits = 0;

        for (Enrollment e : student.getEnrollments()) {
            total += e.getGrade() * e.getCourse().getCredits();
            credits += e.getCourse().getCredits();
        }

        return total / credits;
    }
}