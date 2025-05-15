
public class TurnManager {
    private final int playerCount;
    private int currentTurn;

    public TurnManager(int playerCount) {
        this.playerCount = playerCount;
        this.currentTurn = 0;
    }

    public synchronized boolean isMyTurn(int playerIndex) {
        return currentTurn == playerIndex;
    }

    public synchronized void waitForMyTurn(int playerIndex) throws InterruptedException {
        while (!isMyTurn(playerIndex)) {
            wait();
        }
    }

    public synchronized void finishTurn() {
        currentTurn = (currentTurn + 1) % playerCount;
        notifyAll();
    }
}