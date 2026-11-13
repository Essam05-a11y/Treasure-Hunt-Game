import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreFile {

    private String fileName = "scores.txt";

    public void save(GameResult result) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(result.toFileLine() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save score: " + e.getMessage());
        }
    }

    public ArrayList<GameResult> readAll() {
        ArrayList<GameResult> list = new ArrayList<>();

        try {
            File file = new File(fileName);

            if (!file.exists()) {
                return list;
            }

            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();

                try {
                    GameResult result = GameResult.fromFileLine(line);

                    if (result != null) {
                        list.add(result);
                    }
                } catch (Exception e) {
                    System.out.println("Bad line skipped.");
                }
            }

            scan.close();
        } catch (Exception e) {
            System.out.println("Could not read scores: " + e.getMessage());
        }

        return list;
    }

    public GameResult getBest(Difficulty level) {
        ArrayList<GameResult> list = readAll();
        GameResult best = null;

        for (GameResult result : list) {
            if (result.getLevel() == level) {
                if (best == null || result.getTries() < best.getTries()) {
                    best = result;
                }
            }
        }

        return best;
    }

    public String getAllText() {
        ArrayList<GameResult> list = readAll();

        String text = "Saved Scores:\n";

        if (list.isEmpty()) {
            return text + "No scores saved yet.";
        }

        for (GameResult result : list) {
            text += result.toString() + "\n";
        }

        return text;
    }
}
