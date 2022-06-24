package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import utilz.LoadSave;

import java.awt.Color;

public class EnergyBall extends Projectile {
    private GamePanel gp;

    public EnergyBall(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("EnergyBall");
        speed = 5;
        maxLife = 40;
        life = maxLife;
        attack = 2;
        setUseCost(1);
        alive = false;
        getImage();
    }

    private void getImage() {
        up1 = LoadSave.setup("/projectile/energyball_up1", gp.tileSize, gp.tileSize);
        up2 = LoadSave.setup("/projectile/energyball_up2", gp.tileSize, gp.tileSize);
        down1 = LoadSave.setup("/projectile/energyball_down1", gp.tileSize, gp.tileSize);
        down2 = LoadSave.setup("/projectile/energyball_down2", gp.tileSize, gp.tileSize);
        left1 = LoadSave.setup("/projectile/energyball_left1", gp.tileSize, gp.tileSize);
        left2 = LoadSave.setup("/projectile/energyball_left2", gp.tileSize, gp.tileSize);
        right1 = LoadSave.setup("/projectile/energyball_right1", gp.tileSize, gp.tileSize);
        right2 = LoadSave.setup("/projectile/energyball_right2", gp.tileSize, gp.tileSize);

    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        if (user.mana >= getUseCost()) {
            haveResource = true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user) {
        user.mana -= getUseCost();
    }

    public Color getParticleColor() {
        Color color = new Color(87, 175, 252);
        return color;
    }

    public int getParticleSize() {
        int size = 10;
        return size;
    }

    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }

    public int getParticleMaxLife() {
        int maxLife = 20;
        return maxLife;
    }

}
