package entity;

import main.GamePanel;
import main.UtilityTool;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class NPC_Inspector extends Entity {

    public NPC_Inspector(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage() {

        up1 = setup("/NPC/inspector/inspector_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/inspector/inspector_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/inspector/inspector_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/inspector/inspector_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/inspector/inspector_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/inspector/inspector_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/inspector/inspector_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/inspector/inspector_right2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "Oh, this is a rare occasion.";
        dialogues[1] = "I can't remember the last time\na new visitor made their way to the village.";
        dialogues[2] = "Very well.\nAs a inspector, I bid you welcome.";
        dialogues[3] = "It is safe here.\nYou may let down your guard.";
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
