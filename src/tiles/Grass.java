package tiles;

import java.awt.*;

public class Grass extends Tile {

    public Grass(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    @Override
    public boolean isPassable() {
        return true;
    }
}
