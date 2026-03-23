package Parking_Garage_System;

class PaymentSystem {
    public double calculateFee(long entryTime) {
        long duration = System.currentTimeMillis() - entryTime;
        long minutes = duration / (1000 * 60);
        return minutes * 2;
    }
}