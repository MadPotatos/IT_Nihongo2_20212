package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    private GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    boolean drawPath = true;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[60];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/Maps/map01.txt", 0);
        loadMap("/Maps/dungeon.txt", 1);
    }

    public void getTileImage() {
        for (int i = 0; i < 10; i++) {
            setup(i, "sand1", false);
        }
        setup(10, "sand1", false);
        setup(11, "sand2", false);
        setup(12, "sand3", false);
        setup(13, "sand4", false);
        setup(14, "sand5", false);
        setup(15, "sand6", false);
        setup(16, "sand7", false);
        setup(17, "sand8", false);
        setup(18, "sand9", false);
        setup(19, "sand10", false);
        setup(20, "sand11", false);
        setup(21, "sand12", false);
        setup(22, "sand13", false);
        setup(23, "water1", true);
        setup(24, "water2", true);
        setup(25, "water3", true);
        setup(26, "water4", true);
        setup(27, "water5", true);
        setup(28, "water6", true);
        setup(29, "table1", true);
        setup(30, "table2", true);
        setup(31, "table3", true);
        setup(32, "wall1", true);
        setup(33, "wall2", true);
        setup(34, "wall3", true);
        setup(35, "wall4", true);
        setup(36, "wall5", true);
        setup(37, "wall6", true);
        setup(38, "wall7", true);
        setup(39, "wall8", true);
        setup(40, "light", false);
        setup(41, "dirt", false);
        setup(42, "block", true);
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
        // if (drawPath == true) {
        // g2.setColor(new Color(255, 0, 0, 70));
        // for (int i = 0; i < gp.pFinder.pathList.size(); i++) {
        // int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
        // int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
        // int screenX = worldX - gp.player.worldX + gp.player.getScreenX();
        // int screenY = worldY - gp.player.worldY + gp.player.getScreenY();
        // g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
        // }
        // }
    }
}
