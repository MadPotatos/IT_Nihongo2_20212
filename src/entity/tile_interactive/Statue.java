package entity.tile_interactive;

import main.GamePanel;
import utilz.LoadSave;

public class Statue extends InteractiveTile {
    private GamePanel gp;

    public Statue(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = LoadSave.setup("/tiles_interactive/statue", gp.tileSize * 2, gp.tileSize * 2);
        setCollision(false);

    }

}