package org.elephant.game.visitor;

import org.elephant.game.model.CellHealth;
import org.elephant.game.model.Coordinate;
import org.elephant.game.rule.HealthTransitionRule;

import java.util.function.Function;

public class NeighbourCellVisitor {
    private final HealthTransitionRule healthTransitionRule;
    private final Coordinate boundary;

    public NeighbourCellVisitor(HealthTransitionRule healthTransitionRule, Coordinate boundary) {
        this.healthTransitionRule = healthTransitionRule;
        this.boundary = boundary;
    }

    public CellHealth visit(CellHealth currentHealth, int row, int column, Function<Coordinate, CellHealth> currentStateAtPosition) {
        NeighboursState result = new NeighboursState();
        for (TravelStep step : TravelStep.values()) {
            Coordinate position = step.getPosition(row, column);
            if (position.isInRange(boundary)) {
                CellHealth cellHealth = currentStateAtPosition.apply(position);
                result.updateCellHealth(position, cellHealth);
            }
        }
        return healthTransitionRule.computeNewHealth(currentHealth, result);
    }
}