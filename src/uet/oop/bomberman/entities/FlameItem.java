package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class FlameItem extends Item {

    public int count = 0;
    public int getX[] = {-1, 0, 1, 0};
    public int getY[] = {0, -1, 0, 1};
    public FlameItem(int x, int y, Image img) {
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
                        img = Sprite.powerup_flames.getFxImage();
                        explosed = true;
                    }
            ));
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(35000),
                    (ActionEvent event) -> {
                        super.delete();
                    }
            ));
            t.play();
        }
    }

    @Override
    public void checkItem() {
        if (this.x == x && this.y == y) {
            super.delete();
            BombermanGame.n_flame++;
        }
    }

    @Override
    public void update() {

    }
}
