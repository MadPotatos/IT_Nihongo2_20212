package entity;

import main.GamePanel;
import main.UtilityTool;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NPC_Inspector extends Entity {

    public NPC_Inspector(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        direction = "down";
        speed = 1;
        getImage();
    }

    public void getImage() {

        up1 = setup("/NPC/inspector_up1");
        up2 = setup("/NPC/inspector_up2");
        down1 = setup("/NPC/inspector_down1");
        down2 = setup("/NPC/inspector_down2");
        left1 = setup("/NPC/inspector_left1");
        left2 = setup("/NPC/inspector_left2");
        right1 = setup("/NPC/inspector_right1");
        right2 = setup("/NPC/inspector_right2");
    }

}
