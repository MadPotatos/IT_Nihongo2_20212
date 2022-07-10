package entity.tile_interactive;

import entity.Entity;
import main.GamePanel;
import utilz.LoadSave;

public class BreakableStone extends InteractiveTile {
    private GamePanel gp;

    public BreakableStone(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = LoadSave.setup("/Tiles/block", gp.tileSize, gp.tileSize);
        setDestructible(true);
        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;
        if (entity.currentWeapon.getType() == LoadSave.TYPE_SWORD) {
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

}
