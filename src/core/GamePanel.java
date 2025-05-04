package core;

import entities.Bomber;
import entities.Enemy;
import tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private GameMap gameMap;
    private Bomber bomber;
    private Timer timer;
    private boolean gameEnded = false;


    public GamePanel() {
        setPreferredSize(new Dimension(Tile.TILE_SIZE * 31, Tile.TILE_SIZE * 13)); // 992 x 416
        setFocusable(true);
        addKeyListener(this);

        gameMap = new GameMap(31, 13);
        bomber = new Bomber(1 * Tile.TILE_SIZE, 1 * Tile.TILE_SIZE, gameMap);
        gameMap.setBomber(bomber); // Đảm bảo GameMap có thể truy xuất Bomber

        MapLoader.loadMap("level1.txt", gameMap, bomber);

        timer = new Timer(1000 / 60, this);  // 60 FPS
        timer.start();

        // Cần để đảm bảo JPanel nhận phím
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameMap.render(g);

        if (bomber.isAlive()) { // Chỉ vẽ nếu còn sống
            bomber.render(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameEnded) return; // Nếu game đã kết thúc, không làm gì cả

        gameMap.update();

        if (!bomber.isAlive()) {
            gameEnded = true;
            timer.stop();
            SwingUtilities.invokeLater(Main::showGameOverScreen);
        }
        if (gameMap.isVictory()) {
            gameEnded = true;
            timer.stop();
            SwingUtilities.invokeLater(Main::showVictoryScreen);
        }


        repaint();
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if (bomber.isAlive()) { // Chỉ xử lý phím nếu Bomber còn sống
            bomber.handleKeyPressed(e);
            repaint(); // Đảm bảo vẽ lại ngay khi có hành động
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Không sử dụng
    }
}
