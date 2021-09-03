package org.elephant.main;

import org.elephant.game.GameController;
import org.elephant.game.GameOfLife;
import org.elephant.game.model.CellHealth;
import org.elephant.game.model.Coordinate;
import org.elephant.game.print.ConsolePrinter;
import org.elephant.game.rule.HealthTransitionRule;
import org.elephant.game.seed.GliderSeed;
import org.elephant.game.visitor.NeighbourCellVisitor;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class GameApplication {

    public static void main(String[] args) {
        GameApplication application = new GameApplication();
        application.run();
    }

    public void run() {
        Coordinate boundary = new Coordinate(25, 25);
        NeighbourCellVisitor visitor = new NeighbourCellVisitor(new HealthTransitionRule(), boundary);
        CellHealth[][] plane = newPlane(boundary);
        GameController controller = new GameController(plane, boundary, new GliderSeed(), visitor, new ConsolePrinter());
        GameOfLife gameOfLife = new GameOfLife(controller, 1, TimeUnit.SECONDS);
        Thread gameThread = new Thread(gameOfLife, "GameOfLife");
        gameThread.start();
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            gameOfLife.stop();
            Thread.currentThread().interrupt();
        }
    }

    private CellHealth[][] newPlane(Coordinate boundary) {
        CellHealth[][] plane = new CellHealth[boundary.getRow()][boundary.getColumn()];
        for (CellHealth[] cellHealths : plane) {
            Arrays.fill(cellHealths, CellHealth.DEAD);
        }
        return plane;
    }
}