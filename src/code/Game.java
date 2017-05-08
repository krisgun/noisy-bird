package code;

import javafx.animation.AnimationTimer;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by Kristoffer on 2017-05-08.
 */

public class Game {
    private AnimationTimer gameLoop;
    private View view;
    private Background background;
    private double backgroundScrollSpeed = 0.3;
    private double SCREEN_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    private double SCREEN_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    public Game(Stage primaryStage) {
        view = new View();
        view.createView(primaryStage, SCREEN_HEIGHT, SCREEN_WIDTH);
        background = new Background(SCREEN_HEIGHT, SCREEN_WIDTH);
        view.addNode(background);
        startGameLoop();
    }

    private void startGameLoop() {

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                background.scrollBackground(backgroundScrollSpeed);
            }
        };
        gameLoop.start();
    }
}
