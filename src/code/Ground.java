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
    private String groundPath = "/assets/pictures/ground.png";

    public Ground() {
        groundImage = new Image(groundPath);
        groundImageView = new ImageView(groundImage);
        groundImageView.setLayoutY(Game.SCREEN_HEIGHT - groundImage.getHeight());
        this.getChildren().add(groundImageView);
    }
}
