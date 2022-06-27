package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import utilz.LoadSave;

public class Shuriken extends Projectile{
	private GamePanel gp;

    public Shuriken(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("EnergyBall");
        setType(LoadSave.TYPE_PROJECTTILE);
        speed = 10;
        maxLife = 40;
        life = maxLife;
        attack = 2;
        setUseCost(1);
        setAniSpeed(4);
        alive = false;
        loadAnimations(LoadSave.PROTILE_SURIKEN);
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
