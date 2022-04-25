package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/Maps/map02.txt");
    }

    public void getTileImage() {

        setup(0, "grass", false);
        setup(1, "grass", false);
        setup(2, "grass", false);
        setup(3, "grass", false);
        setup(4, "grass", false);
        setup(5, "grass", false);
        setup(6, "grass", false);
        setup(7, "grass", false);
        setup(8, "grass", false);
        setup(9, "grass", false);
        setup(9, "earth", false);
        setup(10, "gate01", false);
        setup(11, "gate02", false);
        setup(12, "grass", false);
        setup(13, "grass01", false);
        setup(14, "grass02", false);
        setup(15, "grass03", false);
        setup(16, "house01", true);
        setup(17, "house02", true);
        setup(18, "house03", true);
        setup(19, "house04", true);
        setup(20, "house05", true);
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

    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.titleSize, gp.titleSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
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
                    mapTileNum[col][row] = num;
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
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.titleSize;
            int worldY = worldRow * gp.titleSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Stop moving the camera at the edge
            if (gp.player.screenX > gp.player.worldX) {
                screenX = worldX;
            }
            if (gp.player.screenY > gp.player.worldY) {
                screenY = worldY;
            }
            int rightOffset = gp.screenWidth - gp.player.screenX;
            if (rightOffset > gp.worldWidth - gp.player.worldX) {
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }
            int bottomOffset = gp.screenHeight - gp.player.screenY;
            if (bottomOffset > gp.worldHeight - gp.player.worldY) {
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }

            if (worldX + gp.titleSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.titleSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            } else if (gp.player.screenX > gp.player.worldX || gp.player.screenY > gp.player.worldY
                    || rightOffset > gp.worldWidth - gp.player.worldX
                    || bottomOffset > gp.worldHeight - gp.player.worldY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }

        }
    }
}
