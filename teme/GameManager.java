package org.example;
import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private Map<Integer, HexGame> activeGames;
    private int nextGameId;

    public GameManager() {
        this.activeGames = new HashMap<>();
        this.nextGameId = 1;
    }

    public synchronized int createGame(int size, int timeControl, Player player) {
        HexGame game = new HexGame(nextGameId, size, timeControl/*, this*/);
        game.addPlayer(player);
        activeGames.put(nextGameId, game);
        return nextGameId++;
    }

    public synchronized boolean joinGame(int gameId, Player player) {
        HexGame game = activeGames.get(gameId);
        if (game != null && game.canJoin()) {
            game.addPlayer(player);
            game.start();
            return true;
        }
        return false;
    }

    public synchronized HexGame getGame(int gameId) {
        return activeGames.get(gameId);
    }

}