package entities;

import core.GameMap;
import core.Main;
import java.awt.*;
import java.awt.event.KeyEvent;
import tiles.*;

public class Bomber extends MovableGameObject {
    private final int speed = Tile.TILE_SIZE;
    private boolean alive = true;

    public Bomber(int x, int y, GameMap gameMap) {
        super(x, y, gameMap);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // Vị trí của Bomber được đặt theo pixel (sau khi nhân với TILE_SIZE ở MapLoader)
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        if (!alive) return;

        // Kiểm tra va chạm với Enemy
        for (Enemy enemy : gameMap.getEnemies()) {
            if (enemy.isAlive() && enemy.getX() == x && enemy.getY() == y) {
                alive = false;
                System.out.println("Game Over: Bomber bị enemy chạm!");
                return;
            }
        }

        // Kiểm tra va chạm với Bomb (qua flame)
        for (Bomb bomb : gameMap.getBombs()) {
            if (bomb.isExploded()) {
                for (Point flame : bomb.getFlamePoints()) {
                    int px = x / Tile.TILE_SIZE;
                    int py = y / Tile.TILE_SIZE;
                    if (flame.x == px && flame.y == py) {
                        alive = false;
                        System.out.println("Game Over: Bomber bị bom nổ!");
                        return;
                    }
                }
            }
        }

        int tileX = x / Tile.TILE_SIZE;
        int tileY = y / Tile.TILE_SIZE;
        Tile tile = gameMap.getTile(tileX, tileY);
        if (tile instanceof Portal) {
            Portal portal = (Portal) tile;
            if (portal.isVisible()) {
                if (gameMap.isAllEnemiesDefeated()) {
                    System.out.println("Chiến thắng! Không còn Enemy nào.");
                    gameMap.setVictory();
                }
            }
        }



    }

    @Override
    public void render(Graphics g) {
        if (!alive) return;
        g.setColor(Color.BLUE);
        g.fillOval(x, y, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public void handleKeyPressed(KeyEvent e) {
        if (!alive) return;

        int newX = x, newY = y;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                newY -= speed;
                break;
            case KeyEvent.VK_DOWN:
                newY += speed;
                break;
            case KeyEvent.VK_LEFT:
                newX -= speed;
                break;
            case KeyEvent.VK_RIGHT:
                newX += speed;
                break;
            case KeyEvent.VK_SPACE:
                placeBomb();
                return;
            default:
                return;
        }

        int tileX = newX / Tile.TILE_SIZE;
        int tileY = newY / Tile.TILE_SIZE;
        Tile destTile = gameMap.getTile(tileX, tileY);

        // ✅ Nếu là Portal đã hiện nhưng chưa hết enemy -> in dòng nhắc
        if (destTile instanceof Portal) {
            Portal portal = (Portal) destTile;
            if (portal.isVisible() && !gameMap.isAllEnemiesDefeated()) {
                System.out.println("Chưa thắng được đâu");
            }
        }

        if (canMoveTo(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    private void placeBomb() {
        Bomb bomb = new Bomb(x, y, gameMap);
        gameMap.addBomb(bomb);
    }
}
