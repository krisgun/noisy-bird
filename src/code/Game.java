package code;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * Created by Kristoffer G. and Timas L. on 2017-05-08.
 */

public class Game {
    private AnimationTimer gameLoop;
    private View view;
    private ImageView readyOverlay;
    private String readyOverlayPath = "assets/pictures/get_ready.png";
    private ImageView endOverlay;
    private String endOverlayPath = "assets/pictures/end.png";
    private Background background;
    private Ground ground;
    private Bird bird;
    private Obstacles obstacles;
    Text score;
    int points = 0;
    static boolean hasDied = false;
    private double backgroundScrollSpeed = 0.8;
    private double groundScrollSpeed = 5;
    public static double SCREEN_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    public static double SCREEN_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<String> input;

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
        readyOverlay = view.gameOverlay(readyOverlayPath);
        endOverlay = view.gameOverlay(endOverlayPath);
        score = view.displayPoints();

        background = new Background(SCREEN_HEIGHT, SCREEN_WIDTH);
        ground = new Ground();
        bird = new Bird();
        obstacles = new Obstacles();

        view.createView(primaryStage);
        view.addNode(background);
        view.addNode(ground);
        view.addNode(canvas);
        view.addNode(score);
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

                if (!bird.isDead) {
                    background.scrollBackground(backgroundScrollSpeed);
                    ground.scrollGround(groundScrollSpeed);
                    bird.updateBird(gc, input, isPlaying, currentNanoTime, obstacles);
                    obstacles.updateObstacles(gc, isPlaying, groundScrollSpeed);
                    gameReadyOverlay(isPlaying);
                    updatePoints(bird.constantX, obstacles.currentX);
                }
                else{
                    hasDied = true;
                    gameLoop.stop();
                    bird.die(gc);
                    obstacles.updateObstacles(gc, hasDied, groundScrollSpeed);
                    view.addNode(bird.birdImage);
                    gameEndOverlay(hasDied);
                }
            }
        };
        gameLoop.start();
    }

    /**
     * Updates the points when the bird position is the same as the obstacle positions
     * @param birdPostion, obstaclePosition
     */
    private void updatePoints(double birdPosition, double obstaclePosition) {
        if(birdPosition == obstaclePosition) {
            points++;
        }
        score.setText(Integer.toString(points));
    }

    /**
     * Depending on the value of isPlaying, it will either display the intro readyOverlay or it will remove it.
     * @param isPlaying false before the player starts the game, true when the player is playing.
     */
    private void gameReadyOverlay(boolean isPlaying) {
        if(!isPlaying && !view.isExistingNode(readyOverlay)) {
            view.addNode(readyOverlay);
        }
        else if(isPlaying && view.isExistingNode(readyOverlay)){
            view.removeNode(readyOverlay);
        }
    }

    /**
     *  Displays the overlay when the player has died.
     * @param hasDied
     */
    private void gameEndOverlay(boolean hasDied) {
        if(hasDied  && !view.isExistingNode(endOverlay)) {
            view.addNode(endOverlay);
        }
        else if (!hasDied && view.isExistingNode(endOverlay)) {
            view.removeNode(endOverlay);
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
