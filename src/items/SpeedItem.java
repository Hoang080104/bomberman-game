package items;

import java.awt.*;

public class SpeedItem extends Item {

    public SpeedItem(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x * 32, y * 32, 32, 32);
    }
}
