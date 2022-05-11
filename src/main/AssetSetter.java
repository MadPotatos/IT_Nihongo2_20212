package main;

import java.nio.file.WatchEvent;

import entity.NPC_Inspector;
import entity.NPC_oldman;
import monster.Mole;
import monster.Slime;
import monster.Tanuki;
import object.Axe;
import object.Boots;
import object.Chest;
import object.Coin;
import object.DStradeMark;
import object.Door;
import object.Heart;
import object.Key;
import object.Mana;
import object.SteelShield;
import tile_interactive.BreakableStone;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int i = 0;

        gp.obj[i] = new Key(gp);
        gp.obj[i].worldX = gp.tileSize * 23;
        gp.obj[i].worldY = gp.tileSize * 20;
        i++;
        gp.obj[i] = new Heart(gp);
        gp.obj[i].worldX = gp.tileSize * 23;
        gp.obj[i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[i] = new Mana(gp);
        gp.obj[i].worldX = gp.tileSize * 26;
        gp.obj[i].worldY = gp.tileSize * 21;
        i++;

        gp.obj[i] = new Axe(gp);
        gp.obj[i].worldX = gp.tileSize * 20;
        gp.obj[i].worldY = gp.tileSize * 18;
        i++;

        gp.obj[i] = new SteelShield(gp);
        gp.obj[i].worldX = gp.tileSize * 25;
        gp.obj[i].worldY = gp.tileSize * 24;
        i++;
        gp.obj[i] = new Coin(gp);
        gp.obj[i].worldX = gp.tileSize * 21;
        gp.obj[i].worldY = gp.tileSize * 19;

        gp.obj[i] = new Heart(gp);
        gp.obj[i].worldX = gp.tileSize * 5;
        gp.obj[i].worldY = gp.tileSize * 5;
        i++;

    }

    public void setNPC() {
        gp.npc[0] = new NPC_Inspector(gp);
        gp.npc[0].worldX = gp.tileSize * 10;
        gp.npc[0].worldY = gp.tileSize * 10;
        gp.npc[1] = new NPC_oldman(gp);
        gp.npc[1].worldX = gp.tileSize * 35;
        gp.npc[1].worldY = gp.tileSize * 10;
    }

    public void setMonster() {
        gp.monster[0] = new Slime(gp);
        gp.monster[0].worldX = gp.tileSize * 12;
        gp.monster[0].worldY = gp.tileSize * 10;

        gp.monster[1] = new Slime(gp);
        gp.monster[1].worldX = gp.tileSize * 20;
        gp.monster[1].worldY = gp.tileSize * 18;

        gp.monster[2] = new Tanuki(gp);
        gp.monster[2].worldX = gp.tileSize * 25;
        gp.monster[2].worldY = gp.tileSize * 20;

        gp.monster[4] = new Mole(gp);
        gp.monster[4].worldX = gp.tileSize * 10;
        gp.monster[4].worldY = gp.tileSize * 40;

        gp.monster[5] = new Mole(gp);
        gp.monster[5].worldX = gp.tileSize * 21;
        gp.monster[5].worldY = gp.tileSize * 21;

        gp.monster[6] = new Mole(gp);
        gp.monster[6].worldX = gp.tileSize * 35;
        gp.monster[6].worldY = gp.tileSize * 23;

        gp.monster[7] = new Mole(gp);
        gp.monster[7].worldX = gp.tileSize * 12;
        gp.monster[7].worldY = gp.tileSize * 22;
    }

    public void setInteractiveTile() {
        int i = 0;
        gp.iTile[i] = new BreakableStone(gp, 6, 26);
        i++;
        gp.iTile[i] = new BreakableStone(gp, 7, 26);
        i++;
        gp.iTile[i] = new BreakableStone(gp, 8, 26);
        i++;
        gp.iTile[i] = new BreakableStone(gp, 9, 26);
        i++;
        gp.iTile[i] = new BreakableStone(gp, 10, 26);
        i++;
        gp.iTile[i] = new BreakableStone(gp, 12, 11);
        i++;
        gp.iTile[i] = new BreakableStone(gp, 7, 8);
        i++;

    }
}
