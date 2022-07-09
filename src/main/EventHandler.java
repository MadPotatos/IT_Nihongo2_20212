package main;

import entity.Entity;

public class EventHandler {
    private GamePanel gp;
    private EventRect eventRect[][][];
    private int previousEventX;
    private int previousEventY;
    private boolean canTouchEvent = true;
    private int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 30;
            eventRect[map][col][row].y = 30;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].setEventRectDefaultX(eventRect[map][col][row].x);
            eventRect[map][col][row].setEventRectDefaultY(eventRect[map][col][row].y);
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.playSE(5);
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life -= 1;
        // eventRect[map][col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healingPool(int gameState) {
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.player.setAttackCanceled(true);
            gp.playSE(2);
            gp.ui.currentDialogue = "Rest at the statue\n You feel better! \n Monster respawns!";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setMonster();
        }

    }

    public void checkEvent() {

        // check if player is more than 1 tile away from event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }
        if (canTouchEvent == true) {
            if (hit(0, 5, 5, "left") == true) {
                damagePit(gp.informState);
            } else if (hit(0, 5, 7, "left") == true) {
                damagePit(gp.informState);
            } else if (hit(0, 1, 5, "left") == true) {
                healingPool(gp.informState);
            } else if (hit(0, 7, 7, "any") == true) {
                healingPool(gp.informState);

            } else if (hit(0, 7, 8, "any") == true) {
                healingPool(gp.informState);

            } else if (hit(0, 24, 11, "any") == true) {
                teleport(1, 20, 47);
            } else if (hit(1, 20, 48, "any") == true) {
                teleport(0, 24, 11);

            } else if (hit(1, 19, 48, "any") == true) {
                teleport(0, 24, 11);
            } else if (hit(1, 10, 8, "up") == true) {
                speak(gp.npc[1][0]);

            }
        }

    }

    private void speak(Entity entity) {
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gp.dialogueState;
            entity.speak();
            gp.player.setAttackCanceled(true);

        }
    }

    private void teleport(int map, int col, int row) {
        gp.gameState = gp.loadingState;
        setTempMap(map);
        setTempCol(col);
        setTempRow(row);
        canTouchEvent = false;
    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;
        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row])
                    && eventRect[map][col][row].isEventDone() == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].getEventRectDefaultX();
            eventRect[map][col][row].y = eventRect[map][col][row].getEventRectDefaultY();
        }
        return hit;

    }

    // Getter and setter

    public int getPreviousEventX() {
        return previousEventX;
    }

    public void setPreviousEventX(int previousEventX) {
        this.previousEventX = previousEventX;
    }

    public int getPreviousEventY() {
        return previousEventY;
    }

    public void setPreviousEventY(int previousEventY) {
        this.previousEventY = previousEventY;
    }

    public int getTempMap() {
        return tempMap;
    }

    public void setTempMap(int tempMap) {
        this.tempMap = tempMap;
    }

    public int getTempCol() {
        return tempCol;
    }

    public void setTempCol(int tempCol) {
        this.tempCol = tempCol;
    }

    public int getTempRow() {
        return tempRow;
    }

    public void setTempRow(int tempRow) {
        this.tempRow = tempRow;
    }

}
