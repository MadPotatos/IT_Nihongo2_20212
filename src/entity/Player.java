package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.Dictionary;
import java.awt.AlphaComposite;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.BeginnerSword;
import object.DStradeMark;
import object.EnergyBall;
import object.HealingPotion;
import object.Key;
import object.WoodenShield;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	int standCounter = 0;
	public boolean attackCanceled = false;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.gp = gp;
		this.keyH = keyH;
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		solidArea = new Rectangle();
		solidArea.x = 10;
		solidArea.y = 10;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;

		setDefaultValue();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();

	}

	private void setItems() {
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new Key(gp));
		inventory.add(new HealingPotion(gp));
		inventory.add(new DStradeMark(gp));
		inventory.add(new HealingPotion(gp));

	}

	public void setDefaultValue() {
		worldX = gp.tileSize * 15;
		worldY = gp.tileSize * 15;
		speed = 5;
		direction = "still";
		// PLAYER STATUS
		level = 1;
		maxLife = 6;
		maxMana = 4;
		ammo = 6;
		life = maxLife;
		mana = maxMana;
		strength = 1;
		endurance = 1;
		exp = 0;
		nextLevelExp = 5;
		coin = 0;
		currentWeapon = new BeginnerSword(gp);
		currentShield = new WoodenShield(gp);
		projectile = new EnergyBall(gp);

		attack = getAttack(); // attack = strength + weapon attack
		defense = getDefense(); // defense = endurance + shield defense

	}

	private int getDefense() {
		return defense = endurance * currentShield.defenseValue;
	}

	private int getAttack() {
		attackArea = currentWeapon.attackArea;

		return attack = strength * currentWeapon.attackValue;
	}

	public void getPlayerImage() {

		up1 = setup("/Player/up1", gp.tileSize, gp.tileSize);
		up2 = setup("/Player/up2", gp.tileSize, gp.tileSize);
		down1 = setup("/Player/down1", gp.tileSize, gp.tileSize);
		down2 = setup("/Player/down2", gp.tileSize, gp.tileSize);
		left1 = setup("/Player/left1", gp.tileSize, gp.tileSize);
		left2 = setup("/Player/left2", gp.tileSize, gp.tileSize);
		right1 = setup("/Player/right1", gp.tileSize, gp.tileSize);
		right2 = setup("/Player/right2", gp.tileSize, gp.tileSize);
		still = setup("/Player/still", gp.tileSize, gp.tileSize);
		avatar = setup("/Player/avatar", gp.tileSize, gp.tileSize);

	}

	public void getPlayerAttackImage() {
		if (currentWeapon.type == type_sword) {
			attackUp1 = setup("/Player/attack_up1", gp.tileSize, gp.tileSize);
			attackUp2 = setup("/Player/attack_up2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setup("/Player/attack_down1", gp.tileSize, gp.tileSize);
			attackDown2 = setup("/Player/attack_down2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setup("/Player/attack_left1", gp.tileSize, gp.tileSize);
			attackLeft2 = setup("/Player/attack_left2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setup("/Player/attack_right1", gp.tileSize, gp.tileSize);
			attackRight2 = setup("/Player/attack_right2", gp.tileSize * 2, gp.tileSize);
		}
		if (currentWeapon.type == type_axe) {
			attackUp1 = setup("/Player/attack_up1", gp.tileSize, gp.tileSize);
			attackUp2 = setup("/Player/axe_up2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setup("/Player/attack_down1", gp.tileSize, gp.tileSize);
			attackDown2 = setup("/Player/axe_down2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setup("/Player/attack_left1", gp.tileSize, gp.tileSize);
			attackLeft2 = setup("/Player/axe_left2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setup("/Player/attack_right1", gp.tileSize, gp.tileSize);
			attackRight2 = setup("/Player/axe_right2", gp.tileSize * 2, gp.tileSize);
		}
	}

	public void update() {
		if (attacking == true) {
			attacking();

		} else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true || keyH.enterPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";

			} else if (keyH.downPressed == true) {
				direction = "down";

			} else if (keyH.leftPressed == true) {
				direction = "left";

			} else if (keyH.rightPressed == true) {
				direction = "right";

			}
			// Check tite collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			// Check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickupObject(objIndex);
			// Check NPC collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			// Check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);

			// Check event
			gp.eHandler.checkEvent();

			// if collision is false , player can move
			if (collisionOn == false && keyH.enterPressed == false) {
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
			if (keyH.enterPressed == true && attackCanceled == false) {
				gp.playSE(6);
				attacking = true;
				spriteCounter = 0;

			}
			attackCanceled = false;
			gp.keyH.enterPressed = false;

			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		} else {
			standCounter++;
			if (standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
		}
		if (gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30
				&& projectile.haveResource(this) == true) {
			// Set default
			projectile.set(worldX, worldY, direction, true, this);
			// subtract resource
			projectile.subtractResource(this);
			// add projectile to list
			gp.projectileList.add(projectile);
			shotAvailableCounter = 0;
			gp.playSE(10);

		}
		if (invincible == true) {
			invincibleCounter++;

			if (invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if (shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
	}

	private void attacking() {
		spriteCounter++;
		if (spriteCounter <= 5) {
			spriteNum = 1;
		}
		if (spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
			// save the current position of the player
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			// adjust the position of the attack area
			switch (direction) {
				case "up":
					worldY -= attackArea.height;
					break;
				case "down":
					worldY += attackArea.height;
					break;
				case "left":
					worldX -= attackArea.width;
					break;
				case "right":
					worldX += attackArea.width;
					break;

			}
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			// check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex, attack);
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if (spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}

	public void damageMonster(int i, int attack) {
		if (i != 999) {
			if (gp.monster[i].invincible == false) {
				int damage = attack - gp.monster[i].defense;
				if (damage < 0) {
					damage = 0;
				}
				gp.monster[i].life -= damage;
				gp.ui.addMessage(damage + " damage!");
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();
				if (gp.monster[i].life <= 0) {
					gp.playSE(7);
					gp.monster[i].dying = true;
					gp.ui.addMessage(gp.monster[i].name + " is killed!");
					gp.ui.addMessage("Exp + " + gp.monster[i].exp);
					exp += gp.monster[i].exp;
					checkLevelUp();
				}
			}
		}
	}

	private void checkLevelUp() {
		if (exp >= nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp * 2;
			maxLife += 2;
			maxMana += 1;
			strength++;
			endurance++;
			attack = getAttack();
			defense = getDefense();
			life = maxLife;
			mana = maxMana;
			gp.playSE(8);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "Level Up! \n Raise all attributes by 1 point. \n Press Enter to continue.";
		}
	}

	public void selectItem() {
		int itemIndex = gp.ui.getItemIndexonSlot();
		if (itemIndex < inventory.size()) {
			Entity selectedItem = inventory.get(itemIndex);
			if (selectedItem.type == type_axe || selectedItem.type == type_sword) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if (selectedItem.type == type_shield) {
				currentShield = selectedItem;
				defense = getDefense();
			}
			if (selectedItem.type == type_consumable) {
				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
		}
	}

	private void contactMonster(int i) {
		if (i != 999) {
			if (invincible == false && gp.monster[i].dying == false) {
				int damage = gp.monster[i].attack - defense;
				if (damage < 0) {
					damage = 0;
				}
				life -= damage;
				gp.playSE(5);
				invincible = true;
				invincibleCounter = 0;
			}

		}
	}

	public void pickupObject(int i) {
		if (i != 999) {
			String text;
			if (inventory.size() != maxInventorySize) {
				inventory.add(gp.obj[i]);
				gp.playSE(1);
				text = "You got a " + gp.obj[i].name + "!";
			} else {
				text = "Can't carry more items!";
			}
			gp.ui.addMessage(text);
			gp.obj[i] = null;

		}

	}

	public void interactNPC(int i) {
		if (gp.keyH.enterPressed == true) {
			if (i != 999) {
				attackCanceled = true;

				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();

			}
		}

	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		switch (direction) {
			case "up":
				if (attacking == false) {

					if (spriteNum == 1) {
						image = up1;
					}
					if (spriteNum == 2) {
						image = up2;
					}
				}
				if (attacking == true) {

					if (spriteNum == 1) {
						image = attackUp1;
					}
					if (spriteNum == 2) {
						tempScreenY = screenY - gp.tileSize;
						image = attackUp2;
					}
				}
				break;
			case "down":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = down1;
					}
					if (spriteNum == 2) {
						image = down2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackDown1;
					}
					if (spriteNum == 2) {
						image = attackDown2;
					}
				}
				break;
			case "left":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = left1;
					}
					if (spriteNum == 2) {
						image = left2;
					}
				}
				if (attacking == true) {

					if (spriteNum == 1) {
						image = attackLeft1;
					}
					if (spriteNum == 2) {
						tempScreenX = screenX - gp.tileSize;
						image = attackLeft2;
					}
				}
				break;
			case "right":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = right1;
					}
					if (spriteNum == 2) {
						image = right2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackRight1;
					}
					if (spriteNum == 2) {
						image = attackRight2;
					}
				}
				break;
			case "still":
				image = still;
				break;
		}

		if (screenX > worldX)
			tempScreenX = worldX - (screenX - tempScreenX);
		if (screenY > worldY)
			tempScreenY = worldY - (screenY - tempScreenY);

		int rightOffset = gp.screenWidth - screenX;
		if (rightOffset > gp.worldWidth - worldX) {
			tempScreenX = (gp.screenWidth - (gp.worldWidth - worldX)) - (screenX - tempScreenX);
		}
		int bottomOffset = gp.screenHeight - screenY;
		if (bottomOffset > gp.worldHeight - worldY) {
			tempScreenY = (gp.screenHeight - (gp.worldHeight - worldY)) - (screenY - tempScreenY);
		}
		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

	}
}
