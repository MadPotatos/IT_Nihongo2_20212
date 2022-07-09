package entity.projectile;

import entity.Entity;
import main.GamePanel;
import utilz.LoadSave;

public class Shuriken extends Projectile {
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

}
