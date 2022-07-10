package entity.weapon;

import entity.item.Item;
import main.GamePanel;
import utilz.LoadSave;

public class SteelShield extends Item {

    public SteelShield(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        setType(LoadSave.TYPE_SHIELD);
        setName("鋼鉄製シールド");
        down1 = LoadSave.setup("/Objects/shield_steel", gp.tileSize, gp.tileSize);
        setDefenseValue(2);
        setDescription("[" + getName() + "]\n" + "鋼鉄でできた盾。");
        setPrice(8);
    }

}
