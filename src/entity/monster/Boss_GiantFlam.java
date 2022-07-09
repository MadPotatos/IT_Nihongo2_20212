package entity.monster;

import java.awt.image.BufferedImage;
import java.util.Random;

import entity.item.Key;
import main.GamePanel;
import main.UtilityTool;
import utilz.LoadSave;

public class Boss_GiantFlam extends Monster {
    GamePanel gp;

    public Boss_GiantFlam(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("GiantFlam");
        setAniSpeed(6);
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 40;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 290;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 60;
        solidArea.height = 60;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        loadAnimations();

    }

    public void loadAnimations() {
        BufferedImage imgWalk = LoadSave.GetSpriteAtlas(LoadSave.BOSS_GIANTFLAM);
        UtilityTool uTool = new UtilityTool();
        BufferedImage[][] animations = new BufferedImage[4][4];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = imgWalk.getSubimage(i * 50, 0, 50, 50);
                animations[j][i] = uTool.scaleImage(animations[j][i], 2 * gp.tileSize, 2 * gp.tileSize);
            }
        }
        setAnimations(animations);
    }

    @Override
    public void damageReaction() {

        setActionLockCounter(0);
        onPath = true;

    }

    @Override
    public void checkDrop() {

        dropItem(new Key(gp));

    }

    public void setAction() {
        if (onPath == true) {
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
            searchPath(goalCol, goalRow);

        } else {
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

    }

}
