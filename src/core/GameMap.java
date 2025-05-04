package core;

import entities.Bomb;
import entities.Bomber;
import entities.Enemy;
import tiles.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameMap {
    private Tile[][] tiles;
    private List<Enemy> enemies;
    private List<Bomb> bombs;
    private Bomber bomber; // Đối tượng Bomber
    private boolean victory = false;

    public GameMap(int width, int height) {
        enemies = new ArrayList<>();
        bombs = new ArrayList<>();
        init(width, height);
    }

    public boolean isAllEnemiesDefeated() {
        return enemies.isEmpty();
    }

    public int getWidth() {
        return tiles[0].length;
    }

    public int getHeight() {
        return tiles.length;
    }

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }

    public Bomber getBomber() {
        return bomber;
    }

    public boolean isPassable(int x, int y) {
        if (isInBounds(x, y)) {
            return tiles[y][x].isPassable();
        }
        return false;
    }

    public boolean isGrassTile(int x, int y) {
        if (isInBounds(x, y)) {
            return tiles[y][x] instanceof Grass;
        }
        return false;
    }

    public void init(int width, int height) {
        tiles = new Tile[height][width];
    }

    public void setTile(int x, int y, Tile tile) {
        if (isInBounds(x, y)) {
            tiles[y][x] = tile;
        }
    }

    public Tile getTile(int x, int y) {
        if (isInBounds(x, y)) {
            return tiles[y][x];
        }
        return null;
    }

    public boolean isInBounds(int x, int y) {
        return y >= 0 && y < tiles.length && x >= 0 && x < tiles[0].length;
    }

    // Kiểm tra nếu Bomber đang đứng trên một ô Portal (Portal phải được bật visible để passable)
    public boolean isBomberOnPortal(int bomberX, int bomberY) {
        Tile tile = getTile(bomberX, bomberY);
        if (tile instanceof Portal) {
            return tile.isPassable();
        }
        return false;
    }

    public boolean isVictory() {
        return victory;
    }
    public void setVictory() {
        this.victory = true;
    }


    public void render(Graphics g) {
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                if (tiles[y][x] != null) {
                    tiles[y][x].render(g);
                }
            }
        }

        for (Bomb bomb : bombs) {
            bomb.render(g);
        }

        for (Enemy enemy : enemies) {
            enemy.render(g);
        }

        if (bomber != null) {
            bomber.render(g);
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addBomb(Bomb bomb) {
        int bx = bomb.getX() / Tile.TILE_SIZE;
        int by = bomb.getY() / Tile.TILE_SIZE;
        for (Bomb b : bombs) {
            int ex = b.getX() / Tile.TILE_SIZE;
            int ey = b.getY() / Tile.TILE_SIZE;
            if (bx == ex && by == ey) return;
        }
        bombs.add(bomb);
    }

    public void update() {
        // Cập nhật Bomber
        if (bomber != null) {
            bomber.update();
        }

        // Cập nhật danh sách Enemy
        Iterator<Enemy> enemyIt = enemies.iterator();
        while (enemyIt.hasNext()) {
            Enemy e = enemyIt.next();
            e.update();
            if (!e.isAlive()) {
                enemyIt.remove();
                System.out.println("Enemy bị tiêu diệt: " + e.getClass().getSimpleName());
            }
        }

        // Cập nhật Bomb
        Iterator<Bomb> bombIt = bombs.iterator();
        while (bombIt.hasNext()) {
            Bomb b = bombIt.next();
            b.update();
            if (b.isExploded()) {
                bombIt.remove();
            }
        }

        // Nếu tất cả Enemy đã bị tiêu diệt, "tiết lộ" Portal ẩn bên trong Brick
        if (isAllEnemiesDefeated()) {
            for (int y = 0; y < getHeight(); y++) {
                for (int x = 0; x < getWidth(); x++) {
                    Tile tile = getTile(x, y);
                    if (tile instanceof Brick) {
                        Brick brick = (Brick) tile;
                        if (brick.getHiddenTile() instanceof Portal) {
                            Portal portal = (Portal) brick.getHiddenTile();
                            portal.setVisible(true); // Bật portal
                            setTile(x, y, portal); // Thay thế Brick bằng Portal trong map
                        }
                    }
                }
            }
        }
    }
}
