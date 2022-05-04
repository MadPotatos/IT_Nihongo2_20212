package object;

import entity.Projectile;
import main.GamePanel;

public class EnergyBall extends Projectile {
    GamePanel gp;

    public EnergyBall(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "EnergyBall";
        speed = 5;
        maxLife = 40;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    private void getImage() {
        up1 = setup("/projectile/energyball_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/energyball_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/energyball_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/energyball_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/energyball_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/energyball_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/energyball_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/energyball_right2", gp.tileSize, gp.tileSize);

    }

}
