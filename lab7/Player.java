
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player {
    private final String name;
    private final Bag bag;
    private final Board board;
    private final Dictionary dictionary;
    private final List<Tile> hand;
    private int score;
    private static final int INITIAL_HAND_SIZE = 7;

    public Player(String name, Bag bag, Board board, Dictionary dictionary) {
        this.name = name;
        this.bag = bag;
        this.board = board;
        this.dictionary = dictionary;
        this.hand = new ArrayList<>();
        this.score = 0;

        drawInitialHand();
    }

    private void drawInitialHand() {
        List<Tile> initialTiles = bag.drawTiles(INITIAL_HAND_SIZE);
        hand.addAll(initialTiles);
    }

    public void play(TurnManager turnManager, int playerIndex, AtomicBoolean gameOver) {
        try {
            while (!gameOver.get() && !bag.isEmpty() && hand.size() > 0) {
                turnManager.waitForMyTurn(playerIndex);

                if (gameOver.get() || bag.isEmpty()) {
                    turnManager.finishTurn();
                    break;
                }
                String word = formWord();

                if (word != null) {
                    int wordPoints = calculatePoints(word);
                    board.submitWord(name, word, wordPoints);
                    score += wordPoints;
                    removeTilesForWord(word);
                    List<Tile> newTiles = bag.drawTiles(word.length());
                    hand.addAll(newTiles);

                    System.out.println(name + " drew " + newTiles.size() + " new tiles. Hand size= " + hand.size());
                } else {
                    System.out.println(name + " couldn't form a word, discarding hand and drawing new tiles");
                    int handSize = hand.size();
                    hand.clear();
                    List<Tile> newTiles = bag.drawTiles(handSize);
                    hand.addAll(newTiles);
                    System.out.println(name + " drew " + newTiles.size() + " new tiles");
                }

                Thread.sleep(50);

                turnManager.finishTurn();
            }

            System.out.println(name + " finished playing with score: " + score);

        } catch (InterruptedException e) {
            System.err.println(name + " interrupted: " + e.getMessage());
        }
    }

    private String formWord() {
        List<String> possibleWords = findPossibleWords(hand);
        return possibleWords.stream()
                .max(Comparator.comparingInt(this::calculatePoints))
                .orElse(null);
    }

    private List<String> findPossibleWords(List<Tile> tiles) {
        Map<Character, Integer> letterCounts = new HashMap<>();
        for (Tile tile : tiles) {
            char letter = tile.getLetter();
            letterCounts.put(letter, letterCounts.getOrDefault(letter, 0) + 1);
        }

        List<String> possibleWords = new ArrayList<>();

        for (int length = 2; length <= tiles.size(); length++) {
            generatePermutations(new char[length], 0, letterCounts, possibleWords);
        }

        return possibleWords;
    }

    private void generatePermutations(char[] current, int position, Map<Character, Integer> availableLetters, List<String> results) {
        if (position == current.length) {
            String word = new String(current);
            if (dictionary.contains(word)) {
                results.add(word);
            }
            return;
        }

        for (Map.Entry<Character, Integer> entry : new HashMap<>(availableLetters).entrySet()) {
            char letter = entry.getKey();
            int count = entry.getValue();

            if (count > 0) {
                current[position] = letter;
                availableLetters.put(letter, count - 1);
                generatePermutations(current, position + 1, availableLetters, results);
                availableLetters.put(letter, count);
            }
        }
    }

    private int calculatePoints(String word) {
        int totalPoints = 0;
        Map<Character, List<Tile>> tilesByLetter = new HashMap<>();
        for (Tile tile : hand) {
            tilesByLetter.computeIfAbsent(tile.getLetter(), k -> new ArrayList<>()).add(tile);
        }
        for (char c : word.toCharArray()) {
            List<Tile> tilesForLetter = tilesByLetter.get(c);
            if (tilesForLetter != null && !tilesForLetter.isEmpty()) {
                Tile tile = tilesForLetter.get(0);
                totalPoints += tile.getPoints();
            }
        }

        return totalPoints;
    }

    private void removeTilesForWord(String word) {
        Map<Character, Integer> lettersToRemove = new HashMap<>();
        for (char c : word.toCharArray()) {
            lettersToRemove.put(c, lettersToRemove.getOrDefault(c, 0) + 1);
        }
        Iterator<Tile> iterator = hand.iterator();
        while (iterator.hasNext() && !lettersToRemove.isEmpty()) {
            Tile tile = iterator.next();
            char letter = tile.getLetter();

            if (lettersToRemove.containsKey(letter)) {
                int count = lettersToRemove.get(letter);
                if (count > 0) {
                    iterator.remove();
                    lettersToRemove.put(letter, count - 1);
                    if (lettersToRemove.get(letter) == 0) {
                        lettersToRemove.remove(letter);
                    }
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}