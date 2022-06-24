package entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Color;
import main.GamePanel;
import main.UtilityTool;
import object.Item;

import java.awt.Rectangle;
import utilz.LoadSave;

public abstract class Entity {
	GamePanel gp;

	public int speed;
	public BufferedImage down1, still, avatar;
	private int aniTick, aniIndex, aniSpeed = 15;
	private BufferedImage[][] animations;

	public BufferedImage[][] getAnimations() {
		return animations;
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

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= 4) {
				aniIndex = 0;
			}
		}
	}

	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
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

	// COUNTER
	private int actionLockCounter = 0;
	private int spriteCounter = 0;
	private int invincibleCounter = 0;
	private int shotAvailableCounter = 0;

	private int dyingCounter = 0;
	private int hpBarCounter = 0;
	// CHARACTER ATTRIBUTE
	protected String dialogues[] = new String[30];
	private int dialogueIndex = 0;
	private String name;
	private boolean collision = false;
	private int useCost;
	// Type
	private int type; // 0 = player, 1 = npc, 2 = monster

	// CHARACTER ATTRIBUTE
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

	public ArrayList<Item> inventory = new ArrayList<>();

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void setAction() {
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

	public void use(Entity entity) {
	}

	public void dropItem(Item droppedItem) {
		for (int i = 0; i < gp.obj[1].length; i++) {
			if (gp.obj[gp.currentMap][i] == null) {
				gp.obj[gp.currentMap][i] = droppedItem;
				gp.obj[gp.currentMap][i].worldX = worldX;
				gp.obj[gp.currentMap][i].worldY = worldY;
				break;
			}
		}
	}

	public void generateParticle(Entity generator, Entity target) {
		Color color = generator.getParticleColor();
		int size = generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getParticleMaxLife();

		Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
		Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
		Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
		Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
		gp.particleList.add(p1);
		gp.particleList.add(p2);
		gp.particleList.add(p3);
		gp.particleList.add(p4);

	}

	public void update() {
		setAction();

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

		if (type == LoadSave.TYPE_MONSTER || type == LoadSave.TYPE_NPC) {
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
			double hpBarValue = oneScale * life;
			g2.setColor(new Color(35, 35, 35));
			g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
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

	private Color getParticleColor() {
		Color color = null;
		return color;
	}

	private int getParticleSize() {
		int size = 0;
		return size;
	}

	private int getParticleSpeed() {
		int speed = 0;
		return speed;
	}

	private int getParticleMaxLife() {
		int maxLife = 0;
		return maxLife;
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
