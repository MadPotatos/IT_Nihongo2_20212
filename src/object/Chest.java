package object;

import main.GamePanel;

public class Chest extends Item {

    public Chest(GamePanel gp) {
        super(gp);
        setName("Chest");
        down1 = setup("/Objects/chest", gp.tileSize, gp.tileSize);

    }
}