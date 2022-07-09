package entity.npc;

import main.GamePanel;

import utilz.LoadSave;

import entity.Entity;

public class NPC_oldman extends Entity {
    private GamePanel gp;

    public NPC_oldman(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("Oldman");
        direction = "down";
        setType(LoadSave.TYPE_NPC);
        speed = 0;
        avatar = LoadSave.setup("/NPC/oldman/oldman_avatar", gp.tileSize, gp.tileSize);
        loadAnimations(LoadSave.NPC_OLDMAN);
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "Ho ho ho";
        dialogues[1] = "目の前にはダンジョンに続く\nゲートがあります。";
        dialogues[2] = "その中にあなたの答えがある\nはずです";
        dialogues[3] = "本当に勇気のある方は\nご入場ください";
    }

    public void speak() {
        super.speak();
        gp.ui.npc = this;
    }

}
