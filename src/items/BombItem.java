package items;

import java.awt.*;

public class BombItem extends Item {

    public BombItem(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x * 32, y * 32, 32, 32);
    }
}
