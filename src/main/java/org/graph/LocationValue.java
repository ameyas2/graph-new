package org.graph;

public class LocationValue extends Location {

    int value;
    public LocationValue(int row, int col, int value) {
        super(row, col);
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString() + " " + value;
    }
}
