package code;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Created by Kristoffer G. & Timas L. on 2017-05-08.
 */
public class Launcher extends Application {

    public static void main(String[] args){
        launch(args);
    }

    /**
     * Method used by JavaFX to start the program.
     * @param primaryStage Stage used by JavaFX
     * @throws Exception if there is any type of exception it will be thrown and the stack trace will be printed.
     */
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
