package org.elephant.game;

import org.elephant.game.model.CellHealth;
import org.elephant.game.model.Coordinate;
import org.elephant.game.print.Printer;
import org.elephant.game.seed.CellSeed;
import org.elephant.game.visitor.NeighbourCellVisitor;

import java.util.Arrays;
import java.util.function.Function;

public class GameController {
    private CellHealth[][] plane;
    private final Printer printer;
    private final Coordinate boundary;
    private final PlaneReset planeReset;
    private final NeighbourCellVisitor cellVisitor;
    public GameController(Coordinate boundary, CellSeed cellSeed, NeighbourCellVisitor cellVisitor, Printer printer) {
        this.boundary = boundary;
        this.printer = printer;
        this.plane = newPlane(boundary);
        this.cellVisitor = cellVisitor;
        boundary.validate();
        cellSeed.seed(plane);
        planeReset = new PlaneReset();
    }

    private CellHealth[][] newPlane(Coordinate boundary) {
        CellHealth[][] newPlane = new CellHealth[boundary.getRow()][boundary.getColumn()];
        for (CellHealth[] cellHealths : newPlane) {
            Arrays.fill(cellHealths, CellHealth.DEAD);
        }
        return newPlane;
    }

    private void displayCurrentState(CellHealth[][] plane) {
        printer.print(plane);
    }

    public void doTransition() {
        Function<Coordinate, CellHealth> healthByPositionFn = (pos) -> plane[pos.getRow()][pos.getColumn()];
        CellHealth[][] newPlane = new CellHealth[boundary.getRow()][boundary.getColumn()];
        for (int row = 0; row < boundary.getRow(); row++) {
            for (int col = 0; col < boundary.getColumn(); col++) {
                CellHealth newHealth = cellVisitor.visit(plane[row][col], row, col, healthByPositionFn);
                newPlane[row][col] = newHealth;
            }
        }
        swapPlane(newPlane);
        displayCurrentState(newPlane);
    }

    private void swapPlane(CellHealth[][] newPlane) {
        this.plane = planeReset.resetPosition(newPlane, boundary);
    }
}
