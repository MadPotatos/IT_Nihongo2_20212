package main;

import entity.NPC_Inspector;
import entity.NPC_oldman;
import monster.Slime;
import object.Boots;
import object.Chest;
import object.Door;
import object.Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

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

    }
}
