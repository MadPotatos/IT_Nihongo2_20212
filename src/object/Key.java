package object;

import main.GamePanel;
import utilz.LoadSave;

public class Key extends Item {

    public Key(GamePanel gp) {
        super(gp);
        setName("Key");
        down1 = LoadSave.setup("/Objects/key", gp.tileSize, gp.tileSize);
        setDescription("[" + getName() + "]\n" + "Can be used to open\ndoors.");

    }
}
