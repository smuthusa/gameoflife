package org.elephant.game;

import org.elephant.game.model.CellHealth;
import org.elephant.game.model.Coordinate;
import org.elephant.game.print.Printer;
import org.elephant.game.rule.HealthTransitionRule;
import org.elephant.game.seed.GliderSeed;
import org.elephant.game.visitor.NeighbourCellVisitor;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameControllerTest {

    @Test
    public void testTransition() {
        Coordinate boundary = new Coordinate(10, 10);
        NeighbourCellVisitor visitor = new NeighbourCellVisitor(new HealthTransitionRule(), boundary);
        CellHealth[][] plane = new CellHealth[boundary.getRow()][boundary.getColumn()];
        for (CellHealth[] cellHealths : plane) {
            Arrays.fill(cellHealths, CellHealth.DEAD);
        }
        AtomicReference<CellHealth[][]> result = new AtomicReference<>();
        Printer unitTestObserver = result::set;
        GameController controller = new GameController(plane, boundary, new GliderSeed(), visitor, unitTestObserver);
        controller.doTransition();
        CellHealth[][] resultPlane = result.get();
        assertNotNull(resultPlane);
        assertEquals(CellHealth.LIVE, resultPlane[1][1]);
        assertEquals(CellHealth.LIVE, resultPlane[1][3]);
        assertEquals(CellHealth.LIVE, resultPlane[2][2]);
        assertEquals(CellHealth.LIVE, resultPlane[2][3]);
        assertEquals(CellHealth.LIVE, resultPlane[3][2]);
    }
}