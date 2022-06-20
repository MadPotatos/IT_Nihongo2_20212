package object;

import main.GamePanel;

public class Axe extends Item {

    public Axe(GamePanel gp) {
        super(gp);
        setType(type_axe);
        setName("Axe");
        down1 = setup("/Objects/axe", gp.tileSize, gp.tileSize);
        setAttackValue(2);
        setDescription("[" + getName() + "]\n" + "Can be used to chop\ndown tree.");
        attackArea.width = 28;
        attackArea.height = 28;
        setPrice(100);
    }

}
