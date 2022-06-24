package object;

import main.GamePanel;
import utilz.LoadSave;

public class Door extends Item {

    public Door(GamePanel gp) {
        super(gp);
        setName("Door");
        down1 = LoadSave.setup("/Objects/door", gp.tileSize, gp.tileSize);
        setCollision(true);

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}