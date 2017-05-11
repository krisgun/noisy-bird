package code;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Created by Kristoffer on 2017-05-08.
 */
public class View {
    private Group root; //Layer of nodes
    protected Scene scene;

    public View() {
        root = new Group();
    }

    public void createView(Stage primaryStage) {
        scene = new Scene(root, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        primaryStage.setTitle("Noisy Bird");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); //Maximize window
        //primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void addNode(Node node) {
        root.getChildren().add(node);
    }

    public void removeNode(Node node) {
        root.getChildren().remove(node);
    }

    public boolean isExistingNode(Node node) {
        return root.getChildren().contains(node);
    }

    public ImageView GameOverlay() {
        ImageView overlay = new ImageView("/assets/pictures/get_ready.png");
        overlay.setFitWidth(Game.SCREEN_WIDTH/4);
        overlay.setFitHeight(Game.SCREEN_HEIGHT/10);
        overlay.setLayoutX(Game.SCREEN_WIDTH/2 - overlay.getFitWidth()/2);
        overlay.setLayoutY(Game.SCREEN_HEIGHT/4 - overlay.getFitHeight()/2);

        return overlay;
    }

}
