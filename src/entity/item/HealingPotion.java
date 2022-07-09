package entity.item;

import entity.Entity;
import main.GamePanel;
import utilz.LoadSave;

public class HealingPotion extends Item {
    private GamePanel gp;

    public HealingPotion(GamePanel gp) {

        super(gp);
        this.gp = gp;
        setValue(5);
        setType(LoadSave.TYPE_CONSUMABLE);
        setName("Healing Potion");
        down1 = LoadSave.setup("/Objects/healingPotion", gp.tileSize, gp.tileSize);
        setDescription("[" + getName() + "]\n" + "ヒーリングに使用で\nきる" + getValue() + " HP.");
        setPrice(10);
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.informState;
        gp.ui.currentDialogue = " " + getName() + "を使用した。";
        entity.life += getValue();
        if (gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);
        return true;
    }
}
