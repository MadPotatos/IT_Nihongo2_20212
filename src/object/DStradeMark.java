package object;

import entity.Entity;
import main.GamePanel;

public class DStradeMark extends Entity {

    public DStradeMark(GamePanel gp) {
        super(gp);
        name = "DStradeMark";
        down1 = setup("/Objects/darksoulstrademark", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n" + "Mark the one who beat\nthe Dark Souls game.";

    }
}
