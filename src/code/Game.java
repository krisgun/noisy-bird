package code;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    private Background background;
    private Ground ground;
    private Bird bird;
    private double backgroundScrollSpeed = 0.4;
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
        background = new Background(SCREEN_HEIGHT, SCREEN_WIDTH);
        ground = new Ground();
        bird = new Bird();

        view.createView(primaryStage);
        view.addNode(background);
        view.addNode(ground);

        canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);

        gc = canvas.getGraphicsContext2D();

        view.addNode(canvas);

        input = new ArrayList<String>();

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

    private void startGameLoop() {

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                gc.clearRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

                background.scrollBackground(backgroundScrollSpeed);
                ground.scrollGround(groundScrollSpeed);

                if (input.contains("SPACE")) {
                    bird.speedY = 0;
                    bird.falling = false;
                    bird.jump();
                    gc.drawImage(bird.birdImage, bird.startingX, bird.currentY);
                }
                else {
                    if(bird.falling == false){
                        bird.speedY = 0;
                        bird.falling = true;
                    }
                    bird.fall();
                    gc.drawImage(bird.birdImage, bird.startingX, bird.currentY );
                }
            }
        };
        gameLoop.start();
    }
}
