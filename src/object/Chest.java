package object;

import main.GamePanel;
import utilz.LoadSave;

public class Chest extends Item {

    public Chest(GamePanel gp) {
        super(gp);
        setName("Chest");
        down1 = LoadSave.setup("/Objects/chest", gp.tileSize, gp.tileSize);

    }
}