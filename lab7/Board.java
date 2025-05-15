
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private final List<String> submittedWords;

    public Board() {
        this.submittedWords = Collections.synchronizedList(new ArrayList<>());
    }

    public synchronized void submitWord(String playerName, String word, int points) {
        submittedWords.add(word);
        System.out.println(playerName + " submitted the word \"" + word + "\" for " + points + " points");
    }
}