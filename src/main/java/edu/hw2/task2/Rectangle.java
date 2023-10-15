package edu.hw2.task2;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle() {}

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle setWidth(int width) {
        return new Rectangle(width, this.getHeight());
    }

    public Rectangle setHeight(int height) {
        return new Rectangle(this.getWidth(), height);
    }

    double area() {
        return width * height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
