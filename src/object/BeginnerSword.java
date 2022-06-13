package object;

import main.GamePanel;

public class BeginnerSword extends Item {

    public BeginnerSword(GamePanel gp) {
        super(gp);
        type = type_sword;
        // TODO Auto-generated constructor stub
        name = "Beginner Sword";
        down1 = setup("/Objects/sword_beginner", gp.tileSize, gp.tileSize);
        attackValue = 1;
        description = "[" + name + "]\n" + "A simple sword.";
        attackArea.width = 36;
        attackArea.height = 36;
        price = 30;
    }

}
