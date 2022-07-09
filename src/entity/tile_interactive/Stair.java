package entity.tile_interactive;

import main.GamePanel;
import utilz.LoadSave;

public class Stair extends InteractiveTile {
    private GamePanel gp;

    public Stair(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = LoadSave.setup("/tiles_interactive/stair", gp.tileSize * 3, gp.tileSize * 3);
        setCollision(false);

    }

}
