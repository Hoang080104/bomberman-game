package entities;

import core.GameMap;
import tiles.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bomb extends GameObject {
    private int timer = 120;
    private boolean exploded = false;
    private GameMap gameMap;
    private List<Point> flamePoints = new ArrayList<>();
    private int flameDuration = 30; // số frame flame tồn tại

    public Bomb(int x, int y, GameMap gameMap) {
        super(x, y);
        this.gameMap = gameMap;
    }

    @Override
    public void update() {
        if (timer > 0) {
            timer--;
        } else if (!exploded) {
            explode();
            exploded = true;
        } else if (flameDuration > 0) {
            flameDuration--;
        }
    }

    private void explode() {
        int bx = x / Tile.TILE_SIZE;
        int by = y / Tile.TILE_SIZE;

        addFlame(bx, by); // trung tâm

        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        for (int[] dir : directions) {
            int fx = bx + dir[0];
            int fy = by + dir[1];

            if (!gameMap.isInBounds(fx, fy)) continue;

            Tile tile = gameMap.getTile(fx, fy);

            if (tile instanceof Wall) {
                continue;
            }

            if (tile instanceof Brick) {
                Tile under = ((Brick) tile).getHiddenTile();
                if (under instanceof Portal) {
                    ((Portal) under).setVisible(true); // hiển thị portal nếu cần
                    gameMap.setTile(fx, fy, under);
                } else {
                    gameMap.setTile(fx, fy, new Grass(fx, fy));
                }

                addFlame(fx, fy);
                continue;
            }

            addFlame(fx, fy);
        }

        // Kiểm tra nếu Bomber trúng ngọn lửa
        Bomber bomber = gameMap.getBomber();
        if (bomber != null && bomber.isAlive()) {
            int px = bomber.getX() / Tile.TILE_SIZE;
            int py = bomber.getY() / Tile.TILE_SIZE;
            for (Point flame : flamePoints) {
                if (flame.x == px && flame.y == py) {
                    bomber.setAlive(false);
                    System.out.println("Game Over: Bomber bị nổ!");
                    return;
                }
            }
        }

        // Kiểm tra nếu Enemy trúng ngọn lửa
        Iterator<Enemy> enemyIt = gameMap.getEnemies().iterator();
        while (enemyIt.hasNext()) {
            Enemy enemy = enemyIt.next();
            int ex = enemy.getX() / Tile.TILE_SIZE;
            int ey = enemy.getY() / Tile.TILE_SIZE;
            for (Point flame : flamePoints) {
                if (flame.x == ex && flame.y == ey) {
                    enemy.kill();
                    break;
                }
            }
        }
    }

    private void addFlame(int tx, int ty) {
        flamePoints.add(new Point(tx, ty));
    }

    @Override
    public void render(Graphics g) {
        if (!exploded) {
            g.setColor(Color.BLACK);
            g.fillOval(x, y, Tile.TILE_SIZE, Tile.TILE_SIZE);
        } else if (flameDuration > 0) {
            g.setColor(Color.ORANGE);
            for (Point p : flamePoints) {
                g.fillRect(p.x * Tile.TILE_SIZE, p.y * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
            }
        }
    }

    public boolean isExploded() {
        return exploded && flameDuration <= 0;
    }

    public List<Point> getFlamePoints() {
        return flamePoints;
    }
}
