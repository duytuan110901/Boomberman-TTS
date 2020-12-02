package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Entity {
    public  static boolean explosed = false;
    public Portal (int x, int y, Image img) {
        super(x, y, img);
    }

    public void setImg() {
        if (explosed == false) {
            Timeline t = new Timeline();
            t.setCycleCount(1);

            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(5000),
                    (ActionEvent event) -> {
                        img = Sprite.portal.getFxImage();
                    }
            ));
            t.play();
        }
    }

    public void checkItem() {
        if (Balloom.n_Balloom == 0 && Oneal.n_oneal == 0) {
            explosed = true;
        }
    }

    public void endGame() { ;
        Timeline t = new Timeline();
        t.setCycleCount(1);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(230),
                (ActionEvent event) -> {

                }
        ));
        t.play();
    }

    @Override
    public void update() {
        checkItem();
    }
}
