import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the dictionary of valid words
 */
public class Dictionary {
    private final Set<String> words;

    public Dictionary(String filename) throws IOException {
        if (Files.exists(Paths.get(filename))) {
            words = new HashSet<>(Files.readAllLines(Paths.get(filename)));
        } else {
            words = createSampleDictionary();
            Files.write(Paths.get(filename), words);
        }
    }

    private Set<String> createSampleDictionary() {
        Set<String> sampleWords = new HashSet<>();
        String[] commonWords = {
                "THE", "BE", "TO", "OF", "AND", "A", "IN", "THAT", "HAVE", "I",
                "IT", "FOR", "NOT", "ON", "WITH", "HE", "AS", "YOU", "DO", "AT",
                "THIS", "BUT", "HIS", "BY", "FROM", "THEY", "WE", "SAY", "HER", "SHE",
                "OR", "AN", "WILL", "MY", "ONE", "ALL", "WOULD", "THERE", "THEIR", "WHAT",
                "SO", "UP", "OUT", "IF", "ABOUT", "WHO", "GET", "WHICH", "GO", "ME",
                "WHEN", "MAKE", "CAN", "LIKE", "TIME", "NO", "JUST", "HIM", "KNOW", "TAKE",
                "PEOPLE", "INTO", "YEAR", "YOUR", "GOOD", "SOME", "COULD", "THEM", "SEE", "OTHER",
                "THAN", "THEN", "NOW", "LOOK", "ONLY", "COME", "ITS", "OVER", "THINK", "ALSO",
                "BACK", "AFTER", "USE", "TWO", "HOW", "OUR", "WORK", "FIRST", "WELL", "WAY",
                "EVEN", "NEW", "WANT", "BECAUSE", "ANY", "THESE", "GIVE", "DAY", "MOST", "US"
        };
        Collections.addAll(sampleWords, commonWords);
        return sampleWords;
    }

    public boolean contains(String word) {
        return words.contains(word.toUpperCase());
    }

    public int size() {
        return words.size();
    }
}