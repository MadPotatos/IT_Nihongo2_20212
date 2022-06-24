package entity;

import main.GamePanel;
import main.UtilityTool;
import utilz.LoadSave;

import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_oldman extends Entity {

    public NPC_oldman(GamePanel gp) {

        super(gp);
        setName("Oldman");
        direction = "down";
        setType(LoadSave.TYPE_NPC);
        speed = 1;
        loadAnimations();
        setDialogue();
    }
    public void loadAnimations() {
    	avatar = LoadSave.setup("/NPC/oldman/oldman_avatar", gp.tileSize, gp.tileSize);
		BufferedImage imgWalk = LoadSave.GetSpriteAtlas(LoadSave.NPC_OLDMAN);
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

    public void setDialogue() {
        dialogues[0] = "Ho ho ho";
        dialogues[1] = "I am Yoshi the wise";
        dialogues[2] = "I am the one who can \ntell you the truth";
        dialogues[3] = "But you have to wait \nfor game to update";
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

    }

    public void speak() {
        super.speak();
        gp.ui.npc = this;
    }

}
