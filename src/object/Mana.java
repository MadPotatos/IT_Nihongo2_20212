package object;

import entity.Entity;
import main.GamePanel;

public class Mana extends Item {
	private GamePanel gp;

    public Mana(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(type_pickupOnly);
        setValue(1);

        setName("Mana");
        down1 = setup("/HUD/mana_full", gp.tileSize - 12, gp.tileSize - 12);
        setImage(setup("/HUD/mana_full", gp.tileSize - 12, gp.tileSize - 12));

        setImage2(setup("/HUD/mana_empty", gp.tileSize - 12, gp.tileSize - 12));

    }

    public void use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Mana +" + getValue());
        entity.mana += getValue();

    }
}