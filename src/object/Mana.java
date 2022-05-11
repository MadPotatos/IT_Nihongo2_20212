package object;

import entity.Entity;
import main.GamePanel;

public class Mana extends Entity {
    GamePanel gp;

    public Mana(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        value = 1;

        name = "Mana";
        down1 = setup("/HUD/mana_full", gp.tileSize, gp.tileSize);
        image = setup("/HUD/mana_full", gp.tileSize - 12, gp.tileSize - 12);

        image2 = setup("/HUD/mana_empty", gp.tileSize - 12, gp.tileSize - 12);

    }

    public void use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;

    }
}