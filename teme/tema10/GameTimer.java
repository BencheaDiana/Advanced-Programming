package org.example;
public class GameTimer extends Thread {
    private final int[] playerTimes;
    private final HexGame game;
    private volatile boolean running;
    private volatile int currentPlayerIndex;
    private long lastTickTime;

    public GameTimer(int timePlayer1, int timePlayer2, HexGame game) {
        this.playerTimes = new int[]{timePlayer1, timePlayer2};
        this.game = game;
        this.running = true;
        this.currentPlayerIndex = 0;
    }

    @Override
    public void run() {
        lastTickTime = System.currentTimeMillis();
        while (running && !game.isFinished()) {
            try {
                Thread.sleep(100);

                long now = System.currentTimeMillis();
                long elapsed = now - lastTickTime;
                lastTickTime = now;

                //scurgem timpul doar pt playerul activ
                if (elapsed > 0) {
                    synchronized (this) {
                        playerTimes[currentPlayerIndex] -= (int)(elapsed / 1000);

                        //vf timpul playerului
                        if (playerTimes[currentPlayerIndex] <= 0) {
                            playerTimes[currentPlayerIndex] = 0;
                            game.timeOut(game.getCurrentPlayer());
                            break;
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public synchronized void switchPlayer() {
        currentPlayerIndex = 1 - currentPlayerIndex;
        lastTickTime = System.currentTimeMillis();
    }

    public synchronized int getTime(int playerIndex) {
        return playerTimes[playerIndex];
    }
}
