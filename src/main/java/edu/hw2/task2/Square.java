package edu.hw2.task2;

public class Square extends Rectangle {

    private boolean widthWasChanged = false;
    private boolean heightWasChanged = false;

    @Override
    public Rectangle setWidth(int width) {
        if (heightWasChanged) {
            super.setWidth(width);
            return this;
        }

        super.setWidth(width);
        super.setHeight(width);

        widthWasChanged = !widthWasChanged;

        return this;
    }

    @Override
    public Rectangle setHeight(int height) {
        if (widthWasChanged) {
            super.setWidth(height);
            return this;
        }

        super.setWidth(height);
        super.setHeight(height);

        heightWasChanged = !heightWasChanged;

        return this;
    }
}
