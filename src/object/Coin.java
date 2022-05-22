package object;

import entity.Entity;
import main.GamePanel;

public class Coin extends Entity {
    GamePanel gp;

    public Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        name = "Coin";
        value = 1;
        down1 = setup("/Objects/coin", gp.tileSize, gp.tileSize);

    }

    public void use(Entity entity) {
        gp.playSE(1);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;
    }
}
