package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.Coin;
import object.Heart;
import object.Mana;
import utilz.LoadSave;

public abstract class Monster extends Entity {
    private GamePanel gp;

    public Monster(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_MONSTER);
        solidArea.x = 3;
        solidArea.y = 18;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
    }

    public void loadAnimations() {}

    public void damageReaction() {}

    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;
        // Set monster drop
        if (i < 50) {
            dropItem(new Coin(gp));
        }
        if (i > 50 && i < 75) {
            dropItem(new Heart(gp));
        }
        if (i > 75) {
            dropItem(new Mana(gp));
        }
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
