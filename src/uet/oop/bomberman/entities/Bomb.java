package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Bomb extends Entity {
    public double TimeAlive;
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public void explosion() {
        Timeline t = new Timeline();
        t.setCycleCount(1);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(250),
                (ActionEvent event) -> {
                    img = Sprite.bomb_2.getFxImage();
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(500),
                (ActionEvent event) -> {
                    img = Sprite.bomb_1.getFxImage();
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(750),
                (ActionEvent event) -> {
                    img = Sprite.bomb_2.getFxImage();
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(1000),
                (ActionEvent event) -> {
                    img = Sprite.bomb_1.getFxImage();
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(1250),
                (ActionEvent event) -> {
                    img = Sprite.bomb_2.getFxImage();
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(1500),
                (ActionEvent event) -> {
                    img = Sprite.bomb_1.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(1750),
                (ActionEvent event) -> {
                    img = Sprite.bomb.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(2000),
                (ActionEvent event) -> {
                    img = Sprite.bomb_1.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(2250),
                (ActionEvent event) -> {
                    img = Sprite.bomb.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(2500),
                (ActionEvent event) -> {
                    img = Sprite.bomb_1.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(3000),
                (ActionEvent event) -> {
                    img = Sprite.bomb.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(3300),
                (ActionEvent event) -> {
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(3800),
                (ActionEvent event) -> {
                    img = Sprite.bomb_exploded.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(4300),
                (ActionEvent event) -> {
                    img = Sprite.bomb_exploded1.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(4800),
                (ActionEvent event) -> {
                    img = Sprite.bomb_exploded2.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(5000),
                (ActionEvent event) -> {
                    BombermanGame.stillObjects.remove(this);
                }
        ));
        t.play();
    }

    @Override
    public void update() {

    }
}
