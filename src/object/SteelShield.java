package object;

import main.GamePanel;

public class SteelShield extends Item {

    public SteelShield(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        setType(type_shield);
        setName("Steel Shield");
        down1 = setup("/Objects/shield_steel", gp.tileSize, gp.tileSize);
        setDefenseValue(2);
        setDescription("[" + getName() + "]\n" + "A shield made of steel.");
    }

}
