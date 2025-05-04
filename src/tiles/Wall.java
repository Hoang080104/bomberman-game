package tiles;

import java.awt.*;

public class Wall extends Tile {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    @Override
    public boolean isPassable() {
        return false;
    }
}
