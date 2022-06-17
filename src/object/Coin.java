package object;

import entity.Entity;
import main.GamePanel;

public class Coin extends Item {
    private GamePanel gp;

    public Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(type_pickupOnly);
        setName("Coin");
        setValue(1);
        down1 = setup("/Objects/coin", gp.tileSize, gp.tileSize);
        setImage(setup("/Objects/coin", gp.tileSize, gp.tileSize));

    }

    public void use(Entity entity) {
        gp.playSE(1);
        gp.ui.addMessage("Coin +" + getValue());
        gp.player.coin += getValue();
    }
}
