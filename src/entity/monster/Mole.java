package entity.monster;

import java.util.Random;

import entity.object.projectile.Rock;
import main.GamePanel;
import utilz.LoadSave;

public class Mole extends Monster {
    private GamePanel gp;

    public Mole(GamePanel gp) {
        super(gp);
        this.gp = gp;

        setName("Mole");
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 5;
        life = maxLife;
        attack = 3;
        defense = 1;
        exp = 3;
        projectile = new Rock(gp);

        solidArea.width = 36;
        solidArea.height = 30;
        loadAnimations(LoadSave.MONSTER_MOLE);

    }

    public void setAction() {
        if (onPath == true) {
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
            searchPath(goalCol, goalRow);

            int i = new Random().nextInt(100) + 1;
            if (i > 99 && projectile.alive == false && getShotAvailableCounter() == 30) {
                projectile.set(worldX, worldY, direction, true, this);
                gp.projectileList.add(projectile);
                setShotAvailableCounter(0);

            }

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
