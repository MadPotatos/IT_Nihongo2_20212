package object;

import entity.Entity;
import main.GamePanel;
import utilz.LoadSave;

public class Heart extends Item {
    private GamePanel gp;

    public Heart(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_PICKUPONLY);
        setValue(2);

        setName("Heart");
        down1 = LoadSave.setup("/HUD/heart_full", gp.tileSize, gp.tileSize);
        setImage(LoadSave.setup("/HUD/heart_full", gp.tileSize, gp.tileSize));
        setImage2(LoadSave.setup("/HUD/heart_half", gp.tileSize, gp.tileSize));
        setImage3(LoadSave.setup("/HUD/heart_empty", gp.tileSize, gp.tileSize));

    }

    public boolean use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Life +" + getValue());
        entity.life += getValue();
        return true;
    }
}