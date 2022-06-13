package object;

import main.GamePanel;

public class WoodenShield extends Item {

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
