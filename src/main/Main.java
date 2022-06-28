package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javax.swing.JFrame;

public class Main {
    public static JFrame window;

    public static void main(String[] args) throws IOException {
        window = new JFrame();
        InputStream imgStream = Main.class.getResourceAsStream("/Player/avatar.png");
        BufferedImage myImg = ImageIO.read(imgStream);
        window.setIconImage(myImg);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("BruhQuest");
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
