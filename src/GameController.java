public class GameController {

    private GameView view;
    private GameModel model;
    private ScoreFile scoreFile;
    private String playerName;

    public GameController(GameView view) {
        this.view = view;
        model = new GameModel();
        scoreFile = new ScoreFile();

        view.getStartBtn().setOnAction(e -> startGame());
        view.getRestartBtn().setOnAction(e -> startGame());
        view.getScoresBtn().setOnAction(e -> showScores());
    }

    public void startGame() {
        playerName = view.getNameText().trim();

        if (playerName.isEmpty()) {
            view.setMessage("Please enter your name first.");
            return;
        }

        Difficulty level = view.getLevel();

        model.start(level);
        view.makeGrid(model.getSize(), this);
        view.setMessage("Game started. Choose a square.");
        view.setAttempts(0);
        updateBest();
    }

    public void play(int row, int col) {
        if (playerName == null || playerName.isEmpty()) {
            view.setMessage("Start the game first.");
            return;
        }

        boolean found = model.check(row, col);
        view.setAttempts(model.getTries());

        if (found) {
            view.showTreasure(row, col);
            view.setMessage("You found the treasure in " + model.getTries() + " attempts!");

            GameResult result = new GameResult(playerName, model.getLevel(), model.getTries());
            scoreFile.save(result);

            updateBest();
            showScores();
            view.lockGrid();
        } else {
            view.showWrong(row, col);
            String hint = model.getHint(row, col);
            view.setMessage(hint);
        }
    }

    private void updateBest() {
        GameResult best = scoreFile.getBest(view.getLevel());

        if (best == null) {
            view.setBest("-");
        } else {
            view.setBest(best.toString());
        }
    }

    private void showScores() {
        view.setScores(scoreFile.getAllText());
    }
}
