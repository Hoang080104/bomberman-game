package items;

import java.awt.*;

public class FlameItem extends Item {

    public FlameItem(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(x * 32 + 8, y * 32 + 8, 16, 16);
    }

    //@Override
    //public void update() {
        // Chưa có logic hoạt động
    //}

   // @Override
    //public void onCollect() {
        // TODO: tăng độ dài lửa bom
       // System.out.println("Collected FlameItem!");
   // }
}
