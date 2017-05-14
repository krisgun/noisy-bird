package code;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
/**
 * Created by Kristoffer G. & Timas L. on 2017-05-08.
 */
public class Bird {
    public static Image bird;
    private double constantX;
    private double startingY;
    private double currentY;
    private double speedY;
    private double gravity;
    private boolean falling;
    private double maxSpeedY;
    private String characterImagePath =  "/assets/pictures/bird.png";
    private double birdWidth;
    private double birdHeight;
    private Rectangle2D birdHitBox;
    protected boolean isDead;
    protected ImageView birdImage;

    public Bird() {
        bird = new Image(characterImagePath, 145.0, 120, true,true,true);
        birdImage = new ImageView(bird);

        isDead = false;
        startingY = Game.SCREEN_HEIGHT/2 - bird.getRequestedHeight();
        constantX = Game.SCREEN_WIDTH/4;
        currentY = startingY;
        gravity = 1;
        falling = true;
        maxSpeedY = 10;
        birdHeight = bird.getRequestedHeight();
        birdWidth = bird.getRequestedWidth();

        updateHitBox();
    }

    /**
     * Method for how the character falls
     */
    public void fall(Obstacles obstacles) {
        if (falling) {
            speedY += gravity;
            if (speedY > maxSpeedY) {
                speedY = maxSpeedY;
            }
            currentY += speedY;
            updateHitBox();
            if (collisionLower(obstacles) || collisionUpper(obstacles)){
                isDead = true;
            }
        }
    }

    /**
     * Method for how the character moves up on the y-axis.
     */
    public void jump(Obstacles obstacles) {
        if (!falling) {
            speedY -= gravity;
            if(speedY < maxSpeedY) {
                speedY = maxSpeedY;
            }
            currentY -= speedY;
            updateHitBox();

            if(currentY <= 0){
                currentY = 0;
            }

            if (collisionLower(obstacles) || collisionUpper(obstacles)){
                isDead = true;
            }
        }
    }

    /**
     * Controls how the bird is drawn on the canvas and if it is colliding with the ground.
     * COLLISION CHECKING IN THIS METHOD SHOULD BE WRITTEN SEPARATELY ELSEWHERE.
     * @param gc GraphicsContext object from a canvas
     * @param input ArrayList containing key presses
     * @param isPlaying boolean determining whether the game is playing or paused.
     */
    public void updateBird(GraphicsContext gc, ArrayList input, boolean isPlaying, double t, Obstacles obstacles) {
        gc.clearRect(0,0,Game.SCREEN_WIDTH,Game.SCREEN_HEIGHT);

        if(!isPlaying) {
            currentY = 100*Math.sin(5*t/1000000000)+startingY;
        }
        if (input.contains("SPACE")) {
            speedY = 0;
            falling = false;
            obstacles.updateHitBox();
            jump(obstacles);
            gc.drawImage(bird, constantX, currentY);
        }
        else {
            if(!falling) {
                speedY = 0;
                falling = true;
            }
            obstacles.updateHitBox();
            fall(obstacles);

            if (currentY >= (Game.SCREEN_HEIGHT - Ground.groundHeight) - bird.getRequestedHeight() + 3) {
                currentY = (Game.SCREEN_HEIGHT - Ground.groundHeight - bird.getRequestedHeight() + 3);
            }

            gc.drawImage(bird, constantX, currentY);
        }
    }

    public void updateHitBox (){
        birdHitBox = new Rectangle2D(constantX + birdWidth/3, currentY + birdHeight/3, birdWidth/3, birdHeight/3);
    }

    public void die(GraphicsContext gc){
        gc.clearRect(0,0,Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        TranslateTransition downPath = new TranslateTransition(Duration.millis(3000), birdImage);
        downPath.setFromY(currentY);
        downPath.setFromX(constantX);
        downPath.setToY(Game.SCREEN_HEIGHT + birdHeight);

        RotateTransition rotation = new RotateTransition(Duration.millis(3000), birdImage);
        rotation.setByAngle(360);
        rotation.setCycleCount(6);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().add(downPath);
        parallelTransition.getChildren().add(rotation);



        parallelTransition.play();
    }

    private boolean collisionUpper (Obstacles obstacles){
        return obstacles.getUpperHitBox().intersects(birdHitBox);
    }

    private boolean collisionLower (Obstacles obstacles){
        return obstacles.getLowerHitBox().intersects(birdHitBox);
    }
}