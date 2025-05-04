package tiles;

import java.awt.*;

public class Brick extends Tile {
    private Tile hiddenTile;

    public Brick(int x, int y) {
        this(x, y, new Grass(x, y)); // Mặc định phía sau là Grass
    }

    public Brick(int x, int y, Tile hiddenTile) {
        super(x, y);
        this.hiddenTile = hiddenTile;
    }

    public Tile getHiddenTile() {
        return hiddenTile;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(178, 102, 0));
        // Vẽ Brick theo grid
        g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    @Override
    public boolean isPassable() {
        return false;
    }
}
