package object;

import entity.Entity;
import main.GamePanel;

public class HealingPotion extends Entity {
    GamePanel gp;

    public HealingPotion(GamePanel gp) {

        super(gp);
        this.gp = gp;
        value = 5;
        type = type_consumable;
        name = "Healing Potion";
        down1 = setup("/Objects/healingPotion", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n" + "Can be used to heal\n" + value + " HP.";
        price = 10;
    }

    public void use(Entity entity) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You used a " + name + ".";
        entity.life += value;
        if (gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);

    }
}
