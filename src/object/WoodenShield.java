package object;

import entity.Entity;
import main.GamePanel;

public class WoodenShield extends Entity {

    public WoodenShield(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        type = type_shield;
        name = "Wooden Shield";
        down1 = setup("/Objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\n" + "A simple shield";
    }

}
