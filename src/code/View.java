package code;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Kristoffer G. & Timas L. on 2017-05-08.
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
        //primaryStage.setMaximized(true); //Maximize window
        //primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Adds a node to the root.
     * @param node node to be added
     */
    public void addNode(Node node) {
        root.getChildren().add(node);
    }

    /**
     * Removes a node from the root.
     * @param node node to be removed
     */
    public void removeNode(Node node) {
        root.getChildren().remove(node);
    }

    /**
     * Checks whether a node is already added to the root.
     * @param node node to be checked
     * @return true if node exists, false if it does not
     */
    public boolean isExistingNode(Node node) {
        return root.getChildren().contains(node);
    }

    /**
     * Creates an overlay display prompting the user to start playing.
     * @return the overlay as an ImageView object
     */
    public ImageView gameOverlay(String overlayPath) {
        ImageView overlay = new ImageView(overlayPath);
        overlay.setFitWidth(Game.SCREEN_WIDTH/4);
        overlay.setFitHeight(Game.SCREEN_HEIGHT/10);
        overlay.setLayoutX(Game.SCREEN_WIDTH/2 - overlay.getFitWidth()/2);
        overlay.setLayoutY(Game.SCREEN_HEIGHT/4 - overlay.getFitHeight()/2);

        return overlay;
    }

    /**
     * Displays points
     * @return points
     */
    public Text displayPoints() {
        Text points = new Text(Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT/6, "0");
        points.setFont(new Font(72));
        points.setFill(Color.WHITE);
        return points;
    }

}
