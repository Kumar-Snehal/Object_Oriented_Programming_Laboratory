package Drone_Delivery_System;

import java.util.*;

class Mission {
    private List<Package> packages = new ArrayList<>();

    public List<Package> getPackages() {
        return packages;
    }

    public void addPackage(String id, String address, double weight) {
        packages.add(new Package(id, address, weight));
    }

    public void removePackage(Package p) {
        packages.remove(p);
    }
}