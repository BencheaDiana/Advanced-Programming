package org.example;
import java.util.ArrayList;
import java.util.List;

public class HexGame {
    private final int id;
    private final int size;
    private final int timeControl;//in secunde
    private final List<Player> players;
    private Player currentPlayer;
    private int[][] board;
    private boolean started;
    private boolean finished;
    private Player winner;
    private GameTimer timer;

    public HexGame(int id, int size, int timeControl/*, GameManager gameManager*/) {
        this.id = id;
        this.size = size;
        this.timeControl = timeControl;
       // this.gameManager = gameManager;
        this.players = new ArrayList<>();
        this.board = new int[size][size];
        this.started = false;
        this.finished = false;
    }

    public void addPlayer(Player player) {
        players.add(player);
        if (players.size() == 1) {
            currentPlayer = player;
        }
    }

    public boolean canJoin() {
        return players.size() == 1 && !started;
    }

    public void start() {
        started = true;
        timer = new GameTimer(timeControl, timeControl, this);
        timer.start();
        notifyPlayers("Game started! " + currentPlayer.getName() + "'s turn. Time remaining: " +
                timer.getTime(players.indexOf(currentPlayer)) + "s");
    }

    public boolean makeMove(Player player, int x, int y) {
        if (!started || finished || player != currentPlayer || !isValidMove(x, y)) {
            return false;
        }

        board[x][y] = players.indexOf(player) + 1; //1 pt p1 2 pt p2

        if (checkWin(x, y)) {
            finishGame(player);
            return true;
        }

        switchPlayer();
        return true;
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size && board[x][y] == 0;
    }

    private void switchPlayer() {
        currentPlayer = players.get(1 - players.indexOf(currentPlayer));
        if (timer != null) {
            timer.switchPlayer();
        }
        notifyPlayers(currentPlayer.getName() + "'s turn. Time remaining: " +
                timer.getTime(players.indexOf(currentPlayer)) + "s");
    }


    //vf ultima miscare si folosim DFS sa vf daca e linia continua
    private boolean checkWin(int x, int y) {
        int player = board[x][y];
        boolean[][] visited = new boolean[size][size];
        return player == 1 ? checkTopBottomConnectivity(x, y, visited) :
                checkLeftRightConnectivity(x, y, visited);
    }

    private boolean checkTopBottomConnectivity(int x, int y, boolean[][] visited) {
        if (x == size - 1) return true; //max de jos
        visited[x][y] = true;

        //vf vecini
        int[][] directions = {{-1,0},{0,-1},{1,-1},{1,0},{0,1},{-1,1}};
        for (int[] dir : directions) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= 0 && nx < size && ny >= 0 && ny < size &&
                    board[nx][ny] == 1 && !visited[nx][ny]) {
                if (checkTopBottomConnectivity(nx, ny, visited)) return true;
            }
        }
        return false;
    }

    private boolean checkLeftRightConnectivity(int x, int y, boolean[][] visited) {
        if (y == size - 1) return true; //max dreapta
        visited[x][y] = true;

        int[][] directions = {{-1,0},{0,-1},{1,-1},{1,0},{0,1},{-1,1}};
        for (int[] dir : directions) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= 0 && nx < size && ny >= 0 && ny < size &&
                    board[nx][ny] == 2 && !visited[nx][ny]) {
                if (checkLeftRightConnectivity(nx, ny, visited)) return true;
            }
        }
        return false;
    }

    //vf timp la playeri
    public void timeOut(Player timedOutPlayer) {
        Player winner = players.get(1 - players.indexOf(timedOutPlayer));
        notifyPlayers(timedOutPlayer.getName() + " ran out of time! ");
        finishGame(winner);
    }

    private void finishGame(Player winner) {
        finished = true;
        this.winner = winner;
        timer.stop();
        notifyPlayers("Game over! " + winner.getName() + " won");
        /*if (gameManager != null) {
            gameManager.removeGame(id);
        }*/
    }

    private void notifyPlayers(String message) {
        for (Player p : players) {
            p.getClientThread().sendMessage(message);
        }
    }

    public Player getCurrentPlayer() { return currentPlayer; }

    public boolean isFinished() {
        if (finished) return true;
        return false;
    }
    public GameTimer getTimer(){
        return timer;
    }
    public boolean isStarted(){
        if(started) return true;
        return false;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
