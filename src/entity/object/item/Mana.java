package entity.object.item;

import entity.Entity;
import main.GamePanel;
import utilz.LoadSave;

public class Mana extends Item {
    private GamePanel gp;

    public Mana(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_PICKUPONLY);
        setValue(1);

        setName("Mana");
        down1 = LoadSave.setup("/HUD/mana_full", gp.tileSize - 12, gp.tileSize - 12);
        setImage(LoadSave.setup("/HUD/mana_full", gp.tileSize - 12, gp.tileSize - 12));

        setImage2(LoadSave.setup("/HUD/mana_empty", gp.tileSize - 12, gp.tileSize - 12));

    }

    public boolean use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Mana +" + getValue());
        entity.mana += getValue();
        return true;
    }
}