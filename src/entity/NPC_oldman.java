package entity;

import main.GamePanel;
import main.UtilityTool;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class NPC_oldman extends Entity {

    public NPC_oldman(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage() {

        up1 = setup("/NPC/oldman/oldman_up1");
        up2 = setup("/NPC/oldman/oldman_up2");
        down1 = setup("/NPC/oldman/oldman_down1");
        down2 = setup("/NPC/oldman/oldman_down2");
        left1 = setup("/NPC/oldman/oldman_left1");
        left2 = setup("/NPC/oldman/oldman_left2");
        right1 = setup("/NPC/oldman/oldman_right1");
        right2 = setup("/NPC/oldman/oldman_right2");
    }

    public void setDialogue() {
        dialogues[0] = "Ho ho ho";
        dialogues[1] = "I am Yoshi the wise";
        dialogues[2] = "I am the one who can tell you the truth";
        dialogues[3] = "But you have to wait for game to update";
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i < 25) {
                direction = "up";
            }
            if (i > 25 && i < 50) {
                direction = "down";
            }
            if (i > 50 && i < 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }

    public void speak() {
        super.speak();
    }

}
