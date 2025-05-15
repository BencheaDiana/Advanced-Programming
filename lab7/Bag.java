
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag {
    private final List<Tile> tiles;

    public Bag() {
        this.tiles = new ArrayList<>();
    }

    public synchronized void addTile(Tile tile) {
        tiles.add(tile);
    }

    public synchronized Tile drawTile() {
        if (tiles.isEmpty()) {
            return null;
        }
        int randomIndex = new Random().nextInt(tiles.size());
        return tiles.remove(randomIndex);
    }

    public synchronized List<Tile> drawTiles(int count) {
        List<Tile> drawnTiles = new ArrayList<>();
        for (int i = 0; i < count && !tiles.isEmpty(); i++) {
            Tile tile = drawTile();
            if (tile != null) {
                drawnTiles.add(tile);
            }
        }
        return drawnTiles;
    }

    public synchronized boolean isEmpty() {
        return tiles.isEmpty();
    }

    public synchronized int size() {
        return tiles.size();
    }
}