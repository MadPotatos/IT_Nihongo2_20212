package entity.item;

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
        setDescription("[" + getName() + "]\n" + "ドア開閉に使用可能");
        setPrice(100);
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.informState;
        int objIndex = getDetected(entity, gp.obj, "Door");
        if (objIndex != 999) {
            gp.ui.currentDialogue = "鍵を使ってドアを開けたんですね。";
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        } else {
            gp.ui.currentDialogue = "開くべき扉がない。";
            return false;
        }
    }
}
