package entity;

import main.GamePanel;

import utilz.LoadSave;

import java.util.Random;

public class NPC_oldman extends Entity {

    public NPC_oldman(GamePanel gp) {

        super(gp);
        setName("Oldman");
        direction = "down";
        setType(LoadSave.TYPE_NPC);
        speed = 1;
        avatar = LoadSave.setup("/NPC/oldman/oldman_avatar", gp.tileSize, gp.tileSize);
        loadAnimations(LoadSave.NPC_OLDMAN);
        setDialogue();
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
