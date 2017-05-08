package code;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Created by Kristoffer on 2017-05-08.
 */
public class Launcher extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            new Game(primaryStage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
