package entities;

import core.GameMap;
import java.awt.*;
import java.util.Random;
import tiles.*;

public class Oneal extends Enemy {
    private GameMap map;
    private Random random = new Random();
    private int moveCounter = 20 + random.nextInt(11); // Di chuyển lúc 20, lúc 30 frame

    public Oneal(int x, int y, GameMap map) {
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
        g.setColor(Color.RED);
        g.fillOval(x, y, 32, 32);
    }

    private void move() {
        if (moveCounter == 0) { // Di chuyển sau số frame ngẫu nhiên
            Bomber bomber = map.getBomber();
            int bomberX = bomber.getX();
            int bomberY = bomber.getY();

            int newX = x, newY = y;

            if (Math.abs(bomberX - x) > Math.abs(bomberY - y)) {
                newX += (bomberX > x) ? Tile.TILE_SIZE : -Tile.TILE_SIZE;
            } else {
                newY += (bomberY > y) ? Tile.TILE_SIZE : -Tile.TILE_SIZE;
            }

            if (map.isGrassTile(newX / Tile.TILE_SIZE, newY / Tile.TILE_SIZE)) {
                x = newX;
                y = newY;
            } else {
                moveRandom();
            }

            moveCounter = 20 + random.nextInt(11); // Đổi thời gian sau mỗi lần di chuyển
        } else {
            moveCounter--;
        }
    }

    private void moveRandom() {
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
    }
}
