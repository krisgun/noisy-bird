package code;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * Created by Kristoffer G. & Timas L. on 2017-05-08.
 */
public class Obstacles {
    private Image upperObstacle;
    private Image lowerObstacle;
    private String upperObstaclePath = "/assets/pictures/obstacle_roof.png";
    private String lowerObstaclePath = "/assets/pictures/obstacle_ground.png";
    private double upperY;
    private double lowerY;
    protected double currentX;
    private double startingX;
    private Random rand;
    private final double GAP = Bird.bird.getRequestedHeight()*2;
    private Rectangle2D upperHitBox;
    private Rectangle2D lowerHitBox;

    public Obstacles() {
        rand = new Random();
        createObstacles();
        upperY = 0;
        startingX = Game.SCREEN_WIDTH + upperObstacle.getWidth();
    }

    /**
     * Updates the obstacle positions and draw them
     * @param GraphicsContext
     * @param isPlaying
     * @param obstacleSpeed
     */
    public void updateObstacles(GraphicsContext gc, boolean isPlaying, double obstacleSpeed) {
        if (!isPlaying) {
            currentX = startingX;
        }

        currentX -= obstacleSpeed;
        gc.drawImage(upperObstacle, currentX, upperY);
        gc.drawImage(lowerObstacle,currentX, lowerY);


        if (currentX <= -upperObstacle.getWidth() & currentX <= -lowerObstacle.getWidth()){
            currentX = startingX;
            createObstacles();
        }
    }

    /**
     * Creates the obstacles
     */
    private void createObstacles(){
        double maxHeight = Game.SCREEN_HEIGHT / 2;
        double minHeight = Game.SCREEN_HEIGHT / 6;
        double upperHeight = minHeight + (maxHeight - minHeight) * rand.nextDouble();
        double lowerHeight = Game.SCREEN_HEIGHT - Ground.groundHeight - upperHeight - GAP;
        upperObstacle = new Image(upperObstaclePath, 0, upperHeight, true, true, true);
        lowerObstacle = new Image(lowerObstaclePath, 0, lowerHeight, true, true, true);
        lowerY = Game.SCREEN_HEIGHT - Ground.groundHeight - lowerObstacle.getRequestedHeight() + 25;
    }

    /**
     * Updates the hitBox around the obstacles, by creating new ones with appropriate positions.
     */
    public void updateHitBox(){
        upperHitBox = new Rectangle2D(currentX + upperObstacle.getWidth()/4,upperY,upperObstacle.getWidth()/8,upperObstacle.getRequestedHeight());
        lowerHitBox = new Rectangle2D(currentX + lowerObstacle.getWidth()/4,lowerY,lowerObstacle.getWidth()/8,upperObstacle.getRequestedHeight()+100);
    }

    /**
     * Getter for upperHitBox
     * @return upperHitBox
     */
    public Rectangle2D getUpperHitBox(){
        return upperHitBox;
    }

    /**
     * Getter for lowerHitBox
     * @return lowerHitBox
     */
    public Rectangle2D getLowerHitBox(){
        return lowerHitBox;
    }
}
