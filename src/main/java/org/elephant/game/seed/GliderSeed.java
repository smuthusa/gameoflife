package org.elephant.game.seed;

import org.elephant.game.model.CellHealth;
import org.elephant.game.model.Coordinate;
import org.elephant.game.visitor.TravelStep;

import java.util.Arrays;
import java.util.Collection;

import static org.elephant.game.visitor.TravelStep.*;

public class GliderSeed implements CellSeed {

    private final Collection<TravelStep> steps;
    private final Coordinate initialCoordinate;

    public GliderSeed() {
        steps = Arrays.asList(RIGHT, RIGHT, UP, UP_LEFT);
        initialCoordinate = new Coordinate(2, 1);
    }

    @Override
    public void seed(CellHealth[][] plane) {
        Coordinate path = initialCoordinate;
        plane[path.getRow()][path.getColumn()] = CellHealth.LIVE;
        for (TravelStep step : steps) {
            path = step.getPosition(path.getRow(), path.getColumn());
            plane[path.getRow()][path.getColumn()] = CellHealth.LIVE;
        }
    }
}
