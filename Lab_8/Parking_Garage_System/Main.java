package Parking_Garage_System;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<ParkingSlot> slots = new ArrayList<>();
        slots.add(new ParkingSlot(1));
        slots.add(new ParkingSlot(2));

        Vehicle v = new Vehicle("KA01AB1234", "Car");

        // Assign slot (bad design)
        for (ParkingSlot slot : slots) {
            if (slot.isAvailable()) {
                slot.assignSlot();
                System.out.println("Vehicle parked in slot " + slot.getSlotNumber());
                break;
            }
        }

        // Display available slots
        System.out.print("Available slots: ");
        for (ParkingSlot slot : slots) {
            if (slot.isAvailable()) {
                System.out.print(slot.getSlotNumber() + " ");
            }
        }
        System.out.println();

        // Calculate fee (bad design)
        PaymentSystem payment = new PaymentSystem();
        double fee = payment.calculateFee(v.getEntryTime());
        System.out.println("Parking fee: " + fee);
    }
}