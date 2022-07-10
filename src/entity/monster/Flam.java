package entity.monster;

import java.util.Random;

import entity.item.Mana;
import main.GamePanel;

import utilz.LoadSave;

public class Flam extends Monster {
    private GamePanel gp;

    public Flam(GamePanel gp) {
        super(gp);
        this.gp = gp;

        setName("Flam");
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 6;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;

        solidArea.width = 36;
        solidArea.height = 30;
        loadAnimations(LoadSave.MONSTER_FLAM);

    }

    public void update() {
        super.update();
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (int) (Math.sqrt(xDistance * xDistance + yDistance *
                yDistance) / gp.tileSize);

        if (onPath == false && tileDistance < 5) {
            int i = new Random().nextInt(100) + 1;
            if (i > 50) {
                gp.playSE(15);
                onPath = true;
            }
        }
        if (onPath == true && tileDistance > 10) {
            onPath = false;
        }
    }

    @Override
    public void damageReaction() {

        setActionLockCounter(0);
        onPath = true;

    }

    @Override
    public void checkDrop() {
        dropItem(new Mana(gp));
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
