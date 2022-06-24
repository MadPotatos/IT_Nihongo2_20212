package monster;

import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;
import utilz.LoadSave;

public class Boss_GiantFlam extends Monster {
    private GamePanel gp;

    public Boss_GiantFlam(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("GiantFlam");
        setAniSpeed(6);
        speed = 1;
        maxLife = 40;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 290;
        solidArea.width = 90;
        solidArea.height = 90;
        loadAnimations();

    }

    public void loadAnimations() {
        BufferedImage imgWalk = LoadSave.GetSpriteAtlas(LoadSave.BOSS_GIANTFLAM);
        UtilityTool uTool = new UtilityTool();
        BufferedImage[][] animations = new BufferedImage[4][4];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = imgWalk.getSubimage(i * 50, 0, 50, 50);
                animations[j][i] = uTool.scaleImage(animations[j][i], 3 * gp.tileSize, 3 * gp.tileSize);
            }
        }
        setAnimations(animations);
    }

    public void damageReaction() {
        setActionLockCounter(0);
        direction = gp.player.direction;
    }
}
