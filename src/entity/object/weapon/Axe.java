package entity.object.weapon;

import entity.object.item.Item;
import main.GamePanel;
import utilz.LoadSave;

public class Axe extends Item {

    public Axe(GamePanel gp) {
        super(gp);
        setType(LoadSave.TYPE_AXE);
        setName("Axe");
        down1 = LoadSave.setup("/Objects/axe", gp.tileSize, gp.tileSize);
        setAttackValue(2);
        setDescription("[" + getName() + "]\n" + "木の切り倒しに\n使用できます。");
        attackArea.width = 28;
        attackArea.height = 28;
        setPrice(100);
        knockBackPower = 10;
    }

}
