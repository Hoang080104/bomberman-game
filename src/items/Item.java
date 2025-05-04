package items;

import java.awt.*;

public abstract class Item {
    protected int x, y;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics g);
}
