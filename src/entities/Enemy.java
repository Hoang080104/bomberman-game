package entities;

import core.GameMap;
import java.awt.*;
import java.util.List;
import java.util.Random;
import tiles.*;

public abstract class Enemy extends MovableGameObject {
    private boolean alive = true;
    private int direction = 0;
    private int moveCounter = 0;
    private Random random = new Random();

    public Enemy(int x, int y, GameMap gameMap) {
        super(x, y, gameMap);
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;
    }

    @Override
    public void update() {
        if (!alive) return;

        // Kiểm tra nếu bị tiêu diệt bởi bom
        List<Bomb> bombs = gameMap.getBombs();
        for (Bomb bomb : bombs) {
            if (bomb.isExploded()) {
                for (Point flame : bomb.getFlamePoints()) {
                    int ex = x / Tile.TILE_SIZE;
                    int ey = y / Tile.TILE_SIZE;
                    if (flame.x == ex && flame.y == ey) {
                        alive = false;
                        return;
                    }
                }
            }
        }

        // Di chuyển ngẫu nhiên
        if (moveCounter == 0) {
            direction = random.nextInt(4);
            moveCounter = 30;
        } else {
            int newX = x;
            int newY = y;
            switch (direction) {
                case 0 -> newY -= Tile.TILE_SIZE;
                case 1 -> newY += Tile.TILE_SIZE;
                case 2 -> newX -= Tile.TILE_SIZE;
                case 3 -> newX += Tile.TILE_SIZE;
            }
            if (canMoveTo(newX, newY)) {
                x = newX;
                y = newY;
            }
            moveCounter--;
        }
    }

    @Override
    public void render(Graphics g) {
        if (!alive) return;
        g.setColor(Color.RED);
        g.fillOval(x, y, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public boolean canMoveTo(int newX, int newY) {
        int tileX1 = newX / Tile.TILE_SIZE;
        int tileY1 = newY / Tile.TILE_SIZE;
        int tileX2 = (newX + Tile.TILE_SIZE - 1) / Tile.TILE_SIZE;
        int tileY2 = (newY + Tile.TILE_SIZE - 1) / Tile.TILE_SIZE;

        return gameMap.isGrassTile(tileX1, tileY1)
                && gameMap.isGrassTile(tileX2, tileY1)
                && gameMap.isGrassTile(tileX1, tileY2)
                && gameMap.isGrassTile(tileX2, tileY2);
    }
}
