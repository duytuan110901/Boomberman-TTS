package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

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
    private List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    private char [][] mapMatrix = new char[HEIGHT][WIDTH];
    private Entity [][] ObjectMap = new Entity[HEIGHT][WIDTH];

    private Bomber bomberman;
    private BombItem bomb;

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
            int y = bomberman.getY()/Sprite.SCALED_SIZE;
            switch (event.getCode()) {
                case RIGHT:
                    if (ObjectMap[y][x+1] instanceof Grass)
                        bomberman.moveRight();
                    break;
                case LEFT:
                    if (ObjectMap[y][x-1] instanceof Grass)
                        bomberman.moveLeft();
                    break;
                case UP:
                    if (ObjectMap[y-1][x] instanceof Grass)
                        bomberman.moveUp();
                    break;
                case DOWN:
                    if (ObjectMap[y+1][x] instanceof Grass)
                        bomberman.moveDown();
                    break;
                case SPACE:
                    bomb = new BombItem(x, y, Sprite.bomb_2.getFxImage());
                    stillObjects.add(bomb);
                    Flame left = new Flame(x-1, y, ObjectMap[y][x-1].getImg());
                    Flame right = new Flame(x+1, y, ObjectMap[y][x+1].getImg());
                    Flame up = new Flame(x, y-1, ObjectMap[y-1][x].getImg());
                    Flame down = new Flame(x, y+1, ObjectMap[y+1][x].getImg());
                    BombermanGame.stillObjects.add(left);
                    BombermanGame.stillObjects.add(right);
                    BombermanGame.stillObjects.add(up);
                    BombermanGame.stillObjects.add(down);
                    up.up();
                    left.left();
                    down.down();
                    right.right();
                    bomb.explosion();
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

    }

    public void createMap() {
        File file = new File("target\\classes\\levels\\Level1.txt");
        try {
            Scanner reader = new Scanner(file);
            String line = reader.nextLine();
            for (int j=0; j<HEIGHT; j++) {
                line = reader.nextLine();
                for (int i = 0; i < WIDTH; i++) {
                    char x= line.charAt(i);
                    mapMatrix[j][i] = x;
                    Entity object;
                    if (x == '#') {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                    } else if (x == '*') {
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                    } else if (x == 'x') {
                        object = new Portal(i, j, Sprite.portal.getFxImage());
                    }
                    else {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                    }
                    ObjectMap[j][i] = object;
                    stillObjects.add(object);
                }
            }
        } catch (Exception e) {

        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
