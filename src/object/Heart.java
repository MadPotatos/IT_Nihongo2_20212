package object;

import entity.Entity;
import main.GamePanel;

public class Heart extends Item {
	private GamePanel gp;

    public Heart(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(type_pickupOnly);
        setValue(2);

        setName("Heart");
        down1 = setup("/HUD/heart_full", gp.tileSize, gp.tileSize);
        setImage(setup("/HUD/heart_full", gp.tileSize, gp.tileSize));
        setImage2(setup("/HUD/heart_half", gp.tileSize, gp.tileSize));
        setImage3(setup("/HUD/heart_empty", gp.tileSize, gp.tileSize));

    }

    public void use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Life +" + getValue());
        entity.life += getValue();

    }
}