package code;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;


/**
 * Created by Kristoffer on 2017-05-08.
 */

public class Game {
    private AnimationTimer gameLoop;
    private View view;
    private ImageView overlay;
    private Background background;
    private Ground ground;
    private Bird bird;
    private double backgroundScrollSpeed = 0.8;
    private double groundScrollSpeed = 5;
    public static double SCREEN_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    public static double SCREEN_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
    private Canvas canvas;
    private GraphicsContext gc;
    ArrayList<String> input;
    private RotateTransition rotateTransition;


    public Game(Stage primaryStage) {
        init(primaryStage);
        startGameLoop();
    }

    public void init(Stage primaryStage) {
        view = new View();
        canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        input = new ArrayList<String>();
        overlay = view.GameOverlay();

        background = new Background(SCREEN_HEIGHT, SCREEN_WIDTH);
        ground = new Ground();
        bird = new Bird();

        view.createView(primaryStage);
        view.addNode(background);
        view.addNode(ground);
        view.addNode(canvas);

        manageKeyEvents();
    }

    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            boolean isPlaying = false;
            @Override
            public void handle(long currentNanoTime) {
                if(input.contains("SPACE")) isPlaying = true;

                background.scrollBackground(backgroundScrollSpeed);
                ground.scrollGround(groundScrollSpeed);
                bird.updateBird(gc, input, isPlaying);
                gameOverlay(isPlaying);
            }
        };
        gameLoop.start();
    }

    private void gameOverlay(boolean isPlaying) {
        if(!isPlaying && !view.isExistingNode(overlay)) {
            view.addNode(overlay);
        }
        else if(isPlaying && view.isExistingNode(overlay)){
            view.removeNode(overlay);
        }
    }

    private void manageKeyEvents() {
        view.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String code = event.getCode().toString();

                if(!input.contains(code)) {
                    input.add(code);
                }
            }
        });

        view.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String code = event.getCode().toString();
                input.remove(code);
            }
        });
    }
}
