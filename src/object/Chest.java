package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Chest extends SuperObject {
    GamePanel gp;

    public Chest(GamePanel gp) {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/chest.png"));
            uTool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
