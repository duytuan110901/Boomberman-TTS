package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.Random;

public class Item extends Entity {
    public boolean explosed = false;
    public Item(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    public void setImg() {}
}
