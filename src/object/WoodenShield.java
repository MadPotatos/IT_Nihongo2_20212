package object;

import entity.Entity;
import main.GamePanel;

public class WoodenShield extends Entity {

    public WoodenShield(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        name = "Wooden Shield";
        down1 = setup("/Objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
    }

}
