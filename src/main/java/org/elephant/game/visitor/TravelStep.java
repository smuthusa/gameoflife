package org.elephant.game.visitor;

import org.elephant.game.model.Coordinate;
import org.elephant.game.util.MathOp;

import static org.elephant.game.util.MathOp.*;
import static org.elephant.game.util.MathOp.PLUS;

public enum TravelStep {
    TOP_LEFT(MINUS, MINUS),
    TOP(MINUS, NO_OP),
    TOP_RIGHT(MINUS, PLUS),
    LEFT(NO_OP, MINUS),
    RIGHT(NO_OP, PLUS),
    BOTTOM_LEFT(PLUS, MINUS),
    BOTTOM(PLUS, NO_OP),
    BOTTOM_RIGHT(PLUS, PLUS);

    private final MathOp rowOp;
    private final MathOp colOp;

    TravelStep(MathOp rowOp, MathOp columnOp) {
        this.rowOp = rowOp;
        this.colOp = columnOp;
    }

    public Coordinate getPosition(int row, int col) {
        return new Coordinate(rowOp.execute(row), colOp.execute(col));
    }
}
