import java.util.concurrent.atomic.AtomicBoolean;

public class Timekeeper extends Thread {
    private final int maxDurationSeconds;
    private final AtomicBoolean gameOver;

    public Timekeeper(int maxDurationSeconds, AtomicBoolean gameOver) {
        this.maxDurationSeconds = maxDurationSeconds;
        this.gameOver = gameOver;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        int elapsedSeconds = 0;

        try {
            while (elapsedSeconds < maxDurationSeconds && !gameOver.get()) {
                Thread.sleep(1000);

                long currentTime = System.currentTimeMillis();
                elapsedSeconds = (int) ((currentTime - startTime) / 1000);

                if (elapsedSeconds % 10 == 0) {
                    System.out.println("Game time: " + elapsedSeconds + " seconds");
                }
            }

            if (elapsedSeconds >= maxDurationSeconds) {
                System.out.println("\n=== TIME LIMIT REACHED (" + maxDurationSeconds + " seconds) ===");
                gameOver.set(true);
            }

        } catch (InterruptedException e) {
            System.err.println("Timekeeper interrupted: " + e.getMessage());
        }
    }
}