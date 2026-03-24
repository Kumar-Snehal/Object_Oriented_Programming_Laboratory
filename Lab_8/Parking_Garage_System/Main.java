package Parking_Garage_System;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        ParkingController garage = new ParkingController(2);
        Vehicle v1 = new Vehicle("KA01AB1234", "Car");

        garage.viewAvailableSlots();
        ParkingSlot assignedSlot = garage.park(v1);
        garage.viewAvailableSlots();

        if (assignedSlot != null) {
            garage.unpark(v1, assignedSlot);
        }

        garage.viewAvailableSlots();
    }
}