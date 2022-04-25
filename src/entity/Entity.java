package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Rectangle;

public class Entity {
	GamePanel gp;
	public int worldX, worldY;
	public int speed;
	public BufferedImage left1, left2, still, right1, right2, down1, down2, up1, up2;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;

	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.titleSize, gp.titleSize);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
