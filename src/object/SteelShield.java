package object;

import main.GamePanel;
import utilz.LoadSave;

public class SteelShield extends Item {

    public SteelShield(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        setType(LoadSave.TYPE_SHIELD);
        setName("Steel Shield");
        down1 = LoadSave.setup("/Objects/shield_steel", gp.tileSize, gp.tileSize);
        setDefenseValue(2);
        setDescription("[" + getName() + "]\n" + "A shield made of\nsteel.");
    }

}
