package entity.object.item;

import entity.Entity;
import main.GamePanel;
import utilz.LoadSave;

public class Coin extends Item {
    private GamePanel gp;

    public Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_PICKUPONLY);
        setName("Coin");
        setValue(1);
        down1 = LoadSave.setup("/Objects/coin", gp.tileSize / 3, gp.tileSize / 3);
        setImage(LoadSave.setup("/Objects/coin", gp.tileSize, gp.tileSize));

    }

    public boolean use(Entity entity) {
        gp.playSE(1);
        gp.ui.addMessage("Coin +" + getValue());
        gp.player.coin += getValue();
        return true;
    }
}
