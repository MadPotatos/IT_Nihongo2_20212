package entity.weapon;

import entity.item.Item;
import main.GamePanel;
import utilz.LoadSave;

public class WoodenShield extends Item {

    public WoodenShield(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        setType(LoadSave.TYPE_SHIELD);
        setName("Wooden Shield");
        down1 = LoadSave.setup("/Objects/shield_wood", gp.tileSize, gp.tileSize);
        setDefenseValue(1);
        setDescription("[" + getName() + "]\n" + "シンプルなシールド");
    }

}
