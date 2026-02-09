package Lab_5;

import java.time.LocalTime;
import java.util.*;

class Appointment {

    public int appointmentID;
    public String patientName;
    public LocalTime appointmentTime;

    private static int nextId = 1;

    public Appointment() {
        appointmentID = nextId++;
        patientName = "";
        appointmentTime = LocalTime.of(0, 0);
    }

    public Appointment(String name, int hour, int minute) {
        appointmentID = nextId++;
        patientName = name;
        appointmentTime = LocalTime.of(hour, minute);
    }

    public void PrintAppointment() {
        System.out.println(appointmentID + ". " + patientName + " " + appointmentTime);
    }

}

class SortByTime implements Comparator<Appointment> {

    @Override
    public int compare(Appointment A, Appointment B) {
        return A.appointmentTime.compareTo(B.appointmentTime);
    }
}

class SortByName implements Comparator<Appointment> {

    @Override
    public int compare(Appointment A, Appointment B) {
        return A.patientName.compareTo(B.patientName);
    }
}

public class Q5 {

    public static void main(String[] args) {
        List<Appointment> appList = new ArrayList<>();
        appList.add(new Appointment("Ramesh", 6, 30));
        appList.add(new Appointment("Suresh", 18, 0));
        appList.add(new Appointment("Ram", 12, 0));
        appList.add(new Appointment("Shyam", 15, 45));
        appList.add(new Appointment("Raju", 9, 30));
        appList.add(new Appointment("Karan", 10, 0));
        appList.add(new Appointment("Arjun", 10, 30));

        System.out.println("\nAppointments:");
        for (Appointment app : appList) {
            app.PrintAppointment();
        }

        Collections.sort(appList, new SortByTime());
        System.out.println("\nAppointments time-wise:");
        for (Appointment app : appList) {
            app.PrintAppointment();
        }

        Collections.sort(appList, new SortByName());
        System.out.println("\nAppointments patient-wise:");
        for (Appointment app : appList) {
            app.PrintAppointment();
        }
    }
}
