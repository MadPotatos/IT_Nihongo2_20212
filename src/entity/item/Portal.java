package entity.item;

import main.GamePanel;
import utilz.LoadSave;

public class Portal extends Item {
    GamePanel gp;
    Item loot;
    boolean opened = false;

    public Portal(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_OBSTACLE);
        setName("ポータル");
        setImage(LoadSave.setup("/Objects/portal", gp.tileSize, gp.tileSize));
        down1 = LoadSave.setup("/Objects/portal", gp.tileSize * 3, gp.tileSize * 3);
        setCollision(true);
        solidArea.x = gp.tileSize;
        solidArea.y = 10;
        solidArea.width = 32;
        solidArea.height = 128;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void interact() {
        gp.gameState = gp.winState;
        gp.stopMusic();
        gp.playSE(4);
    }
}
