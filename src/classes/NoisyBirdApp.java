package classes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 * Created by timmitommi && krisgun on 2017-05-04.
 */
public class NoisyBirdApp extends Application{
    ImageView backgroundImageViewPrimary;
    ImageView backgroundImageViewSecondary;
    Pane backGroundLayer;
    double backgroundScrollSpeed = 0.3;
    private AnimationTimer gameLoop;
    private double SCREEN_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    private double SCREEN_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    public static void main(String[] args){
        launch(args);
    }

    /**
     * Main game loop
     * @param primaryScene primary scene
     * @throws Exception exception to be thrown
     */
    @Override
    public void start(Stage primaryScene) throws Exception {
        try {
            Group root = new Group();
            backGroundLayer = new Pane(); // Background Pane
            root.getChildren().add(backGroundLayer);
            Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
            primaryScene.setTitle("Noisy Bird");
            primaryScene.setScene(scene);
            primaryScene.setMaximized(true); //Maximize window
            primaryScene.show();
            loadScene(); // Load background
            startGameLoop();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads background assets and fits them to the screen. Two background images are placed next to each other to create the inifitie loop effect.
     */
    private void loadScene() {

        backgroundImageViewPrimary = new ImageView(NoisyBirdApp.class.getResource("assets/pictures/cave_bg.png").toExternalForm());
        backgroundImageViewSecondary = new ImageView(NoisyBirdApp.class.getResource("assets/pictures/cave_bg.png").toExternalForm());

        backgroundImageViewSecondary.setFitHeight(SCREEN_HEIGHT);
        backgroundImageViewSecondary.setFitWidth(SCREEN_WIDTH);
        backgroundImageViewPrimary.setFitHeight(SCREEN_HEIGHT);
        backgroundImageViewPrimary.setFitWidth(SCREEN_WIDTH);

        backgroundImageViewSecondary.setLayoutX(SCREEN_WIDTH);
        backGroundLayer.getChildren().add(backgroundImageViewPrimary);
        backGroundLayer.getChildren().add(backgroundImageViewSecondary);
    }

    /**
     * Scrolls the background and if the image is "outside" the screen its position is reset.
     */
    private void startGameLoop() {

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {

                double x1 = backgroundImageViewPrimary.getLayoutX() - backgroundScrollSpeed;
                double x2 = backgroundImageViewSecondary.getLayoutX() - backgroundScrollSpeed;

                if(x1 <= -SCREEN_WIDTH) {
                    backgroundImageViewPrimary.setLayoutX(0);
                    x1 = backgroundImageViewPrimary.getLayoutX() - backgroundScrollSpeed;
                }
                if(x2 <= 0) {
                    backgroundImageViewSecondary.setLayoutX(SCREEN_WIDTH);
                    x2 = backgroundImageViewSecondary.getLayoutX() - backgroundScrollSpeed;
                }
                // move background
                backgroundImageViewPrimary.setLayoutX(x1);
                backgroundImageViewSecondary.setLayoutX(x2);
            }
        };
        gameLoop.start();
    }
}
