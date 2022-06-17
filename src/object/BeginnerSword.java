package object;

import main.GamePanel;

public class BeginnerSword extends Item {

    public BeginnerSword(GamePanel gp) {
        super(gp);
        setType(type_sword);
        // TODO Auto-generated constructor stub
        setName("Beginner Sword");
        down1 = setup("/Objects/sword_beginner", gp.tileSize, gp.tileSize);
        setAttackValue(1);
        setDescription("[" + getName() + "]\n" + "A simple sword.");
        attackArea.width = 36;
        attackArea.height = 36;
        setPrice(30);
    }

}
