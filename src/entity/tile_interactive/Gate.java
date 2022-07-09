package entity.tile_interactive;

import main.GamePanel;
import utilz.LoadSave;

public class Gate extends InteractiveTile {
    private GamePanel gp;

    public Gate(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = LoadSave.setup("/tiles_interactive/gate", gp.tileSize * 5, gp.tileSize * 3);
        setCollision(true);
        solidArea.x = 0;
        solidArea.y = 10;
        solidArea.width = gp.tileSize * 5;
        solidArea.height = gp.tileSize * 2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
