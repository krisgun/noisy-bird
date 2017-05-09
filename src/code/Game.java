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
    private Ground ground;
    private double backgroundScrollSpeed = 0.4;
    private double groundScrollSpeed = 5;
    public static double SCREEN_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    public static double SCREEN_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    public Game(Stage primaryStage) {
        init(primaryStage);
        startGameLoop();
    }

    public void init(Stage primaryStage) {
        view = new View();
        background = new Background(SCREEN_HEIGHT, SCREEN_WIDTH);
        ground = new Ground();

        view.createView(primaryStage);
        view.addNode(background);
        view.addNode(ground);
    }

    private void startGameLoop() {

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                background.scrollBackground(backgroundScrollSpeed);
                ground.scrollGround(groundScrollSpeed);
            }
        };
        gameLoop.start();
    }
}
