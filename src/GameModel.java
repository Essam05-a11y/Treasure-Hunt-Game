import java.util.Random;

public class GameModel {

    private int size;
    private int tries;
    private Position treasure;
    private boolean[][] used;
    private Difficulty level;
    private HintStrategy hint;

    public GameModel() {
        hint = new SimpleHintStrategy();
    }

    public void start(Difficulty level) {
        this.level = level;
        this.size = level.getSize();
        this.tries = 0;
        this.used = new boolean[size][size];

        Random rand = new Random();
        int row = rand.nextInt(size);
        int col = rand.nextInt(size);
        treasure = new Position(row, col);
    }

    public boolean check(int row, int col) {
        tries++;
        used[row][col] = true;

        Position guess = new Position(row, col);
        return guess.equals(treasure);
    }

    public String getHint(int row, int col) {
        Position guess = new Position(row, col);
        return hint.makeHint(guess, treasure);
    }

    public int getSize() {
        return size;
    }

    public int getTries() {
        return tries;
    }

    public Difficulty getLevel() {
        return level;
    }
}
