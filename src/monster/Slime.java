package monster;

import main.GamePanel;
import utilz.LoadSave;

public class Slime extends Monster {
    private GamePanel gp;

    public Slime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("Slime");
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 290;
        solidArea.width = 42;
        solidArea.height = 30;
        loadAnimations(LoadSave.MONSTER_SLIME);
    }

    public void damageReaction() {
        setActionLockCounter(0);
        direction = gp.player.direction;
    }
}
