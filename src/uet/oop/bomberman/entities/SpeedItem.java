package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item{
    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setImg() {
        if (explosed == false) {
            Timeline t = new Timeline();
            t.setCycleCount(1);

            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(5000),
                    (ActionEvent event) -> {
                        img = Sprite.powerup_speed.getFxImage();
                        explosed = true;
                    }
            ));
            t.play();
        }
    }
}
