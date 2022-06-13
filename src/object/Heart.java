package object;

import entity.Entity;
import main.GamePanel;

public class Heart extends Item {
    GamePanel gp;

    public Heart(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        value = 2;

        name = "Heart";
        down1 = setup("/HUD/heart_full", gp.tileSize, gp.tileSize);
        image = setup("/HUD/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/HUD/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/HUD/heart_empty", gp.tileSize, gp.tileSize);

    }

    public void use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;

    }
}