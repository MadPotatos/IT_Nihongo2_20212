package object;

import main.GamePanel;

public class SteelShield extends Item {

    public SteelShield(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        type = type_shield;
        name = "Steel Shield";
        down1 = setup("/Objects/shield_steel", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\n" + "A shield made of steel.";
    }

}
