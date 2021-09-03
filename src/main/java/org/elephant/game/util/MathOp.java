package org.elephant.game.util;

public enum MathOp {
    PLUS(1),
    MINUS(-1),
    NO_OP(0);

    private final int diff;

    MathOp(int diff) {
        this.diff = diff;
    }

    public int execute(int position) {
        return position + diff;
    }
}