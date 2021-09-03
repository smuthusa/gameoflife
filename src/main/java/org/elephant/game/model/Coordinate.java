package org.elephant.game.model;

public class Coordinate {

    private final int row;
    private final int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void validate() {
        validate(row > 0, "Max row cannot be a negative value");
        validate(column > 0, "Max column cannot be a negative value");
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isInRange(Coordinate boundary) {
        return isInRange(row, boundary.row) && isInRange(column, boundary.column);
    }

    private boolean isInRange(int pos, int maxPos) {
        return pos >= 0 && pos < maxPos;
    }

    private void validate(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}