package org.elephant.game.rule;

import org.elephant.game.visitor.NeighboursState;
import org.elephant.game.model.CellHealth;

public class HealthTransitionRule {

    public CellHealth computeNewHealth(CellHealth currentState, NeighboursState result) {
        if (currentState == CellHealth.LIVE) {
            return canCellSurvive(result.getLiveNeighbourCount()) ? CellHealth.LIVE : CellHealth.DEAD;
        } else if (canReincarnate(result.getLiveNeighbourCount())) {
            return CellHealth.LIVE;
        }
        return CellHealth.DEAD;
    }

    private boolean canReincarnate(int currentPopulation) {
        return currentPopulation == 3;
    }

    private boolean canCellSurvive(int currentPopulation) {
        return currentPopulation == 2 || currentPopulation == 3;
    }
}