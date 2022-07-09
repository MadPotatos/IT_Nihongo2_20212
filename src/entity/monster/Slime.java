package entity.monster;

import java.util.Random;

import entity.item.Coin;
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

    @Override
    public void damageReaction() {
        setActionLockCounter(0);
        gp.player.direction = direction;

    }

    @Override
    public void checkDrop() {

        dropItem(new Coin(gp));

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

}
