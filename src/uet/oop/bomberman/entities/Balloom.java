package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Balloom extends Entity{
    private double v = 4;

    public int getX[] = {-1, 0, 1, 0};
    public int getY[] = {0, -1, 0, 1};

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    public void run() {
        Random ran = new Random();
        int d = ran.nextInt(4);
        int xx = x / Sprite.SCALED_SIZE;
        int yy = y / Sprite.SCALED_SIZE;
        while (!(BombermanGame.ObjectMap[yy+getY[d]][xx+getX[d]] instanceof Grass))  {
            d = ran.nextInt(4);
        }
        int d1 = d;
        Timeline t = new Timeline();
        t.setCycleCount(8);
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(150),
                (ActionEvent event) -> {
                    x += getX[d1] * v;
                    y += getY[d1] * v;
                }
        ));
        t.play();
    }
    public void runB() {
        Timeline t = new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(1300),
                (ActionEvent event) -> {
                    this.run();
                }
        ));
        t.play();
    }
    @Override
    public void update() {

    }
}
