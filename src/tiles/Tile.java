package tiles;

import java.awt.*;

public abstract class Tile {
    protected int x, y;
    public static final int TILE_SIZE = 32;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics g);
    public abstract boolean isPassable();
}
