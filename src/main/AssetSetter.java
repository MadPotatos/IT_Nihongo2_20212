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
        int mapNum = 0;
        int i = 0;

        gp.obj[mapNum][i] = new Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 23;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.obj[mapNum][i] = new Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 23;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[mapNum][i] = new Mana(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;
        i++;

        gp.obj[mapNum][i] = new Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 20;
        gp.obj[mapNum][i].worldY = gp.tileSize * 18;
        i++;

        gp.obj[mapNum][i] = new SteelShield(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 24;
        i++;
        gp.obj[mapNum][i] = new Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 21;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;

        gp.obj[mapNum][i] = new Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 5;
        gp.obj[mapNum][i].worldY = gp.tileSize * 5;
        i++;

        gp.obj[mapNum][i] = new Chest(gp, new Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 20;
        gp.obj[mapNum][i].worldY = gp.tileSize * 43;
        i++;
        gp.obj[mapNum][i] = new Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 10;
        gp.obj[mapNum][i].worldY = gp.tileSize * 10;
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
        gp.monster[mapNum][8] = new Boss_GiantFlam(gp);
        gp.monster[mapNum][8].worldX = gp.tileSize * 10;
        gp.monster[mapNum][8].worldY = gp.tileSize * 8;

        gp.monster[mapNum][0] = new Slime(gp);
        gp.monster[mapNum][0].worldX = gp.tileSize * 12;
        gp.monster[mapNum][0].worldY = gp.tileSize * 10;

        gp.monster[mapNum][1] = new Slime(gp);
        gp.monster[mapNum][1].worldX = gp.tileSize * 20;
        gp.monster[mapNum][1].worldY = gp.tileSize * 18;

        gp.monster[mapNum][2] = new Tanuki(gp);
        gp.monster[mapNum][2].worldX = gp.tileSize * 25;
        gp.monster[mapNum][2].worldY = gp.tileSize * 19;

        gp.monster[mapNum][4] = new Mole(gp);
        gp.monster[mapNum][4].worldX = gp.tileSize * 9;
        gp.monster[mapNum][4].worldY = gp.tileSize * 6;

        gp.monster[mapNum][5] = new Mole(gp);
        gp.monster[mapNum][5].worldX = gp.tileSize * 19;
        gp.monster[mapNum][5].worldY = gp.tileSize * 18;

        gp.monster[mapNum][6] = new Mole(gp);
        gp.monster[mapNum][6].worldX = gp.tileSize * 30;
        gp.monster[mapNum][6].worldY = gp.tileSize * 7;

        gp.monster[mapNum][7] = new Mole(gp);
        gp.monster[mapNum][7].worldX = gp.tileSize * 12;
        gp.monster[mapNum][7].worldY = gp.tileSize * 5;
        // mapNum = 1;
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

    }
}
