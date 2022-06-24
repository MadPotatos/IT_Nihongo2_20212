package object;

import entity.Entity;
import main.GamePanel;

public class HealingPotion extends Item {
    private GamePanel gp;

    public HealingPotion(GamePanel gp) {

        super(gp);
        this.gp = gp;
        setValue(5);
        setType(type_consumable);
        setName("Healing Potion");
        down1 = setup("/Objects/healingPotion", gp.tileSize, gp.tileSize);
        setDescription("[" + getName() + "]\n" + "Can be used to heal\n" + getValue() + " HP.");
        setPrice(10);
    }

    public void use(Entity entity) {
        gp.gameState = gp.informState;
        gp.ui.currentDialogue = "You used a " + getName() + ".";
        entity.life += getValue();
        if (gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);

    }
}
