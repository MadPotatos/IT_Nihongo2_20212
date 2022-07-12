package entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.item.Item;
import entity.projectile.Projectile;

import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Color;
import main.GamePanel;
import main.UtilityTool;

import java.awt.Rectangle;
import utilz.LoadSave;

public abstract class Entity {
	GamePanel gp;

	public int speed;
	public BufferedImage down1, avatar;
	private int aniTick, aniIndex, aniSpeed = 15;
	private BufferedImage[][] animations;

	public Rectangle solidArea = new Rectangle(0, 0, 32, 32);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	// STATE
	public int worldX, worldY;
	public String direction = "down";

	public int spriteNum = 1;
	public boolean collisionOn = false;
	public boolean invincible = false;
	public boolean alive = true;
	public boolean dying = false;
	private boolean hpBarOn = false;
	public boolean onPath = false;
	public boolean knockBack = false;

	// COUNTER
	private int actionLockCounter = 0;
	private int spriteCounter = 0;
	private int invincibleCounter = 0;
	private int shotAvailableCounter = 0;
	private int knockBackCounter = 0;

	private int dyingCounter = 0;
	private int hpBarCounter = 0;

	private int type;
	// CHARACTER ATTRIBUTE
	protected String dialogues[] = new String[30];
	private int dialogueIndex = 0;
	private String name;
	private boolean collision = false;
	private int useCost;
	public int defaultSpeed;
	public int maxLife;
	public int life;
	public int mana;
	public int maxMana;
	public int ammo;
	public int level;
	public int strength;
	public int endurance;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public Item currentWeapon;
	public Item currentShield;
	public Projectile projectile;
	public final int maxInventorySize = 20;
	// ITEM ATTRIBUTE
	public int knockBackPower = 0;
	public ArrayList<Item> inventory = new ArrayList<>();

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public BufferedImage[][] getAnimations() {
		return animations;
	}

	public int getLeftX() {
		return worldX + solidArea.x;
	}

	public int getRightX() {
		return worldX + solidArea.x + solidArea.width;
	}

	public int getTopY() {
		return worldY + solidArea.y;
	}

	public int getBottomY() {
		return worldY + solidArea.y + solidArea.height;
	}

	public int getCol() {
		return (worldX + solidArea.x) / gp.tileSize;
	}

	public int getRow() {
		return (worldY + solidArea.y) / gp.tileSize;
	}

	public void setAnimations(BufferedImage[][] animations) {
		this.animations = animations;
	}

	public void loadAnimations(String name) {
		BufferedImage imgWalk = LoadSave.GetSpriteAtlas(name);
		UtilityTool uTool = new UtilityTool();
		BufferedImage[][] animations = new BufferedImage[4][4];
		for (int j = 0; j < animations.length; j++) {
			for (int i = 0; i < animations[j].length; i++) {
				animations[j][i] = imgWalk.getSubimage(j * 16, i * 16, 16, 16);
				animations[j][i] = uTool.scaleImage(animations[j][i], gp.tileSize, gp.tileSize);
			}
		}
		setAnimations(animations);
	}

	protected void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= 4) {
				aniIndex = 0;
			}
		}
	}

	public void setAction() {
	}

	public int getDetected(Entity user, Item target[][], String targetName) {
		int index = 999;
		// Check surrounding object
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();

		switch (user.direction) {
			case "up":
				nextWorldY = user.getTopY() - 1;
				break;
			case "down":
				nextWorldY = user.getBottomY() + 1;
				break;
			case "left":
				nextWorldX = user.getLeftX() - 1;
				break;
			case "right":
				nextWorldX = user.getRightX() + 1;
				break;
		}
		int col = nextWorldX / gp.tileSize;
		int row = nextWorldY / gp.tileSize;

		for (int i = 0; i < target[1].length; i++) {
			if (target[gp.currentMap][i] != null) {
				if (target[gp.currentMap][i].getCol() == col && target[gp.currentMap][i].getRow() == row
						&& target[gp.currentMap][i].getName().equals(targetName)) {
					index = i;
					break;
				}
			}

		}
		return index;
	}

	public void speak() {
		if (dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		switch (gp.player.direction) {
			case "up":
				direction = "down";
				break;
			case "down":
				direction = "up";
				break;
			case "left":
				direction = "right";
				break;
			case "right":
				direction = "left";
				break;
		}

	}

	public boolean use(Entity entity) {
		return false;
	}

	public void checkCollision() {
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		gp.cChecker.checkEntity(this, gp.iTile);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);

		if (type == LoadSave.TYPE_MONSTER && contactPlayer == true) {
			damagePlayer(attack);

		}
	}

	public void update() {
		if (knockBack == true) {
			checkCollision();
			if (collisionOn == true) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			} else if (collisionOn == false) {
				switch (gp.player.direction) {
					case "up":
						worldY -= speed;
						break;
					case "down":
						worldY += speed;
						break;
					case "left":
						worldX -= speed;
						break;
					case "right":
						worldX += speed;
						break;
				}
			}
			knockBackCounter++;
			if (knockBackCounter == 5) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
		} else {
			setAction();
			checkCollision();

			if (collisionOn == false) {
				switch (direction) {
					case "up":
						worldY -= speed;
						break;
					case "down":
						worldY += speed;
						break;
					case "left":
						worldX -= speed;
						break;
					case "right":
						worldX += speed;
						break;
				}
			}

		}

		spriteCounter++;
		if (spriteCounter > 15) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		if (invincible == true) {
			invincibleCounter++;
		}
		if (invincibleCounter > 40) {
			invincible = false;
			invincibleCounter = 0;
		}
		if (shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}

	}

	public void damagePlayer(int attack) {
		if (gp.player.invincible == false) {
			int damage = attack - gp.player.defense;
			if (damage < 0) {
				damage = 0;
			}
			gp.player.life -= damage;
			gp.player.invincible = true;

			gp.playSE(5);
		}

	}

	public void draw(Graphics2D g2) {
		updateAnimationTick();

		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		// STOP MOVING CAMERA
		if (gp.player.worldX < gp.player.screenX) {
			screenX = worldX;
		}
		if (gp.player.worldY < gp.player.screenY) {
			screenY = worldY;
		}
		int rightOffset = gp.screenWidth - gp.player.screenX;
		if (rightOffset > gp.worldWidth - gp.player.worldX) {
			screenX = gp.screenWidth - (gp.worldWidth - worldX);
		}
		int bottomOffset = gp.screenHeight - gp.player.screenY;
		if (bottomOffset > gp.worldHeight - gp.player.worldY) {
			screenY = gp.screenHeight - (gp.worldHeight - worldY);
		}
		///////////////////

		if (type == LoadSave.TYPE_MONSTER || type == LoadSave.TYPE_NPC || type == LoadSave.TYPE_PROJECTTILE) {
			switch (direction) {
				case "up":
					image = animations[1][aniIndex];
					break;
				case "down":
					image = animations[0][aniIndex];
					break;
				case "left":
					image = animations[2][aniIndex];
					break;
				case "right":
					image = animations[3][aniIndex];
					break;
			}
		} else {

			image = down1;

		}

		// Monster HP bar
		if (type == LoadSave.TYPE_MONSTER && hpBarOn == true) {
			double oneScale = (double) gp.tileSize / maxLife;
			int hpBarwidth = gp.tileSize;

			if (getName() == "GiantFlam") {
				hpBarwidth = 3 * gp.tileSize + 2;
				oneScale = (double) 3 * gp.tileSize / maxLife;
			}
			double hpBarValue = oneScale * life;
			g2.setColor(new Color(35, 35, 35));
			g2.fillRect(screenX - 1, screenY - 16, hpBarwidth + 2, 12);
			g2.setColor(new Color(255, 0, 30));
			g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);
			hpBarCounter++;
			if (hpBarCounter > 300) {
				hpBarCounter = 0;
				hpBarOn = false;
			}
		}

		if (invincible == true) {
			hpBarOn = true;
			hpBarCounter = 0;
			changeAlpha(g2, 0.4f);

		}
		if (dying == true) {
			dyingAnimation(g2);
		}
		// Monster alert
		if (onPath == true) {
			g2.drawImage(LoadSave.ALERT, screenX, screenY - 60, gp.tileSize, gp.tileSize, null);
		}

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			g2.drawImage(image, screenX, screenY, null);
			changeAlpha(g2, 1f);

		}
		// If player is around the edge, draw everything
		else if (gp.player.worldX < gp.player.screenX ||
				gp.player.worldY < gp.player.screenY ||
				rightOffset > gp.worldWidth - gp.player.worldX ||
				bottomOffset > gp.worldHeight - gp.player.worldY) {
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}

	private void dyingAnimation(Graphics2D g2) {

		dyingCounter++;
		int i = 5;

		if (dyingCounter <= i) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i && dyingCounter <= i * 2) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 8) {

			alive = false;
		}

	}

	private void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}

	// Getter and Setter

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public int getUseCost() {
		return useCost;
	}

	public void setUseCost(int useCost) {
		this.useCost = useCost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getShotAvailableCounter() {
		return shotAvailableCounter;
	}

	public void setShotAvailableCounter(int shotAvailableCounter) {
		this.shotAvailableCounter = shotAvailableCounter;
	}

	public int getInvincibleCounter() {
		return invincibleCounter;
	}

	public void setInvincibleCounter(int invincibleCounter) {
		this.invincibleCounter = invincibleCounter;
	}

	public int getActionLockCounter() {
		return actionLockCounter;
	}

	public void setActionLockCounter(int actionLockCounter) {
		this.actionLockCounter = actionLockCounter;
	}

	public int getAniIndex() {
		return aniIndex;
	}

	public void setAniIndex(int aniIndex) {
		this.aniIndex = aniIndex;
	}

	public int getAniSpeed() {
		return aniSpeed;
	}

	public void setAniSpeed(int aniSpeed) {
		this.aniSpeed = aniSpeed;
	}

	public int getAniTick() {
		return aniTick;
	}

	public void setAniTick(int aniTick) {
		this.aniTick = aniTick;
	}

}