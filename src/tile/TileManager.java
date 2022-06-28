package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    private GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[60];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/Maps/map02.txt", 0);
        loadMap("/Maps/store1.txt", 1);
    }

    public void getTileImage() {
        for (int i = 0; i < 10; i++) {
            setup(i, "grass", false);
        }
        setup(10, "gate01", true);
        setup(11, "gate02", true);
        setup(12, "grass", false);
        setup(13, "grass01", false);
        setup(14, "grass02", false);
        setup(15, "grass03", false);
        setup(16, "house01", true);
        setup(17, "house02", true);
        setup(18, "house03", false);
        setup(19, "house04", false);
        setup(20, "house05", false);
        setup(21, "house06", true);
        setup(22, "house07", true);
        setup(23, "house08", true);
        setup(24, "house09", true);
        setup(25, "sand", false);
        setup(26, "sand02", false);
        setup(27, "snow", false);
        setup(28, "snow01", false);
        setup(29, "snowtree", true);
        setup(30, "snowtree01", true);
        setup(31, "stone01", true);
        setup(32, "stump", true);

        setup(34, "tree01", true);
        setup(35, "tree02", true);
        setup(36, "water", true);
        setup(37, "water01", true);
        setup(38, "water02", true);
        setup(39, "water03", true);
        setup(40, "water04", true);
        setup(41, "water05", true);
        setup(42, "water06", true);
        setup(43, "water07", true);
        setup(44, "water08", true);
        setup(45, "checkpoint", true);
        setup(46, "floor1", false);
        setup(47, "wall1", true);
        setup(48, "wall2", true);
        setup(49, "wall3", true);
        setup(50, "wall4", true);
        setup(51, "wall5", true);
        setup(52, "wall6", true);
        setup(53, "wall7", true);
        setup(54, "wall8", true);
        setup(55, "table1", true);
        setup(56, "table2", true);
        setup(57, "table3", true);

    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].setImage(ImageIO.read(getClass().getResourceAsStream("/Tiles/" + imageName + ".png")));
            tile[index].setImage(uTool.scaleImage(tile[index].getImage(), gp.tileSize, gp.tileSize));
            tile[index].setCollision(collision);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {

        try {

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;
                    col++;

                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.getScreenX();
            int screenY = worldY - gp.player.worldY + gp.player.getScreenY();

            // Stop moving the camera at the edge
            if (gp.player.getScreenX() > gp.player.worldX) {
                screenX = worldX;
            }
            if (gp.player.getScreenY() > gp.player.worldY) {
                screenY = worldY;
            }
            int rightOffset = gp.screenWidth - gp.player.getScreenX();
            if (rightOffset > gp.worldWidth - gp.player.worldX) {
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }
            int bottomOffset = gp.screenHeight - gp.player.getScreenY();
            if (bottomOffset > gp.worldHeight - gp.player.worldY) {
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.getScreenX()
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.getScreenX() &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.getScreenY()
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.getScreenY()) {
                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, null);
            } else if (gp.player.getScreenX() > gp.player.worldX || gp.player.getScreenY() > gp.player.worldY
                    || rightOffset > gp.worldWidth - gp.player.worldX
                    || bottomOffset > gp.worldHeight - gp.player.worldY) {
                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }

        }
    }
}
