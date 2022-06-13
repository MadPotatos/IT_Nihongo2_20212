package object;

import main.GamePanel;

public class Key extends Item {

    public Key(GamePanel gp) {
        super(gp);
        name = "Key";
        down1 = setup("/Objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n" + "Can be used to open\ndoors.";

    }
}
