package tile_interactive;

import main.GamePanel;
import utilz.LoadSave;

public class StoneBreak extends InteractiveTile {
    private GamePanel gp;

    public StoneBreak(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = LoadSave.setup("/tiles_interactive/stone_break", gp.tileSize, gp.tileSize);
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;

    }
}