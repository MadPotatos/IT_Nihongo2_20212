package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

import object.Coin;
import object.Heart;
import object.Mana;
import utilz.LoadSave;

public abstract class Monster extends Entity {
    private GamePanel gp;
    public boolean onPath = false;

    public Monster(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_MONSTER);
        solidArea.x = 3;
        solidArea.y = 18;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void damageReaction() {
        setActionLockCounter(0);
        onPath = true;
    }

    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;
        // Set monster drop
        if (i < 50) {
            dropItem(new Coin(gp));
        }
        if (i > 50 && i < 75) {
            dropItem(new Heart(gp));
        }
        if (i > 75) {
            dropItem(new Mana(gp));
        }
    }

    public void setAction() {
        if (onPath == true) {
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
            searchPath(goalCol, goalRow);

        } else {
            setActionLockCounter(getActionLockCounter() + 1);
            if (getActionLockCounter() == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;
                if (i < 25) {
                    direction = "up";
                }
                if (i > 25 && i < 50) {
                    direction = "down";
                }
                if (i > 50 && i < 75) {
                    direction = "left";
                }
                if (i > 75) {
                    direction = "right";
                }
                setActionLockCounter(0);
            }
        }

    }

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;
        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
        if (gp.pFinder.search() == true) {
            // next worldX and worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // entity solid area position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;
            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            } else if (enTopY > nextY && enLeftX > nextX) {
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            } else if (enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY < nextY && enLeftX < nextX) {
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            }
            // int nextCol = gp.pFinder.pathList.get(0).col;
            // int nextRow = gp.pFinder.pathList.get(0).row;
            // if (nextCol == goalCol && nextRow == goalRow) {
            // onPath = false;
            // }
        }
    }

}
