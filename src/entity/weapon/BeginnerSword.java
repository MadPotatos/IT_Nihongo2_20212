package entity.weapon;

import entity.item.Item;
import main.GamePanel;
import utilz.LoadSave;

public class BeginnerSword extends Item {

    public BeginnerSword(GamePanel gp) {
        super(gp);
        setType(LoadSave.TYPE_SWORD);
        // TODO Auto-generated constructor stub
        setName("ビギナーソード");
        down1 = LoadSave.setup("/Objects/sword_beginner", gp.tileSize, gp.tileSize);
        setAttackValue(1);
        setDescription("[" + getName() + "]\n" + "シンプルな剣です。");
        attackArea.width = 36;
        attackArea.height = 36;
        setPrice(30);
        knockBackPower = 2;
    }

}
