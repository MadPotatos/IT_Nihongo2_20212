package object;

import entity.Entity;
import main.GamePanel;
import utilz.LoadSave;

public class Key extends Item {
    GamePanel gp;

    public Key(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("Key");
        setType(LoadSave.TYPE_CONSUMABLE);
        down1 = LoadSave.setup("/Objects/key", gp.tileSize, gp.tileSize);
        setDescription("[" + getName() + "]\n" + "Can be used to open\ndoors.");
        setPrice(100);
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.informState;
        int objIndex = getDetected(entity, gp.obj, "Door");
        if (objIndex != 999) {
            gp.ui.currentDialogue = "You used the key to open the door.";
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        } else {
            gp.ui.currentDialogue = "There is no door to open.";
            return false;
        }
    }
}
