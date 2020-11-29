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

public class Oneal extends Entity {
    private int getX[] = {-1, 0, 1, 0};
    private int getY[] = {0, 1, 0, -1};
    private List<Integer>save_choose = new ArrayList<Integer>();
    private List<Integer>choose_one = new ArrayList<Integer>();
    private double v = 4;
    private int nextStep = -1;
    private int c[][] = new int[BombermanGame.HEIGHT][BombermanGame.WIDTH];
    private boolean kt = false;
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }
    public int begin_X = 0;
    public int begin_Y = 0;
    public int end_X = 0;
    public int end_Y = 0;
    public long time = 1500;
    public void set_Square(int BomberX, int BomberY)
    {
        int bX = BomberX / Sprite.SCALED_SIZE;
        int bY = BomberY / Sprite.SCALED_SIZE;
        int xx = x / Sprite.SCALED_SIZE;
        int yy = y / Sprite.SCALED_SIZE;
        begin_X = Math.min(xx, bX);
        begin_Y = Math.min(yy, bY);
        end_X = Math.max(xx, bX);
        end_Y = Math.max(yy, bY);
    }
    public void Reset()
    {
        save_choose = new ArrayList<Integer>();
        choose_one = new ArrayList<Integer>();
    }
    public void run(int BomberX, int BomberY) {
        if ((x - BomberX) * (x - BomberX) + (y - BomberY) * (y - BomberY) > 60000) {
            kt = false;
        }
        if ((x - BomberX) * (x - BomberX) + (y - BomberY) * (y - BomberY) < 30000 && !kt) {
            kt = !kt;
        }
        if (kt) {
            Reset();
            set_Square(BomberX, BomberY);
            v = 8;
            time = 700;
            kt = false;
            for (int i = 0; i < BombermanGame.HEIGHT; ++i) {
                for (int j = 0; j < BombermanGame.WIDTH; ++j) {
                    c[i][j] = 0;
                }
            }
            int bX = BomberX / Sprite.SCALED_SIZE;
            int bY = BomberY / Sprite.SCALED_SIZE;
            int xx = x / Sprite.SCALED_SIZE;
            int yy = y / Sprite.SCALED_SIZE;
            if (bX!=xx || bY!=yy)
                backtracking(bX, bY, xx, yy, -1);
        } else v = 4;
        if (!kt) {
            Random ran = new Random();
            int next = ran.nextInt(4);
            int xx = x / Sprite.SCALED_SIZE;
            int yy = y / Sprite.SCALED_SIZE;
            while (!(BombermanGame.ObjectMap[yy + getY[next]][xx + getX[next]] instanceof Grass) || BombermanGame.BombMap[yy + getY[next]][xx + getX[next]] == 1) {
                next = ran.nextInt(4);
            }
            nextStep = next;
        }
        kt = false;
        Timeline t = new Timeline();
        int tt = (int) 32 / (int) v;
        t.setCycleCount(tt);
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(150),
                (ActionEvent event) -> {
                    x += getX[nextStep] * v;
                    y += getY[nextStep] * v;
                }
        ));
        t.play();

    }
    public void runB() {
        Timeline t = new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);

        if (v==4) {
            t.getKeyFrames().add(new KeyFrame(

                Duration.millis(v==4 ? 1500 : 750),
                (ActionEvent event) -> {
                    this.run(BombermanGame.bomberman.getX(), BombermanGame.bomberman.getY());
                }
        )); }
        else {
            t.getKeyFrames().add(new KeyFrame(

                    Duration.millis(750),
                    (ActionEvent event) -> {
                        this.run(BombermanGame.bomberman.getX(), BombermanGame.bomberman.getY());
                    }
            ));
        }
        t.play();
    }

    public void backtracking(int endX, int endY, int xx, int yy, int save_path) {
        if(c[yy][xx] == 0 && BombermanGame.ObjectMap[yy][xx] instanceof Grass) {
            c[yy][xx] = 1;
            choose_one.add(save_path);
            if(xx == endX && yy == endY)
            {
                if(save_choose.size() > choose_one.size() || save_choose.size() == 0)
                {
                    save_choose = choose_one;
                    nextStep = save_choose.get((save_choose.size() > 1 ? 1 : 0));
                    kt = true;
                }
            }
            else
            {
                if (xx > begin_X) backtracking(endX, endY, xx - 1, yy, 0);
                if (xx < end_X) backtracking(endX, endY, xx + 1, yy, 2);
                if (yy > begin_Y) backtracking(endX, endY, xx, yy - 1, 3);
                if (yy < end_Y) backtracking(endX, endY, xx, yy + 1, 1);
            }
            c[yy][xx] = 0;
            choose_one.remove(choose_one.size() - 1);
        }
    }

    public void die() {
        Timeline t = new Timeline();
        t.setCycleCount(1);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(300),
                (ActionEvent event) -> {
                    img = Sprite.oneal_dead.getFxImage();
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(800),
                (ActionEvent event) -> {
                    BombermanGame.entities.remove(this);
                }
        ));
        t.play();
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
        List<Bomber> l = new ArrayList<Bomber>();
        for (Entity e : BombermanGame.entities) {
            if (e instanceof Bomber) {
                l.add((Bomber) e);
            }
        }
        for (Bomber e : l)  {
            if (checkColision(e)) {
                e.die();
            }
        }
    }

}
