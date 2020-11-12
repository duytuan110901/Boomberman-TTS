package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Flame extends Entity {
    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void left() {

        Timeline t = new Timeline();
        t.setCycleCount(1);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(3300),
                (ActionEvent event) -> {

                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(3800),
                (ActionEvent event) -> {
                    img = Sprite.explosion_horizontal_left_last.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(4300),
                (ActionEvent event) -> {
                    img = Sprite.explosion_horizontal_left_last1.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(4800),
                (ActionEvent event) -> {
                    img = Sprite.explosion_horizontal_left_last2.getFxImage();
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

    public void right() {
        Timeline t = new Timeline();
        t.setCycleCount(1);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(3300),
                (ActionEvent event) -> {

                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(3800),
                (ActionEvent event) -> {
                    img = Sprite.explosion_horizontal_right_last.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(4300),
                (ActionEvent event) -> {
                    img = Sprite.explosion_horizontal_right_last1.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(4800),
                (ActionEvent event) -> {
                    img = Sprite.explosion_horizontal_right_last2.getFxImage();
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

    public void up() {
        Timeline t = new Timeline();
        t.setCycleCount(1);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(3300),
                (ActionEvent event) -> {

                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(3800),
                (ActionEvent event) -> {
                    img = Sprite.explosion_vertical_top_last.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(4300),
                (ActionEvent event) -> {
                    img = Sprite.explosion_vertical_top_last1.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(4800),
                (ActionEvent event) -> {
                    img = Sprite.explosion_vertical_top_last2.getFxImage();
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

    public void down() {
        Timeline t = new Timeline();
        t.setCycleCount(1);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(3300),
                (ActionEvent event) -> {

                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(3800),
                (ActionEvent event) -> {
                    img = Sprite.explosion_vertical_down_last.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(4300),
                (ActionEvent event) -> {
                    img = Sprite.explosion_vertical_down_last1.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(4800),
                (ActionEvent event) -> {
                    img = Sprite.explosion_vertical_down_last2.getFxImage();
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
