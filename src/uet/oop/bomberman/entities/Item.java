package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Item extends Entity {
    public static long TimerStart;
    public static boolean explosed = false;
    public Item(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
    public void checkItem() {

    }
    public void setImg() {

    }
    public void delete() {
        BombermanGame.stillObjects.remove(this);
        Grass grass = new Grass(x/ Sprite.SCALED_SIZE, y/Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
        BombermanGame.stillObjects.add(grass);
        BombermanGame.ObjectMap[y/Sprite.SCALED_SIZE][x/Sprite.SCALED_SIZE] = grass;
    }
}
