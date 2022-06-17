package object;

import main.GamePanel;

public class Key extends Item {

    public Key(GamePanel gp) {
        super(gp);
        setName("Key");
        down1 = setup("/Objects/key", gp.tileSize, gp.tileSize);
        setDescription("[" + getName() + "]\n" + "Can be used to open\ndoors.");

    }
}
