package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Entity {
    public static boolean explosed = false;
    public static boolean hasEnemy = true;
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
                            explosed = true;
                    }
            ));
            t.play();
        }
    }

    public boolean checkItem() {
        for (Entity e : BombermanGame.entities) {
            if (e instanceof Balloom || e instanceof Oneal || e instanceof Doll || e instanceof Kondoria)
                return true;
        }
        return false;
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
        if (!checkItem()) {
            hasEnemy = false;
        }
    }
}
