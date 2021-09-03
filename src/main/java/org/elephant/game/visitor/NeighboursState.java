package org.elephant.game.visitor;

import org.elephant.game.model.CellHealth;
import org.elephant.game.model.Coordinate;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class NeighboursState {

    private final Map<CellHealth, AtomicInteger> healthCount;

    public NeighboursState() {
        healthCount = new EnumMap<>(CellHealth.class);
    }

    public int getLiveNeighbourCount() {
        AtomicInteger count = healthCount.getOrDefault(CellHealth.LIVE, new AtomicInteger(0));
        return count.get();
    }

    public void updateCellHealth(Coordinate position, CellHealth cellHealth) {
        AtomicInteger count = healthCount.computeIfAbsent(cellHealth, (k) -> new AtomicInteger(0));
        count.incrementAndGet();
    }
}