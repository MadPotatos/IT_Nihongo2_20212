package object;

import entity.Entity;
import main.GamePanel;

public class Heart extends Entity {

    public Heart(GamePanel gp) {
        super(gp);
        name = "Heart";
        image = setup("/HUD/heart_full");
        image2 = setup("/HUD/heart_half");
        image3 = setup("/HUD/heart_empty");

    }
}