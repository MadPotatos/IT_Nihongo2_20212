package object;

import main.GamePanel;

public class WoodenShield extends Item {

    public WoodenShield(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        setType(type_shield);
        setName("Wooden Shield");
        down1 = setup("/Objects/shield_wood", gp.tileSize, gp.tileSize);
        setDefenseValue(1);
        setDescription("[" + getName() + "]\n" + "A simple shield");
    }

}
