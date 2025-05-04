package core;

import javax.swing.*;

public class Main {
    private static GameFrame gameFrame;

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        if (gameFrame != null) {
            gameFrame.dispose(); // Xóa cửa sổ cũ trước khi tạo mới
        }
        gameFrame = new GameFrame();
    }

    public static void showGameOverScreen() {
        SwingUtilities.invokeLater(() -> {
            int choice = JOptionPane.showConfirmDialog(
                    gameFrame,
                    "Game Over! Bạn có muốn chơi lại?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                SwingUtilities.invokeLater(() -> startGame());
            } else {
                System.exit(0);
            }
        });
    }


    public static void showVictoryScreen() {
        SwingUtilities.invokeLater(() -> {
            int choice = JOptionPane.showConfirmDialog(
                    gameFrame,
                    "Chúc mừng! Bạn đã vượt qua màn chơi! Bạn có muốn chơi lại?",
                    "Chiến thắng!",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                // Thêm một delay nhỏ để JOptionPane đóng hoàn toàn trước khi khởi động lại
                SwingUtilities.invokeLater(() -> startGame());
            } else {
                System.exit(0);
            }
        });
    }

}
