package monster;

import main.GamePanel;

public class Slime extends Monster {
    private GamePanel gp;

    public Slime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;
        solidArea.width = 42;
        solidArea.height = 30;
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

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
