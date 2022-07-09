package entity.projectile;

import entity.Entity;
import main.GamePanel;
import utilz.LoadSave;

import java.awt.Color;

public class Rock extends Projectile {
    private GamePanel gp;

    public Rock(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("Rock");
        speed = 6;
        maxLife = 40;
        life = maxLife;
        attack = 2;
        setUseCost(1);
        alive = false;
        getImage();
    }

    private void getImage() {

        down1 = LoadSave.setup("/projectile/rock", gp.tileSize, gp.tileSize);

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
