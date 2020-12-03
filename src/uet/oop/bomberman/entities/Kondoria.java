package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kondoria extends Entity {
    private long timeDie;
    private double v = 8;
    public static int n_Balloom = 0;
    public int heart = 2;

    public int getX[] = {-1, 0, 1, 0, 0};
    public int getY[] = {0, -1, 0, 1, 0};

    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
    }

    public void run() {
        Random ran = new Random();
        int d = ran.nextInt(5);
        int xx = x / Sprite.SCALED_SIZE;
        int yy = y / Sprite.SCALED_SIZE;
        while (!(BombermanGame.ObjectMap[yy+getY[d]][xx+getX[d]] instanceof Grass) || BombermanGame.BombMap[yy+getY[d]][xx+getX[d]]==1)  {
            d = ran.nextInt(5);
        }
        int d1 = d;
        Timeline t = new Timeline();
        t.setCycleCount(4);
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

    public void die() {
        if (heart == 1) {
            Timeline t = new Timeline();
            t.setCycleCount(1);

            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(300),
                    (ActionEvent event) -> {
                        img = Sprite.kondoria_dead.getFxImage();
                        Sound.play("AA126_11");
                    }
            ));
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(800),
                    (ActionEvent event) -> {
                        BombermanGame.entities.remove(this);

                    }
            ));
            t.play();
        } else {
            Timeline t = new Timeline();
            t.setCycleCount(1);

            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(300),
                    (ActionEvent event) -> {
                        img = Sprite.kondoria_dead.getFxImage();
                        Sound.play("AA126_11");
                    }
            ));
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(1200),
                    (ActionEvent event) -> {
                        //BombermanGame.entities.remove(this);
                        img = Sprite.kondoria_right1.getFxImage();
                        heart = 1;
                    }
            ));
            /**t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(1000),
                    (ActionEvent event) -> {
                        Kondoria newK = new Kondoria(x/32, y/32, Sprite.kondoria_right1.getFxImage());
                        newK.heart = 1;
                        BombermanGame.entities.add(newK);
                        Kondoria newK2 = new Kondoria(x/32, y/32, Sprite.kondoria_right1.getFxImage());
                        newK2.heart = 1;
                        BombermanGame.entities.add(newK2);
                    }
            ));*/
            t.play();
        }
    }

    public boolean checkColision(Bomber b) {
        int topBall = y ;
        int bottomBall = y + 30;
        int rightBall = x + 28;
        int leftBall = x + 4;

        int topBomber = b.getY() ;
        int bottomBomber = b.getY() + 30;
        int rightBomber = b.getX() + 20;
        int leftBomber = b.getX() ;

        if (bottomBall <= topBomber) {
            return false;
        }
        if (topBall >= bottomBomber) {
            return false;
        }
        if (rightBall <= leftBomber) {
            return false;
        }
        if (leftBall >= rightBomber) {
            return false;
        }
        return true;
    }
    @Override
    public void update() {
        if (BombermanGame.bomberman != null) {
            List<Bomber> l = new ArrayList<Bomber>();
            for (Entity e : BombermanGame.entities) {
                if (e instanceof Bomber) {
                    l.add((Bomber) e);
                }
            }
            for (Bomber e : l) {
                if (checkColision(e)) {
                    e.die();
                    BombermanGame.bomberman = null;
                }
            }
        }
    }
}
