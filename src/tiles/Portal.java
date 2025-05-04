package tiles;

import core.GameMap;
import java.awt.*;

public class Portal extends Tile {
    private boolean visible = false;
    private GameMap gameMap;

    public Portal(int x, int y, GameMap gameMap) {
        super(x, y);
        this.gameMap = gameMap;
    }

    // Lấy tọa độ pixel theo trục X, bằng cách nhân với TILE_SIZE
    public int getPixelX() {
        return x * TILE_SIZE;
    }

    // Lấy tọa độ pixel theo trục Y
    public int getPixelY() {
        return y * TILE_SIZE;
    }

    public int getX() {
        return x;  // trả về tọa độ ô
    }

    public int getY() {
        return y;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public void render(Graphics g) {
        if (isVisible()) {
            g.setColor(new Color(255, 105, 180));
            // Vẽ theo tọa độ pixel
            g.fillRect(getPixelX(), getPixelY(), TILE_SIZE, TILE_SIZE);
        }
    }

    @Override
    public boolean isPassable() {
        boolean passable = visible && gameMap.isAllEnemiesDefeated();
        return passable;
    }
}
