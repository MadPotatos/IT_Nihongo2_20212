package object;

import main.GamePanel;

public class Axe extends Item {

    public Axe(GamePanel gp) {
        super(gp);
        type = type_axe;
        name = "Axe";
        down1 = setup("/Objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        description = "[" + name + "]\n" + "Can be used to chop down\ntree.";
        attackArea.width = 28;
        attackArea.height = 28;
        price = 100;
    }

}
