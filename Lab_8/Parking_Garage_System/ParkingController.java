package Parking_Garage_System;

import java.util.ArrayList;
import java.util.List;

public class ParkingController {
    private List<ParkingSlot> slots;
    private int capacity;

    public ParkingController(int capacity) {
        slots = new ArrayList<>(capacity);
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

    public void park(Vehicle v) {
        for (ParkingSlot slot : slots) {
            if (slot.isAvailable()) {
                slot.assignSlot();
                System.out.println("Vehicle parked in slot " + slot.getSlotNumber());
                break;
            }
        }
    }
}
