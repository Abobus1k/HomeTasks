package edu.project2;

import java.util.Objects;

public class SmartPoint extends Point {

    private final SmartPoint parent;

    public SmartPoint(int x, int y, SmartPoint parent) {
        super(x, y);
        this.parent = parent;
    }

    public SmartPoint(Point point, SmartPoint parent) {
        super(point.getX(), point.getY());
        this.parent = parent;
    }

    public SmartPoint getParent() {
        return parent;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SmartPoint that = (SmartPoint) o;
        return Objects.equals(parent, that.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent);
    }
}
