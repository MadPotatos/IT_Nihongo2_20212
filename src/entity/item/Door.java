package entity.item;

import main.GamePanel;
import utilz.LoadSave;

public class Door extends Item {
    GamePanel gp;

    public Door(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_OBSTACLE);
        setName("ドア");
        down1 = LoadSave.setup("/Objects/door", gp.tileSize, gp.tileSize);
        setCollision(true);

        solidArea.x = 10;
        solidArea.y = 10;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void interact() {
        gp.gameState = gp.informState;
        gp.ui.currentDialogue = "扉を開けるには鍵が必要です。";
    }
}