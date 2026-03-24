package Parking_Garage_System;

import java.util.ArrayList;
import java.util.List;

public class ParkingController {
    private List<ParkingSlot> slots;
    private PaymentSystem paymentSystem;

    public ParkingController(int capacity) {
        slots = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            slots.add(new ParkingSlot(i));
        }
        paymentSystem = new PaymentSystem();
    }

    public void addSlot(int slot_no) {
        for (ParkingSlot slot : slots) {
            if (slot.getSlotNumber() == slot_no) {
                return;
            }
        }
        slots.add(new ParkingSlot(slot_no));
    }

    public void removeSlot(int slot_no) {
        for (ParkingSlot slot : slots) {
            if (slot.getSlotNumber() == slot_no) {
                slots.remove(slot);
                return;
            }
        }
    }

    public ParkingSlot park(Vehicle vehicle) {
        for (ParkingSlot slot : slots) {
            if (slot.isAvailable()) {
                slot.assignSlot();
                System.out.println("Vehicle " + vehicle.getNumber() + " parked in slot " + slot.getSlotNumber());
                return slot;
            }
        }
        System.out.println("Garage is full. Vehicle " + vehicle.getNumber() + " cannot enter.");
        return null;
    }

    public void unpark(Vehicle vehicle, ParkingSlot slot) {
        if (slot != null && !slot.isAvailable()) {
            slot.releaseSlot();
            double fee = paymentSystem.calculateFee(vehicle.getEntryTime());
            System.out.println("Vehicle " + vehicle.getNumber() + " exited slot " + slot.getSlotNumber());
            System.out.println("Parking fee: " + fee);
        } else {
            System.out.println("Invalid exit operation. Slot may already be empty.");
        }
    }

    public void viewAvailableSlots() {
        System.out.print("Available slots: ");
        boolean hasAvailable = false;
        for (ParkingSlot slot : slots) {
            if (slot.isAvailable()) {
                System.out.print(slot.getSlotNumber() + " ");
                hasAvailable = true;
            }
        }
        if (!hasAvailable) {
            System.out.print("None");
        }
        System.out.println();
    }
}
