package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class Slime extends Entity {

    public Slime(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        type = 2;
        name = "Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();

    }

    public void getImage() {
        up1 = setup("/monsters/slime_up1");
        up2 = setup("/monsters/slime_up2");
        down1 = setup("/monsters/slime_down1");
        down2 = setup("/monsters/slime_down2");
        left1 = setup("/monsters/slime_left1");
        left2 = setup("/monsters/slime_left2");
        right1 = setup("/monsters/slime_right1");
        right2 = setup("/monsters/slime_right2");
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

}
