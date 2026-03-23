package Drone_Delivery_System;

public class Main {
    public static void main(String[] args) {

        Mission mission = new Mission();

        Package p1 = new Package("P1", "City A", 5.0);
        Package p2 = new Package("P2", "City B", 3.5);

        mission.getPackages().add(p1);
        mission.getPackages().add(p2);

        for (Package p : mission.getPackages()) {
            p.display();
        }
    }
}