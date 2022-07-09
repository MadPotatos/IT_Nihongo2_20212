package entity.monster;

import main.GamePanel;
import utilz.LoadSave;

public class Slime extends Monster {
    private GamePanel gp;

    public Slime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("Slime");
        setAniSpeed(6);
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 1;
        solidArea.width = 42;
        solidArea.height = 30;
        loadAnimations(LoadSave.MONSTER_SLIME);

    }

}
