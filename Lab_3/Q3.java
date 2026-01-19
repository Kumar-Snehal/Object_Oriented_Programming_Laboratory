package Lab_3;

import java.util.Scanner;

abstract class Object3D {

    abstract double SurfaceArea();

    abstract double volume();
}

class Box extends Object3D {
    double length;
    double breadth;
    double height;

    public Box(double length, double breadth, double height) {
        this.length = length;
        this.breadth = breadth;
        this.height = height;
    }

    @Override
    double SurfaceArea() {
        return 2 * (length * breadth + breadth * height + height * length);
    }

    @Override
    double volume() {
        return length * breadth * height;
    }
}

class Cube extends Object3D {
    double length;

    public Cube(double length) {
        this.length = length;
    }

    @Override
    double SurfaceArea() {
        return 6 * (length * length);
    }

    @Override
    double volume() {
        return Math.pow(length, 3);
    }
}

class Cylinder extends Object3D {
    double radius;
    double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    @Override
    double SurfaceArea() {
        return 2 * Math.PI * radius * (radius + height);
    }

    @Override
    double volume() {
        return height * Math.pow(radius, 2);
    }
}

class Cone extends Object3D {
    double radius;
    double height;

    public Cone(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    private double length() {
        return Math.sqrt(Math.pow(radius, 2) + Math.pow(height, 2));
    }

    @Override
    double SurfaceArea() {
        return Math.PI * radius * (radius + length());
    }

    @Override
    double volume() {
        return height * Math.pow(radius, 2) / 3;
    }
}

public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter length, breadth, height of Box:");
        double boxLength = sc.nextDouble();
        double boxBreadth = sc.nextDouble();
        double boxHeight = sc.nextDouble();
        Box box = new Box(boxLength, boxBreadth, boxHeight);
        System.out.println("Surface Area of Box: " + box.SurfaceArea());
        System.out.println("Volume of Box: " + box.volume());

        System.out.print("\nEnter length of Cube:");
        double cubeLength = sc.nextDouble();
        Cube cube = new Cube(cubeLength);
        System.out.println("Surface Area of Cube: " + cube.SurfaceArea());
        System.out.println("Volume of Cube: " + cube.volume());

        System.out.print("\nEnter radius and height of Cylinder:");
        double cylinderRadius = sc.nextDouble();
        double cylinderHeight = sc.nextDouble();
        Cylinder cylinder = new Cylinder(cylinderRadius, cylinderHeight);
        System.out.println("Surface Area of Cylinder: " + cylinder.SurfaceArea());
        System.out.println("Volume of Cylinder: " + cylinder.volume());

        System.out.print("\nEnter radius and height of Cone:");
        double coneRadius = sc.nextDouble();
        double coneHeight = sc.nextDouble();
        Cone cone = new Cone(coneRadius, coneHeight);
        System.out.println("Surface Area of Cone: " + cone.SurfaceArea());
        System.out.println("Volume of Cone: " + cone.volume());

        sc.close();
    }
}
