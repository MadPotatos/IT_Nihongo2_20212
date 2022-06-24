package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.UtilityTool;


public class LoadSave {
	public static final int TYPE_PLAYER = 0;
	public static final int TYPE_NPC = 1;
	public static final int TYPE_MONSTER = 2;
	public static final int TYPE_SWORD = 3;
	public static final int TYPE_AXE = 4;
	public static final int TYPE_SHIELD = 5;
	public static final int TYPE_CONSUMABLE = 6;
	public static final int TYPE_PICKUPONLY = 7;
	
	//PLAYER
	public static final String PLAYER_WALK = "/Player/knight_walk.png";
	
	//MONSTERS
	public static final String MONSTER_MOLE = "/monsters/mole/Mole.png";
	public static final String MONSTER_SLIME = "/monsters/slime/Slime.png";
	public static final String MONSTER_TANUKI = "/monsters/tanuki/Tanuki.png";
	
	//BOSS
	public static final String BOSS_GIANTFLAM = "/monsters/boss/GaintFlam.png";
	
	
	//NPC
	public static final String NPC_OLDMAN = "/NPC/oldman/Oldman.png";
	public static final String NPC_MERCHANT = "/NPC/merchant/Merchant.png";
	
	
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream(fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	public static BufferedImage setup(String imagePath, int width, int height, int x, int y, int pixel) {
		UtilityTool uTool = new UtilityTool();
		InputStream is = LoadSave.class.getResourceAsStream(imagePath);
		BufferedImage img = null;
		BufferedImage imgSub = null;
		try {
			//image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		}
		imgSub = img.getSubimage(pixel*x, pixel*y, pixel, pixel);
		imgSub = uTool.scaleImage(imgSub, width, height);
		return imgSub;
	}
	public static BufferedImage setup(String fileName, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream(fileName + ".png");
		try {
			img = ImageIO.read(is);
			img = uTool.scaleImage(img, width, height);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}


}
