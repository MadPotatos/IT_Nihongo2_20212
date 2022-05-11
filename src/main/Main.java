package main;

import javax.swing.JFrame;

public class Main {
    public static JFrame window;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("BruhQuest");
        // window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        gamePanel.config.loadConfig();
        if (gamePanel.fullscreenOn == true) {
            window.setUndecorated(true);

        }
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

}
