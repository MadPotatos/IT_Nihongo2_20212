package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class Slime extends Entity {
    GamePanel gp;

    public Slime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        // TODO Auto-generated constructor stub
        type = 2;
        name = "Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();

    }

    public void getImage() {
        up1 = setup("/monsters/slime/slime_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/monsters/slime/slime_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/monsters/slime/slime_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/monsters/slime/slime_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/monsters/slime/slime_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/monsters/slime/slime_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/monsters/slime/slime_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/monsters/slime/slime_right2", gp.tileSize, gp.tileSize);
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

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }

}
