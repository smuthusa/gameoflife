package org.elephant.game.print;

import org.elephant.game.model.CellHealth;

public class ConsolePrinter implements Printer {

    @Override
    public void print(CellHealth[][] plane) {
        System.out.println("\n");
        System.out.println("Current State");
        StringBuilder planeState = new StringBuilder();
        for (CellHealth[] cellHealths : plane) {
            for (CellHealth health : cellHealths) {
                planeState.append(health == CellHealth.DEAD ? "." : "*").append("  ");
            }
            planeState.append("\n");
        }
        System.out.print(planeState);
    }
}