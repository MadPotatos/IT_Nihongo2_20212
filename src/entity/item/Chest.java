package entity.item;

import main.GamePanel;
import utilz.LoadSave;

public class Chest extends Item {
    GamePanel gp;
    Item loot;
    boolean opened = false;

    public Chest(GamePanel gp, Item loot) {
        super(gp);
        this.gp = gp;
        this.loot = loot;
        setType(LoadSave.TYPE_OBSTACLE);
        setName("Chest");
        setImage(LoadSave.setup("/Objects/chest", gp.tileSize, gp.tileSize));
        setImage2(LoadSave.setup("/Objects/chest_opened", gp.tileSize, gp.tileSize));
        down1 = LoadSave.setup("/Objects/chest", gp.tileSize, gp.tileSize);
        setCollision(true);
        solidArea.x = 0;
        solidArea.y = 10;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact() {
        gp.gameState = gp.informState;
        if (opened == false) {
            gp.playSE(3);
            StringBuilder sb = new StringBuilder();
            sb.append("胸を開き、" + loot.getName() + "を見つけた。!");
            if (gp.player.inventory.size() == gp.player.maxInventorySize) {
                sb.append("\nカバンがいっぱいになった。");
            } else {
                gp.player.inventory.add(loot);
                sb.append("\nYou obtained " + loot.getName() + ".");
                down1 = getImage2();
                opened = true;
            }
            gp.ui.currentDialogue = sb.toString();
        } else {
            gp.ui.currentDialogue = "チェストは空っぽです。";
        }
    }
}