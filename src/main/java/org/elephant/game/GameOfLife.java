package org.elephant.game;

import java.util.concurrent.TimeUnit;

public class GameOfLife implements Runnable {
    private final TimeUnit timeUnit;
    private final long tickInterval;
    private volatile boolean done = false;
    private final GameController controller;

    public GameOfLife(GameController controller, long tickInterval, TimeUnit timeUnit) {
        this.controller = controller;
        this.timeUnit = timeUnit;
        this.tickInterval = tickInterval;
    }

    public void play() {
        while (!done) {
            controller.doTransition();
            try {
                Thread.sleep(timeUnit.toMillis(tickInterval));
            } catch (InterruptedException e) {
                done = true;
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void run() {
        play();
    }

    public void stop() {
        this.done = true;
    }
}
