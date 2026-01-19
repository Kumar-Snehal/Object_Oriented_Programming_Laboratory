package Lab_3;

abstract class Object3D {

    abstract double SurfaceArea();

    abstract double volume();
}

class Box extends Object3D {
    double length;
    double breadth;
    double height;

    Box() {
        length = 0;
        breadth = 0;
        height = 0;
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

public class Q3 {

}
