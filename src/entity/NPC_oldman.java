package entity;

import main.GamePanel;

import java.util.Random;

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

        up1 = setup("/NPC/oldman/oldman_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/oldman/oldman_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/oldman/oldman_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/oldman/oldman_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/oldman/oldman_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/oldman/oldman_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/oldman/oldman_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/oldman/oldman_right2", gp.tileSize, gp.tileSize);
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
