package entity;

import main.GamePanel;
import object.Axe;
import object.BeginnerSword;
import object.HealingPotion;
import object.Key;
import object.SteelShield;

public class NPC_merchant extends Entity {

    public NPC_merchant(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        direction = "down";
        speed = 0;
        getImage();
        setDialogue();
        setItems();
    }

    public void getImage() {

        up1 = setup("/NPC/merchant/merchant_down1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/merchant/merchant_down2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/merchant/merchant_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/merchant/merchant_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/merchant/merchant_down1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/merchant/merchant_down2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/merchant/merchant_down1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/merchant/merchant_down2", gp.tileSize, gp.tileSize);

    }

    public void setDialogue() {
        dialogues[0] = "Welcome customer.\nWhat can I do for you?";
    }

    public void setItems() {
        inventory.add(new HealingPotion(gp));
        inventory.add(new BeginnerSword(gp));
        inventory.add(new Axe(gp));
        inventory.add(new Key(gp));
        inventory.add(new SteelShield(gp));

    }

    public void speak() {
        super.speak();
        gp.gameState = gp.tradingState;
        gp.ui.npc = this;
    }
}
