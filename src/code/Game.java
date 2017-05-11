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

import java.util.ArrayList;


/**
 * Created by Kristoffer G. and Timas L. on 2017-05-08.
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

    /**
     * Method which starts the game.
     * @param primaryStage Stage object JavaFX
     */
    public Game(Stage primaryStage) {
        init(primaryStage);
        manageKeyEvents();
        startGameLoop();
    }

    /**
     * Initializes all objects on screen.
     * @param primaryStage Stage object for JavaFX
     */
    public void init(Stage primaryStage) {
        view = new View();
        canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        input = new ArrayList<String>();
        overlay = view.GameReadyOverlay();

        background = new Background(SCREEN_HEIGHT, SCREEN_WIDTH);
        ground = new Ground();
        bird = new Bird();

        view.createView(primaryStage);
        view.addNode(background);
        view.addNode(ground);
        view.addNode(canvas);
    }

    /**
     * This method contains all nodes which needs updating. Everything inside handle is called every frame.
     */
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

    /**
     * Depending on the value of isPlaying, it will either display the intro overlay or it will remove it.
     * @param isPlaying false before the player starts the game, true when the player is playing.
     */
    private void gameOverlay(boolean isPlaying) {
        if(!isPlaying && !view.isExistingNode(overlay)) {
            view.addNode(overlay);
        }
        else if(isPlaying && view.isExistingNode(overlay)){
            view.removeNode(overlay);
        }
    }

    /**
     * Adds key presses to the input ArrayList when the key is pressed down and removes them when the key is released.
     */
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
