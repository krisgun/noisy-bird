package code;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * Created by Kristoffer on 2017-05-08.
 */
public class Ground extends Pane {
    private Image groundImage;
    private ImageView groundImageView;
    private ImageView groundImageView2;
    private String groundPath = "/assets/pictures/ground_long.png";

    public Ground() {
        groundImage = new Image(groundPath);
        groundImageView = new ImageView(groundImage);
        groundImageView2 = new ImageView(groundImage);

        groundImageView.setFitWidth(Game.SCREEN_WIDTH);
        groundImageView.setFitHeight(Game.SCREEN_HEIGHT / 5);
        groundImageView.setLayoutY(Game.SCREEN_HEIGHT - groundImageView.getFitHeight());

        groundImageView2.setFitWidth(Game.SCREEN_WIDTH);
        groundImageView2.setFitHeight(Game.SCREEN_HEIGHT / 5);
        groundImageView2.setLayoutY(Game.SCREEN_HEIGHT - groundImageView.getFitHeight());

        groundImageView2.setLayoutX(Game.SCREEN_WIDTH);

        this.getChildren().add(groundImageView);
        this.getChildren().add(groundImageView2);
    }

    public void scrollGround(double scrollSpeed) {
        double x1 = groundImageView.getLayoutX() - scrollSpeed;
        double x2 = groundImageView2.getLayoutX() - scrollSpeed;

        if(x1 <= -Game.SCREEN_WIDTH) {
            groundImageView.setLayoutX(0);
            x1 = groundImageView.getLayoutX() - scrollSpeed;
        }
        if(x2 <= 0) {
            groundImageView2.setLayoutX(Game.SCREEN_WIDTH);
            x2 = groundImageView2.getLayoutX() - scrollSpeed;
        }
        // move background
        groundImageView.setLayoutX(x1);
        groundImageView2.setLayoutX(x2);
    }

    public double getGroundHeight() {
        return groundImageView.getFitHeight();
    }

    public double getGroundWidth() {
        return groundImageView.getFitWidth();
    }
}
