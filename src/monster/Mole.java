package monster;

import java.awt.image.BufferedImage;
import java.util.Random;

import main.GamePanel;
import main.UtilityTool;
import object.Rock;
import utilz.LoadSave;

public class Mole extends Monster {
    private GamePanel gp;

    public Mole(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        setName("Mole");
        speed = 2;
        maxLife = 5;
        life = maxLife;
        attack = 3;
        defense = 1;
        exp = 3;
        projectile = new Rock(gp);

        solidArea.width = 36;
        solidArea.height = 30;
        loadAnimations();


    }
    public void loadAnimations() {
		BufferedImage imgWalk = LoadSave.GetSpriteAtlas(LoadSave.MONSTER_MOLE);
		UtilityTool uTool = new UtilityTool();
		BufferedImage[][] animations = new BufferedImage[4][4];
		for (int j = 0; j < animations.length; j++) {
			for (int i = 0; i < animations[j].length; i++) {
				animations[j][i] = imgWalk.getSubimage(j * 16, i * 16, 16, 16);
				animations[j][i] = uTool.scaleImage(animations[j][i], gp.tileSize, gp.tileSize);
			}
		}
		setAnimations(animations);
	}

    public void setAction() {
        setActionLockCounter(getActionLockCounter() + 1);
        if (getActionLockCounter() == 120) {
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
            setActionLockCounter(0);

        }
        int i = new Random().nextInt(100) + 1;
        if (i > 99 && projectile.alive == false && getShotAvailableCounter() == 30) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            setShotAvailableCounter(0);

        }
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
