
import java.util.ArrayList;
import java.util.List;

public class Airport {
    private List<String> runways;

    public Airport() {
        this.runways = new ArrayList<>();
    }

    public void addRunway(String runway) {
        runways.add(runway);
    }

    public List<String> getRunways() {
        return runways;
    }

    @Override
    public String toString() {
        return "Airport{" + "runways=" + runways + '}';
    }
}