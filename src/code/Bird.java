package code;


import javafx.animation.RotateTransition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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

    public Bird() {
        bird = new Image(characterImagePath, 0, 120, true,true,true);

        startingY = Game.SCREEN_HEIGHT/2 - bird.getRequestedHeight();
        constantX = Game.SCREEN_WIDTH/4;
        currentY = startingY;
        gravity = 1;
        falling = true;
        maxSpeedY = 10;
    }

    /**
     * Method for how the character falls
     */
    public void fall() {
        if (falling) {
            speedY += gravity;
            if (speedY > maxSpeedY) {
                speedY = maxSpeedY;
            }
            currentY += speedY;
        }
    }

    /**
     * Method for how the character moves up on the y-axis.
     */
    public void jump() {
        if (!falling) {
            speedY -= gravity;
            if(speedY < maxSpeedY) {
                speedY = maxSpeedY;
            }
            currentY -= speedY;
        }
    }

    /**
     * Controls how the bird is drawn on the canvas and if it is colliding with the ground.
     * COLLISION CHECKING IN THIS METHOD SHOULD BE WRITTEN SEPARATELY ELSEWHERE.
     * @param gc GraphicsContext object from a canvas
     * @param input ArrayList containing key presses
     * @param isPlaying boolean determining whether the game is playing or paused.
     */
    public void updateBird(GraphicsContext gc, ArrayList input, boolean isPlaying, double t) {
        gc.clearRect(0,0,Game.SCREEN_WIDTH,Game.SCREEN_HEIGHT);

        if(!isPlaying) {
            currentY = 100*Math.sin(5*t/1000000000)+startingY;
        }
        if (input.contains("SPACE") && currentY >= 0) {
            speedY = 0;
            falling = false;
            jump();
            gc.drawImage(bird, constantX, currentY);
        }
        else {
            if(!falling) {
                speedY = 0;
                falling = true;
            }
            fall();

            if (currentY >= (Game.SCREEN_HEIGHT - Ground.groundHeight) - bird.getRequestedHeight() + 3) {
                currentY = (Game.SCREEN_HEIGHT - Ground.groundHeight - bird.getRequestedHeight() + 3);
            }

            gc.drawImage(bird, constantX, currentY);
        }
    }
}