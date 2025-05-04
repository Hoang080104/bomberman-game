package core;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Bomberman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(new GamePanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
