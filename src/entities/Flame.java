package entities;

import java.awt.*;

public class Flame extends GameObject {
    public Flame(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {}

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x * 32, y * 32, 32, 32);
    }
}
