import java.time.LocalDateTime;

public class GameResult {

    private String name;
    private Difficulty level;
    private int tries;
    private String date;

    public GameResult(String name, Difficulty level, int tries) {
        this.name = name;
        this.level = level;
        this.tries = tries;
        this.date = LocalDateTime.now().toString();
    }

    public GameResult(String name, Difficulty level, int tries, String date) {
        this.name = name;
        this.level = level;
        this.tries = tries;
        this.date = date;
    }

    public Difficulty getLevel() {
        return level;
    }

    public int getTries() {
        return tries;
    }

    public String toFileLine() {
        return name + "," + level.name() + "," + tries + "," + date;
    }

    public static GameResult fromFileLine(String line) {
        String[] parts = line.split(",", 4);

        if (parts.length < 4) {
            return null;
        }

        String name = parts[0];
        Difficulty level = Difficulty.valueOf(parts[1]);
        int tries = Integer.parseInt(parts[2]);
        String date = parts[3];

        return new GameResult(name, level, tries, date);
    }

    @Override
    public String toString() {
        return name + " | " + level + " | Attempts: " + tries;
    }
}
