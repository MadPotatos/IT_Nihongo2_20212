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
        loadAnimations();


    }


    public void loadAnimations() {
		BufferedImage imgWalk = LoadSave.GetSpriteAtlas(LoadSave.MONSTER_TANUKI);
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
