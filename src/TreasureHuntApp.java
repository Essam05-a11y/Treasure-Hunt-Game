import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TreasureHuntApp extends Application {

    @Override
    public void start(Stage stage) {
        GameView view = new GameView();
        GameController ctrl = new GameController(view);

        Scene scene = new Scene(view.getRoot(), 900, 620);

        stage.setTitle("Treasure Hunt Grid Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
