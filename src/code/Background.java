package code;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Created by Kristoffer G. & Timas L. on 2017-05-08.
 */
public class Background extends Pane {

    ImageView backgroundImageViewPrimary;
    ImageView backgroundImageViewSecondary;
    String backgroundPath = "../assets/pictures/cave_bg.png";
    double SCREEN_WIDTH;
    double SCREEN_HEIGHT;

    /**
     * Creates two background ImageViews placed next to each other. One is on-screen and the other is off-screen.
     * These are set to the width and height of the monitor.
     * @param SCREEN_HEIGHT height of monitor
     * @param SCREEN_WIDTH height of monitor
     */
    public Background(double SCREEN_HEIGHT, double SCREEN_WIDTH) {
        backgroundImageViewPrimary = new ImageView(Launcher.class.getResource(backgroundPath).toExternalForm());
        backgroundImageViewSecondary = new ImageView(Launcher.class.getResource(backgroundPath).toExternalForm());
        backgroundImageViewSecondary.setFitHeight(SCREEN_HEIGHT);
        backgroundImageViewSecondary.setFitWidth(SCREEN_WIDTH);
        backgroundImageViewPrimary.setFitHeight(SCREEN_HEIGHT);
        backgroundImageViewPrimary.setFitWidth(SCREEN_WIDTH);
        backgroundImageViewSecondary.setLayoutX(SCREEN_WIDTH);

        this.getChildren().add(backgroundImageViewPrimary);
        this.getChildren().add(backgroundImageViewSecondary);

        this.SCREEN_HEIGHT = SCREEN_HEIGHT;
        this.SCREEN_WIDTH = SCREEN_WIDTH;
    }

    /**
     * Calculates how much each background ImageView should be moved on the x-axis and if the image is out of bounds, its position is reset.
     * @param scrollSpeed the value of how much the background should move on the x-axis per frame
     */
    public void scrollBackground(double scrollSpeed) {
        double x1 = backgroundImageViewPrimary.getLayoutX() - scrollSpeed;
        double x2 = backgroundImageViewSecondary.getLayoutX() - scrollSpeed;

        if(x1 <= -SCREEN_WIDTH) {
            backgroundImageViewPrimary.setLayoutX(0);
            x1 = backgroundImageViewPrimary.getLayoutX() - scrollSpeed;
        }
        if(x2 <= 0) {
            backgroundImageViewSecondary.setLayoutX(SCREEN_WIDTH);
            x2 = backgroundImageViewSecondary.getLayoutX() - scrollSpeed;
        }
        // move background
        backgroundImageViewPrimary.setLayoutX(x1);
        backgroundImageViewSecondary.setLayoutX(x2);
    }
}
