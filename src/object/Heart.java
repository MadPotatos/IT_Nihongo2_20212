package object;

import entity.Entity;
import main.GamePanel;

public class Heart extends Entity {

    public Heart(GamePanel gp) {
        super(gp);
        name = "Heart";
        image = setup("/HUD/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/HUD/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/HUD/heart_empty", gp.tileSize, gp.tileSize);

    }
}