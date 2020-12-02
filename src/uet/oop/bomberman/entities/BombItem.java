package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item{
    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void setImg() {
        if (explosed == false) {
            Timeline t = new Timeline();
            t.setCycleCount(1);

            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(5000),
                    (ActionEvent event) -> {
                        img = Sprite.powerup_bombs.getFxImage();
                        explosed = true;
                    }
            ));
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(35000),
                    (ActionEvent event) -> {
                    }
            ));
            t.play();
        }
    }

    @Override
    public void checkItem() {
        if (explosed) {
            super.delete();
            BombermanGame.n_bomb++;
        }
    }

    @Override
    public void update() {

    }
}
