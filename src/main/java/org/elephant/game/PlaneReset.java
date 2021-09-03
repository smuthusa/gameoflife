package org.elephant.game;

import org.elephant.game.model.CellHealth;
import org.elephant.game.model.Coordinate;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlaneReset {

    public CellHealth[][] resetPosition(CellHealth[][] newPlane, Coordinate boundary) {
        Map<Integer, AtomicBoolean> columnsState = new HashMap<>();
        Map<Integer, AtomicBoolean> rowsState = new HashMap<>();
        for (int row = 0; row < boundary.getRow(); row++) {
            AtomicBoolean rowState = rowsState.computeIfAbsent(row, key -> new AtomicBoolean(false));
            for (int col = 0; col < boundary.getColumn(); col++) {
                AtomicBoolean colState = columnsState.computeIfAbsent(col, key -> new AtomicBoolean(false));
                colState.set(colState.get() || newPlane[row][col] == CellHealth.LIVE);
                rowState.set(rowState.get() || newPlane[row][col] == CellHealth.LIVE);
            }
        }
        OptionalInt minLiveCellRow = rowsState.entrySet().stream().filter(e -> e.getValue().get()).mapToInt(Map.Entry::getKey).min();
        OptionalInt minLiveCellColumn = columnsState.entrySet().stream().filter(e -> e.getValue().get()).mapToInt(Map.Entry::getKey).min();
        OptionalInt maxLiveCellRow = rowsState.entrySet().stream().filter(e -> e.getValue().get()).mapToInt(Map.Entry::getKey).max();
        OptionalInt maxLiveCellCol = columnsState.entrySet().stream().filter(e -> e.getValue().get()).mapToInt(Map.Entry::getKey).max();
        int colsToShift = minLiveCellColumn.isPresent() ? minLiveCellColumn.getAsInt() - 1 : 0;
        int rowsToShift = minLiveCellRow.isPresent() ? minLiveCellRow.getAsInt() - 1 : 0;
        boolean isCellAtEndOfTheColumn = maxLiveCellCol.isPresent() && maxLiveCellCol.getAsInt() == boundary.getColumn() - 1;
        boolean isCellAtEndOfTheRow = maxLiveCellRow.isPresent() && maxLiveCellRow.getAsInt() == boundary.getRow() - 1;
        return isCellAtEndOfTheColumn || isCellAtEndOfTheRow ? moveCellsBy(newPlane, boundary, rowsToShift, colsToShift) : newPlane;
    }

    private CellHealth[][] moveCellsBy(CellHealth[][] newPlane, Coordinate boundary, int rowsToShift, int colsToShift) {
        for (int row = 0; row < boundary.getRow(); row++) {
            for (int col = 0; col < boundary.getColumn(); col++) {
                if (newPlane[row][col] == CellHealth.LIVE) {
                    newPlane[row - rowsToShift][col - colsToShift] = CellHealth.LIVE;
                    newPlane[row][col] = CellHealth.DEAD;
                }
            }
        }
        return newPlane;
    }
}