package code;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * Created by Kristoffer G. & Timas L. on 2017-05-08.
 */
public class Obstacles {
    private Image upperObstacle;
    private Image lowerObstacle;
    private String upperObstaclePath = "/assets/pictures/obstacle_ground.png";
    private String lowerObstaclePath = "/assets/pictures/obstacle_roof.png";
    private double upperY;
    private double lowerY;
    private double currentX1;
    private double currentX2;
    private double startingX;
    private Random rand;
    private final double GAP = Bird.bird.getRequestedHeight()*2;


    public Obstacles() {
        rand = new Random();
        createObstacles();
        upperY = 0;
        startingX = Game.SCREEN_WIDTH + upperObstacle.getWidth();
    }

    public void updateObstacles(GraphicsContext gc, boolean isPlaying, double obstacleSpeed) {
        if (!isPlaying) {
            currentX1 = startingX;
            currentX2 = startingX;
        }

        currentX1 -= obstacleSpeed;
        gc.drawImage(upperObstacle, currentX1, upperY);
        gc.drawImage(lowerObstacle,currentX1, lowerY);

       /* if (currentX1 <= Game.SCREEN_WIDTH/2-upperObstacle.getWidth()/2){
            currentX2 -= obstacleSpeed;
            gc.drawImage(upperObstacle, currentX2, upperY);
            gc.drawImage(lowerObstacle,currentX2, lowerY);
        }*/

        if (currentX1 <= -upperObstacle.getWidth() & currentX1 <= -lowerObstacle.getWidth()){
            currentX1 = startingX;
            createObstacles();
        }

       /* if (currentX2 <= -upperObstacle.getWidth()){
            currentX2 = startingX;
        }*/



    }

    private void createObstacles(){
        double maxHeight = Game.SCREEN_HEIGHT / 2;
        double minHeight = Game.SCREEN_HEIGHT / 6;
        double upperHeight = minHeight + (maxHeight - minHeight) * rand.nextDouble();
        double lowerHeight = Game.SCREEN_HEIGHT - Ground.groundHeight - upperHeight - GAP;
        upperObstacle = new Image(upperObstaclePath, 0, upperHeight, true, true, true);
        lowerObstacle = new Image(lowerObstaclePath, 0, lowerHeight, true, true, true);
        lowerY = Game.SCREEN_HEIGHT - Ground.groundHeight - lowerObstacle.getRequestedHeight() + 25;
    }
}

