package entity.npc;

import java.awt.image.BufferedImage;

import entity.Entity;
import entity.item.HealingPotion;
import entity.item.Key;
import entity.weapon.Axe;
import entity.weapon.BeginnerSword;
import entity.weapon.SteelShield;
import main.GamePanel;
import utilz.LoadSave;

public class NPC_merchant extends Entity {
    private GamePanel gp;

    public NPC_merchant(GamePanel gp) {
        super(gp);
        this.gp = gp;
        // TODO Auto-generated constructor stub
        setName("マーチャント");
        setType(LoadSave.TYPE_NPC);
        direction = "down";
        speed = 0;
        avatar = LoadSave.setup("/NPC/merchant/merchant_avatar", gp.tileSize, gp.tileSize);
        loadAnimations(LoadSave.NPC_MERCHANT);
        setDialogue();
        setItems();
    }

    public void setDialogue() {
        dialogues[0] = "いらっしゃいませ\n何になさいますか?";
    }

    public void setItems() {
        inventory.add(new HealingPotion(gp));
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
