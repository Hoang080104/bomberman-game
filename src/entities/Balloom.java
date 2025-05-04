package entities;

import core.GameMap;
import java.awt.*;
import java.util.Random;
import tiles.*;

public class Balloom extends Enemy {
    private GameMap map;
    private Random random = new Random();
    private int moveCounter = 25;

    public Balloom(int x, int y, GameMap map) {
        super(x, y, map);
        this.map = map;
    }

    @Override
    public void update() {
        if (isAlive()) {
            move();
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.PINK);
        g.fillOval(x, y, 32, 32);
    }

    private void move() {
        if (moveCounter == 0) { // Di chuyển sau mỗi 25 frame
            int direction = random.nextInt(4);
            int newX = x, newY = y;

            switch (direction) {
                case 0 -> newY -= Tile.TILE_SIZE;
                case 1 -> newY += Tile.TILE_SIZE;
                case 2 -> newX -= Tile.TILE_SIZE;
                case 3 -> newX += Tile.TILE_SIZE;
            }

            if (map.isGrassTile(newX / Tile.TILE_SIZE, newY / Tile.TILE_SIZE)) {
                x = newX;
                y = newY;
            }

            moveCounter = 25; // Luôn đặt lại đúng 25 frame
        } else {
            moveCounter--;
        }
    }
}
