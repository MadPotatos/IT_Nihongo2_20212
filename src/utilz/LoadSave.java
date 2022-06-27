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
	public static final int TYPE_PROJECTTILE = 8;


	// PLAYER
	public static final String PLAYER_WALK = "/Player/knight_walk.png";

	// MONSTERS
	public static final String MONSTER_MOLE = "/monsters/mole/Mole.png";
	public static final String MONSTER_SLIME = "/monsters/slime/Slime.png";
	public static final String MONSTER_TANUKI = "/monsters/tanuki/Tanuki.png";

	// BOSS
	public static final String BOSS_GIANTFLAM = "/monsters/boss/GaintFlam.png";

	// NPC
	public static final String NPC_OLDMAN = "/NPC/oldman/Oldman.png";
	public static final String NPC_MERCHANT = "/NPC/merchant/Merchant.png";

	//PROJECTILE 
	public static final String PROTILE_ENERGYBALL = "/projectile/energyball.png";
	public static final String PROTILE_SURIKEN = "/projectile/shuriken.png";
	
	
	// UI
	public static final BufferedImage bg = setup("/ui/bg", 1200, 659);
	public static final BufferedImage KB_A = setup("/ui/a", 16, 16);
	public static final BufferedImage KB_A_P = setup("/ui/a_p", 16, 16);
	public static final BufferedImage KB_B = setup("/ui/b", 16, 16);
	public static final BufferedImage KB_D = setup("/ui/d", 16, 16);
	public static final BufferedImage KB_D_P = setup("/ui/d_p", 16, 16);
	public static final BufferedImage KB_C = setup("/ui/c", 16, 16);
	public static final BufferedImage KB_C_P = setup("/ui/c_p", 16, 16);
	public static final BufferedImage KB_F = setup("/ui/f", 16, 16);
	public static final BufferedImage KB_F_P = setup("/ui/f_p", 16, 16);
	public static final BufferedImage KB_S = setup("/ui/s", 16, 16);
	public static final BufferedImage KB_S_P = setup("/ui/s_p", 16, 16);
	public static final BufferedImage KB_W = setup("/ui/w", 16, 16);
	public static final BufferedImage KB_W_P = setup("/ui/w_p", 16, 16);
	public static final BufferedImage KB_ENTER = setup("/ui/enter", 48, 16);
	public static final BufferedImage KB_ENTER_P = setup("/ui/enter_p", 48, 16);
	public static final BufferedImage KB_ESC = setup("/ui/esc", 32, 16);
	public static final BufferedImage KB_ESC_P = setup("/ui/esc_p", 32, 16);

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
			// image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		}
		imgSub = img.getSubimage(pixel * x, pixel * y, pixel, pixel);
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
