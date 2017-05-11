package code;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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

}
