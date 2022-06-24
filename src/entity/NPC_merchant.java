package entity;

import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;
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
        loadAnimations();
        setDialogue();
        setItems();
    }    
    
    public void loadAnimations() {
    	avatar = LoadSave.setup("/NPC/merchant/merchant_avatar", gp.tileSize, gp.tileSize);
		BufferedImage imgWalk = LoadSave.GetSpriteAtlas(LoadSave.NPC_MERCHANT);
		UtilityTool uTool = new UtilityTool();
		BufferedImage[][] animations = new BufferedImage[4][4];
		for (int j = 0; j < animations.length; j++) {
			for (int i = 0; i < animations[j].length; i++) {
				animations[j][i] = imgWalk.getSubimage(j * 16, i * 16, 16, 16);
				animations[j][i] = uTool.scaleImage(animations[j][i], gp.tileSize, gp.tileSize);
			}
		}
		setAnimations(animations);
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
