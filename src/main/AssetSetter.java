package main;

import entity.NPC_Inspector;
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
        gp.npc[0].worldX = gp.titleSize * 10;
        gp.npc[0].worldY = gp.titleSize * 10;
    }
}
