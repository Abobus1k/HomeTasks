package edu.hw2.task2;

public class Square extends Rectangle {

    private int side;

    public Square() {}

    public Square(int side) {
        this.side = side;
    }

    public Square setSide(int sideSize) {
        return new Square(sideSize);
    }

    @Override
    double area() {
        return side * side;
    }
}
