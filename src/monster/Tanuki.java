package monster;

import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;
import utilz.LoadSave;

public class Tanuki extends Monster {
    private GamePanel gp;

    public Tanuki(GamePanel gp) {
        super(gp);
        this.gp = gp;
        // TODO Auto-generated constructor stub

        setName("Tanuki");
        speed = 2;
        maxLife = 5;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;

        solidArea.width = 36;
        solidArea.height = 30;
        loadAnimations(LoadSave.MONSTER_TANUKI);

    }

    public void damageReaction() {
        setActionLockCounter(0);
        if (gp.player.direction == "up") {
            direction = "down";
        }
        if (gp.player.direction == "down") {
            direction = "up";
        }
        if (gp.player.direction == "left") {
            direction = "right";
        }
        if (gp.player.direction == "right") {
            direction = "left";
        }

    }

}
