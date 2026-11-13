import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameView {

    private BorderPane root;
    private TextField nameTxt;
    private ComboBox<Difficulty> levelBox;
    private Button startBtn;
    private Button restartBtn;
    private Button scoresBtn;

    private Label msgLbl;
    private Label tryLbl;
    private Label bestLbl;

    private GridPane grid;
    private Button[][] btns;
    private TextArea scoresArea;

    public GameView() {
        root = new BorderPane();
        root.setPadding(new Insets(12));

        makeTop();
        makeLeft();
        makeCenter();
        makeBottom();
    }

    private void makeTop() {
        Label title = new Label("Treasure Hunt Grid Game");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Label nameLbl = new Label("Name:");
        nameTxt = new TextField();
        nameTxt.setPrefWidth(170);

        Label levelLbl = new Label("Difficulty:");
        levelBox = new ComboBox<>();
        levelBox.getItems().addAll(Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD);
        levelBox.setValue(Difficulty.EASY);
        levelBox.setPrefWidth(140);

        startBtn = new Button("Start Game");
        restartBtn = new Button("Restart");
        scoresBtn = new Button("Show Scores");

        HBox form = new HBox(14, nameLbl, nameTxt, levelLbl, levelBox, startBtn, restartBtn, scoresBtn);
        form.setAlignment(Pos.CENTER);

        VBox topBox = new VBox(14, title, form);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(5, 5, 15, 5));

        root.setTop(topBox);
    }

    private void makeLeft() {
        msgLbl = new Label("Enter your name and start the game.");
        tryLbl = new Label("Attempts: 0");
        bestLbl = new Label("Best Score: -");

        msgLbl.setWrapText(true);
        tryLbl.setWrapText(true);
        bestLbl.setWrapText(true);

        VBox leftBox = new VBox(18, msgLbl, tryLbl, bestLbl);
        leftBox.setPadding(new Insets(10));
        leftBox.setPrefWidth(320);
        leftBox.setMinWidth(320);

        root.setLeft(leftBox);
    }

    private void makeCenter() {
        grid = new GridPane();
        grid.setHgap(7);
        grid.setVgap(7);
        grid.setAlignment(Pos.CENTER);
        root.setCenter(grid);
    }

    private void makeBottom() {
        scoresArea = new TextArea();
        scoresArea.setPrefHeight(110);
        scoresArea.setEditable(false);
        scoresArea.setText("Saved Scores:");
        root.setBottom(scoresArea);
    }

    public void makeGrid(int size, GameController ctrl) {
        grid.getChildren().clear();
        btns = new Button[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Button btn = new Button("?");
                btn.setPrefSize(70, 60);
                btn.setStyle(normalStyle());

                final int r = row;
                final int c = col;

                btn.setOnAction(e -> ctrl.play(r, c));

                btns[row][col] = btn;
                grid.add(btn, col, row);
            }
        }
    }

    public void showWrong(int row, int col) {
        Button btn = btns[row][col];
        btn.setText("X");
        btn.setDisable(true);
        btn.setStyle(wrongStyle());
    }

    public void showTreasure(int row, int col) {
        Button btn = btns[row][col];
        btn.setText("T");
        btn.setDisable(true);
        btn.setStyle(treasureStyle());
    }

    public void lockGrid() {
        if (btns == null) {
            return;
        }

        for (int row = 0; row < btns.length; row++) {
            for (int col = 0; col < btns[row].length; col++) {
                btns[row][col].setDisable(true);
            }
        }
    }

    private String normalStyle() {
        return "-fx-background-color: #F2F2F2;" +
               "-fx-text-fill: #8B99A3;" +
               "-fx-border-color: #D8D8D8;" +
               "-fx-font-size: 18px;" +
               "-fx-font-weight: bold;";
    }

    private String wrongStyle() {
        return "-fx-background-color: #F8D7DA;" +
               "-fx-text-fill: #B02A37;" +
               "-fx-border-color: #D6A5AE;" +
               "-fx-font-size: 20px;" +
               "-fx-font-weight: bold;" +
               "-fx-opacity: 1;";
    }

    private String treasureStyle() {
        return "-fx-background-color: #F4E285;" +
               "-fx-text-fill: #8A6D1D;" +
               "-fx-border-color: #D4C16A;" +
               "-fx-font-size: 20px;" +
               "-fx-font-weight: bold;" +
               "-fx-opacity: 1;";
    }

    public BorderPane getRoot() {
        return root;
    }

    public String getNameText() {
        return nameTxt.getText();
    }

    public Difficulty getLevel() {
        return levelBox.getValue();
    }

    public Button getStartBtn() {
        return startBtn;
    }

    public Button getRestartBtn() {
        return restartBtn;
    }

    public Button getScoresBtn() {
        return scoresBtn;
    }

    public void setMessage(String text) {
        msgLbl.setText(text);
    }

    public void setAttempts(int tries) {
        tryLbl.setText("Attempts: " + tries);
    }

    public void setBest(String text) {
        bestLbl.setText("Best Score: " + text);
    }

    public void setScores(String text) {
        scoresArea.setText(text);
    }
}
