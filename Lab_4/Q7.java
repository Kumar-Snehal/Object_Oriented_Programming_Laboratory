package Lab_4;

import java.util.ArrayList;

abstract class Person {

    private int personId;
    private String name;
    private int age;

    private static int nextPersonId = 1;

    public Person(String name, int age) {
        this.personId = nextPersonId++;
        this.name = name;
        this.age = age;
    }

    public boolean login(String name, int age) {
        return this.name.equals(name) && this.age == age;
    }

    public void logout() {

    }
}

class Doctor extends Person {

    private int doctorId;
    private String specialization;

    private static int nextDoctorId = 1;

    public Doctor(String name, int age, String specialization) {
        super(name, age);
        this.doctorId = nextDoctorId++;
        this.specialization = specialization;
    }

    public void PrescribeMedicine() {

    }
}

class Patient extends Person {

    private int patientId;
    private String disease;
    private ArrayList<Appointment> appointmentsList;
    private Ward ward;

    private static int nextPatientId = 1;

    public Patient(String name, int age, String disease) {
        super(name, age);
        this.patientId = nextPatientId++;
        this.disease = disease;
    }

    public void bookAppointment() {

    }

    public void admitToWard(Ward w) {
        ward = w;
    }

    public void dischargeFromWard() {
        ward = null;
    }
}

class Ward {

    private int wardNo;
    private String type;
    private ArrayList<Patient> patients;
    private int capacity;

    public Ward(int wardNo, String type, int capacity) {
        this.wardNo = wardNo;
        this.type = type;
        this.capacity = capacity;
        this.patients = new ArrayList<>();
    }

    public void addPatient(Patient p) {
        if (patients.size() < capacity) {
            patients.add(p);
            p.admitToWard(this);
        } else {
            System.out.println("Ward is full. Cannot admit patient.");
        }
    }

    public void releasePatient(Patient p) {
        patients.remove(p);
        p.dischargeFromWard();
    }

    public int getPatientCount() {
        return patients.size();
    }
}

class Appointment {

    private int appointmentId;
    private java.util.Date date;
    private String time;
    private Patient patient;
    private ArrayList<MedicalRecord> records;

    private static int nextAppointmentId = 1;

    private Billing billing;

    public Appointment(Doctor doctor, Patient patient, java.util.Date date, String time) {
        this.appointmentId = nextAppointmentId++;
        this.patient = patient;
        this.date = date;
        this.time = time;
        this.records = new ArrayList<>();
        this.billing = new Billing();
    }

    public void confirm() {

    }

    public void cancel() {

    }

    public double generateBill() {
        double totalAmount = 0;
        for (MedicalRecord record : records) {
            if (record.getMedicine() != null) {
                totalAmount += record.getMedicine().getPrice();
            }
        }
        return billing.generateBill(totalAmount);
    }
}

class Billing {

    private int billId;
    private double amount;

    private static int nextBillId = 1;

    public Billing() {
        this.billId = nextBillId++;
    }

    public double generateBill(double amount) {
        this.amount = amount;
        return amount;
    }
}

class MedicalRecord {

    private int recordId;
    private String diagnosis;

    private static int nextRecordId = 1;

    private Medicine medicine;

    public MedicalRecord(String diagnosis) {
        this.recordId = nextRecordId++;
        this.diagnosis = diagnosis;
    }

    public void updateRecord(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void useMedicine(Medicine m) {
        this.medicine = m;
    }

    public Medicine getMedicine() {
        return medicine;
    }
}

class Medicine {

    private int medicineId;
    private String name;
    private double price;

    private static int nextMedicineId = 1;

    public Medicine(String name, double price) {
        this.medicineId = nextMedicineId++;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

public class Q7 {

    public static void main(String[] args) {

    }
}
