package code;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by Kristoffer on 2017-05-08.
 */
public class View {
    Group root; //Layer of nodes

    public View() {
        root = new Group();
    }

    public void createView(Stage primaryStage, double SCREEN_HEIGHT, double SCREEN_WIDTH) {
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        primaryStage.setTitle("Noisy Bird");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); //Maximize window
        primaryStage.show();
    }

    public void addNode(Node node) {
        root.getChildren().add(node);
    }

}
