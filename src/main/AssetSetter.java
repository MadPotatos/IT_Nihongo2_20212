package main;

import entity.tile_interactive.*;
import entity.weapon.*;
import entity.item.*;
import entity.monster.*;
import entity.npc.*;

public class AssetSetter {
    private GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
    	
    	int mapNum = 1;
        
        int i = 0;
        gp.obj[0][i] = new Key(gp);
        gp.obj[0][i].worldX = gp.tileSize * 25;
        gp.obj[0][i].worldY = gp.tileSize * 44;
        i++;
        
        gp.obj[mapNum][i] = new Chest(gp, new HealingPotion(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 3;
        gp.obj[mapNum][i].worldY = gp.tileSize * 33;
        i++;
        gp.obj[mapNum][i] = new Chest(gp, new Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 2;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;
        i++;

        gp.obj[mapNum][i] = new Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 14;
        i++;

        gp.obj[mapNum][i] = new Chest(gp, new HealingPotion(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 44;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.obj[mapNum][i] = new Chest(gp, new Axe(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 45;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.obj[mapNum][i] = new Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 37;
        gp.obj[mapNum][i].worldY = gp.tileSize * 8;
        i++;
        gp.obj[mapNum][i] = new Portal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 45;
        gp.obj[mapNum][i].worldY = gp.tileSize * 7;
        i++;

    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;
        // map 0

        gp.npc[mapNum][i] = new NPC_merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 20;
        gp.npc[mapNum][i].worldY = gp.tileSize * 22;
        i++;
        gp.npc[mapNum][i] = new NPC_ninja(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 23;
        gp.npc[mapNum][i].worldY = gp.tileSize * 30;
        i++;
        gp.npc[mapNum][i] = new NPC_oldman(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 26;
        gp.npc[mapNum][i].worldY = gp.tileSize * 15;
        i++;

    }

    public void setMonster() {
        int mapNum = 1;
        int i = 0;
        

        gp.monster[mapNum][i] = new Boss_GiantFlam(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 10;
        gp.monster[mapNum][i].worldY = gp.tileSize * 5;
        i++;

        gp.monster[mapNum][i] = new Flam(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 12;
        gp.monster[mapNum][i].worldY = gp.tileSize * 3;
        i++;
        gp.monster[mapNum][i] = new Flam(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 14;
        gp.monster[mapNum][i].worldY = gp.tileSize * 4;
        i++;
        gp.monster[mapNum][i] = new Flam(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 16;
        gp.monster[mapNum][i].worldY = gp.tileSize * 5;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 3;
        gp.monster[mapNum][i].worldY = gp.tileSize * 34;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 4;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 3;
        gp.monster[mapNum][i].worldY = gp.tileSize * 38;
        i++;
        gp.monster[mapNum][i] = new Tanuki(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 3;
        gp.monster[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.monster[mapNum][i] = new Tanuki(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 4;
        gp.monster[mapNum][i].worldY = gp.tileSize * 24;
        i++;

        gp.monster[mapNum][i] = new Mole(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 46;
        gp.monster[mapNum][i].worldY = gp.tileSize * 31;
        i++;
        gp.monster[mapNum][i] = new Mole(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 45;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        gp.monster[mapNum][i] = new Mole(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 45;
        gp.monster[mapNum][i].worldY = gp.tileSize * 34;

    }

    public void setInteractiveTile() {
        int mapNum = 0;
        int i = 0;

        gp.iTile[mapNum][i] = new Gate(gp, 22, 9);
        i++;
        gp.iTile[mapNum][i] = new Stair(gp, 23, 12);
        i++;
        gp.iTile[mapNum][i] = new Statue(gp, 27, 10);
        i++;
        gp.iTile[mapNum][i] = new Statue(gp, 20, 10);
        i++;
        mapNum = 1;

        gp.iTile[mapNum][i] = new BreakableStone(gp, 44, 25);
        i++;
        gp.iTile[mapNum][i] = new BreakableStone(gp, 45, 25);
        i++;

    }
}
