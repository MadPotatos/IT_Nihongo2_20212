package entity;

import java.awt.image.BufferedImage;

import main.GamePanel;
import object.Axe;
import object.BeginnerSword;
import object.HealingPotion;
import object.Key;
import object.SteelShield;
import utilz.LoadSave;

public class NPC_merchant extends Entity {

    public NPC_merchant(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        setName("Merchant");
        setType(LoadSave.TYPE_NPC);
        direction = "down";
        speed = 0;
        avatar = LoadSave.setup("/NPC/merchant/merchant_avatar", gp.tileSize, gp.tileSize);
        loadAnimations(LoadSave.NPC_MERCHANT);
        setDialogue();
        setItems();
    }

    public void setDialogue() {
        dialogues[0] = "いらっしゃいませ.\n何になさいますか?";
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
