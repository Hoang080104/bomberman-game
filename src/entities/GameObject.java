package entities;

import java.awt.*;

public abstract class GameObject {
    protected int x, y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics g);

    public abstract void update();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
