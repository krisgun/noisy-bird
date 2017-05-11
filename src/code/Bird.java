package code;


import javafx.animation.RotateTransition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
/**
 * Created by Kristoffer on 2017-05-08.
 */
public class Bird {
    private Image bird;
    private double constantX;
    private double startingY;
    private double currentY;
    private double speedY;
    private double gravity;
    private boolean falling;
    private double maxSpeedY;

    public Bird() {
        bird = new Image("/assets/pictures/bird.png", 0, 120, true,true,true);

        startingY = Game.SCREEN_HEIGHT/2 - bird.getRequestedHeight();
        constantX = Game.SCREEN_WIDTH/4;
        currentY = startingY;
        gravity = 1;
        falling = true;
        maxSpeedY = 10;
    }

    public void fall() {
        if (falling) {
            speedY += gravity;
            if (speedY > maxSpeedY) {
                speedY = maxSpeedY;
            }
            currentY += speedY;
        }
    }

    public void jump() {
        if (!falling) {
            speedY -= gravity;
            if(speedY < maxSpeedY) {
                speedY = maxSpeedY;
            }
            currentY -= speedY;
        }
    }
  
    public void updateBird(GraphicsContext gc, ArrayList input, boolean isPlaying) {
        gc.clearRect(0,0,Game.SCREEN_WIDTH,Game.SCREEN_HEIGHT);

        if(!isPlaying) {
            currentY = startingY;
        }
        if (input.contains("SPACE")) {
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

            if (currentY >= Game.SCREEN_HEIGHT*(0.66)) {
                currentY = Game.SCREEN_HEIGHT*(0.66);
            }

            gc.drawImage(bird, constantX, currentY);
        }
    }
}