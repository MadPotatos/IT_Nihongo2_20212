package object;

import main.GamePanel;

public class Door extends Item {

    public Door(GamePanel gp) {
        super(gp);
        setName("Door");
        down1 = setup("/Objects/door", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}