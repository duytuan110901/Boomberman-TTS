package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    private final char [][] mapMatrix = new char[HEIGHT][WIDTH];
    public static Entity [][] ObjectMap = new Entity[HEIGHT][WIDTH];
    public static int [][] BombMap = new int[HEIGHT][WIDTH];

    public static Bomber bomberman;
    public int[] getX = {-1, 0, 1, 0};
    public int[] getY = {0, -1, 0, 1};
    public static int n_bomb = 1;
    public static int n_flame = 1;
    public static int n_speed = 1;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(event -> {
            int x = bomberman.getX()/Sprite.SCALED_SIZE;
            int x1 = bomberman.getX();
            int y = bomberman.getY()/Sprite.SCALED_SIZE;
            int y1 = bomberman.getY();
            switch (event.getCode()) {
                case RIGHT:
                    if(ObjectMap[y][x+1] instanceof Wall||ObjectMap[y][x+1] instanceof Brick || BombMap[y][x+1]==1) {
                        bomberman.moveRight1();
                    } else if (ObjectMap[y][x+1] instanceof Grass && x1 % 32 == 0 && y1 % 32 == 0 && BombMap[y][x+1]!=1) {
                        bomberman.moveRight();
                    }
                    if (ObjectMap[y][x+1] instanceof Item) {
                        if (((Item) ObjectMap[y][x+1]).explosed == true) {
                            bomberman.moveRight();
                            Item it = (Item) ObjectMap[y][x+1];
                            it.checkItem();
                            it.explosed = false;
                        } else {
                            bomberman.moveRight1();
                        }
                    }
                    break;
                case LEFT:
                    if(ObjectMap[y][x-1] instanceof Wall||ObjectMap[y][x-1] instanceof Brick||BombMap[y][x-1]==1) {
                        bomberman.moveLeft1();
                    } else if (ObjectMap[y][x-1] instanceof Grass && x1 % 32 == 0 && y1 % 32 == 0 && BombMap[y][x-1]!=1) {
                        bomberman.moveLeft();
                    } else if (ObjectMap[y][x-1] instanceof Item) {
                        if (((Item) ObjectMap[y][x-1]).explosed == true) {
                            bomberman.moveLeft();
                            Item it = (Item) ObjectMap[y][x-1];
                            it.checkItem();
                            it.explosed = false;
                        } else {
                            bomberman.moveLeft1();
                        }
                    }
                    break;
                case UP:
                    if(ObjectMap[y-1][x] instanceof Wall||ObjectMap[y-1][x] instanceof Brick || BombMap[y-1][x]==1) {
                        bomberman.moveUp1();
                    } else if (ObjectMap[y-1][x] instanceof Grass && x1 % 32 == 0 && y1 % 32 == 0 && BombMap[y-1][x]!=1) {
                        bomberman.moveUp();
                    } else if (ObjectMap[y-1][x] instanceof Item) {
                        if (((Item) ObjectMap[y-1][x]).explosed == true) {
                            bomberman.moveUp();
                            Item it = (Item) ObjectMap[y-1][x];
                            it.checkItem();
                            it.explosed = false;
                        } else {
                            bomberman.moveUp1();
                        }
                    }
                    break;
                case DOWN:
                    if(ObjectMap[y+1][x] instanceof Wall || ObjectMap[y+1][x] instanceof Bomb || BombMap[y+1][x]==1) {
                        bomberman.moveDown1();
                    } else if ((ObjectMap[y+1][x] instanceof Grass) && y1 % 32 == 0&& x1 % 32 == 0 && BombMap[y+1][x]!=1) {
                        bomberman.moveDown();
                    } else if (ObjectMap[y+1][x] instanceof Item) {
                        if (((Item) ObjectMap[y+1][x]).explosed == true) {
                            bomberman.moveDown();
                            Item it = (Item) ObjectMap[y+1][x];
                            it.checkItem();
                            it.explosed = false;
                        } else {
                            bomberman.moveDown1();
                        }
                    }
                    break;
                case SPACE:
                    for (int j = 0; j < n_bomb; j++) {
                        Sound.play("BOM_SET");
                        Bomb bomb = new Bomb(x, y, Sprite.bomb_2.getFxImage());
                        stillObjects.add(bomb);
                        bomb.TimeStart = System.currentTimeMillis();
                        bomb.explosion();
                        testBrick(y, x);
                        BombMap[y][x] = 1;
                        int[] checkWall = {0, 0, 0, 0};
                        boolean[] checkStop = {true, true, true, true};
                        for (int i = 0; i < n_flame - 1; i++) {
                            if (checkStop[0] && checkWall[0] == 0 && !(ObjectMap[y][x-1-i] instanceof Wall)) {
                                Flame left = new Flame(x - 1 - i, y, null);
                                stillObjects.add(left);
                                left.statusHorizontal();
                                testBrick(y, x - 1 - i);
                                if (ObjectMap[y][x-1-i] instanceof Brick || ObjectMap[y][x-1-i] instanceof Item) {
                                    checkStop[0] = false;
                                }
                            } else {
                                checkWall[0] = 1;
                            }
                            if (checkStop[1] && checkWall[1] == 0 && !(ObjectMap[y][x+1+i] instanceof Wall)) {
                                Flame right = new Flame(x + 1 + i, y, null);
                                stillObjects.add(right);
                                right.statusHorizontal();
                                testBrick(y, x + 1 + i);
                                if (ObjectMap[y][x+1+i] instanceof Brick || ObjectMap[y][x+1+i] instanceof Item) {
                                    checkStop[1] = false;
                                }
                            } else {
                                checkWall[1] = 1;
                            }
                            if (checkStop[2] && checkWall[2] == 0 && !(ObjectMap[y-1-i][x] instanceof Wall)) {
                                Flame up = new Flame(x, y - 1 - i, null);
                                stillObjects.add(up);
                                up.statusVertical();
                                testBrick(y - 1 - i, x);
                                if (ObjectMap[y-1-i][x] instanceof Brick || ObjectMap[y-1-i][x] instanceof Item) {
                                    checkStop[2] = false;
                                }
                            } else {
                                checkWall[2] = 1;
                            }
                            if (checkStop[3] && checkWall[3] == 0 && !(ObjectMap[y+1+i][x] instanceof Wall)) {
                                Flame down = new Flame(x, y + 1 + i, null);
                                stillObjects.add(down);
                                down.statusVertical();
                                testBrick(y + 1 + i, x);
                                if (ObjectMap[y+1+i][x] instanceof Brick || ObjectMap[y+1+i][x] instanceof Item) {
                                    checkStop[3] = false;
                                }
                            } else {
                                checkWall[3] = 1;
                            }
                        }
                        if (checkStop[0] && checkWall[0] == 0 && !(ObjectMap[y][x - 1 - (n_flame - 1)] instanceof Wall)) {
                            Flame left = new Flame(x - 1 - (n_flame - 1), y, null);
                            stillObjects.add(left);
                            left.left();
                            testBrick(y, x - 1 - (n_flame - 1));
                        }
                        if (checkStop[1] && checkWall[1] == 0 && !(ObjectMap[y][x + 1 + (n_flame - 1)] instanceof Wall)) {
                            Flame right = new Flame(x + 1 + (n_flame - 1), y, null);
                            stillObjects.add(right);
                            right.right();
                            testBrick(y, x + 1 + (n_flame - 1));
                        }
                        if (checkStop[2] && checkWall[2] == 0 && !(ObjectMap[y - 1 - (n_flame - 1)][x] instanceof Wall)) {
                            Flame up = new Flame(x, y - 1 - (n_flame - 1), null);
                            stillObjects.add(up);
                            up.up();
                            testBrick(y - 1 - (n_flame - 1), x);
                        }
                        if (checkStop[3] && checkWall[3] == 0 && !(ObjectMap[y + 1 + (n_flame - 1)][x] instanceof Wall)) {
                            Flame down = new Flame(x, y + 1 + (n_flame - 1), null);
                            stillObjects.add(down);
                            down.down();
                            testBrick(y + 1 + (n_flame - 1), x);
                        }
                    }
                    break;

                default:
                    break;

            }
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);

        for (Entity e : entities) {
            if (e instanceof Balloom) {
                Balloom b = (Balloom) e;
                b.runB();
            }
            if (e instanceof Oneal) {
                Oneal o = (Oneal) e;
                o.runB();
            }
        }
        Sound.play("soundtrack");
    }

    public void createMap() {
        File file = new File("target\\classes\\levels\\Level1.txt");
        try {
            boolean kt = true;
            Scanner reader = new Scanner(file);
            String line = reader.nextLine();
            for (int j=0; j<HEIGHT; j++) {
                line = reader.nextLine();
                for (int i = 0; i < WIDTH; i++) {
                    char x= line.charAt(i);
                    mapMatrix[j][i] = x;
                    Entity object;
                    Entity en = null;
                    if (x == '#') {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                    } else if (x == '*') {
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                    } else if (x == 'x') {
                        object = new Portal(i, j, Sprite.brick.getFxImage());
                    } else if (x == '1') {
                        en = new Balloom(i, j, Sprite.balloom_right1.getFxImage());
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                    } else if ( x == 'b') {
                        object = new BombItem(i, j, Sprite.brick.getFxImage());
                    } else if ( x == 's') {
                        object = new SpeedItem(i, j, Sprite.brick.getFxImage());
                    } else if ( x == 'f') {
                        object = new FlameItem(i, j, Sprite.brick.getFxImage());
                    } else if (x == '2') {
                        en = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                    }
                    else {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                    }
                    ObjectMap[j][i] = object;
                    stillObjects.add(object);
                    if (en != null)
                        entities.add(en);
                }
            }
        } catch (Exception e) {

        }
    }

    public void update() {
        entities.forEach(Entity::update);
        stillObjects.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public void testBrick(int y, int x) {
        if (ObjectMap[y][x] instanceof Brick) {
            Brick b = (Brick) ObjectMap[y][x];
            b.exploded();
        } else if (ObjectMap[y][x] instanceof Item) {
            Item it = (Item) ObjectMap[y][x];
            it.setImg();
            Item.TimerStart = System.currentTimeMillis();
        }
    }
}
