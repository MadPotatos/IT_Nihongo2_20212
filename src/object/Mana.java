package object;

import entity.Entity;
import main.GamePanel;

public class Mana extends Entity {

    public Mana(GamePanel gp) {
        super(gp);
        name = "Mana";
        image = setup("/HUD/mana_full", gp.tileSize - 12, gp.tileSize - 12);

        image2 = setup("/HUD/mana_empty", gp.tileSize - 12, gp.tileSize - 12);

    }
}