package tile_interactive;

import entity.Entity;
import main.GamePanel;
import java.awt.Color;

public class BreakableStone extends InteractiveTile {
    GamePanel gp;

    public BreakableStone(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles_interactive/breakable_stone", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;
        if (entity.currentWeapon.type == type_axe) {
            isCorrectItem = true;
        }
        return isCorrectItem;
    }

    public void playSE() {
        gp.playSE(11);
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = new StoneBreak(gp, worldX / gp.tileSize, worldY / gp.tileSize);
        return tile;
    }

    public Color getParticleColor() {
        Color color = new Color(215, 142, 99);
        return color;
    }

    public int getParticleSize() {
        int size = 5;
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
