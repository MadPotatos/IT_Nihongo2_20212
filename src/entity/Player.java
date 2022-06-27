package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.BeginnerSword;
import object.EnergyBall;
import object.HealingPotion;
import object.Item;
import object.Key;
import object.Shuriken;
import object.WoodenShield;

import utilz.LoadSave;

public class Player extends Entity {
	private GamePanel gp;
	private KeyHandler keyH;
	protected final int screenX;
	protected final int screenY;

	private int hasKey = 0;
	private int standCounter = 0;
	private boolean attackCanceled = false;
	private boolean attacking = false;

	// Count
	private int spriteCounter = 0;

	private BufferedImage imgWalk;
	// private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 15;
	private BufferedImage attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2,
			attackUp1, attackUp2;

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.gp = gp;
		this.keyH = keyH;
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		solidArea = new Rectangle(7, 7, 30, 30);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		setDefaultValue();

		loadAnimations(LoadSave.PLAYER_WALK);
		getPlayerImage();
		getPlayerAttackImage();
		setItems();

	}

	public void setDefaultPositions() {
		worldX = gp.tileSize * 11;
		worldY = gp.tileSize * 11;
		direction = "still";
	}

	public void restoreLifeAndMana() {
		life = maxLife;
		mana = maxMana;
		invincible = false;

	}

	public void setItems() {
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new Key(gp));
		inventory.add(new HealingPotion(gp));
		inventory.add(new HealingPotion(gp));

	}

	public void setDefaultValue() {
		setDefaultPositions();
		speed = 6;

		// PLAYER STATUS
		level = 1;
		maxLife = 6;
		maxMana = 4;
		ammo = 6;
		life = maxLife;
		mana = maxMana;
		strength = 100;
		endurance = 1;
		exp = 0;
		nextLevelExp = 10;
		coin = 500;
		currentWeapon = new BeginnerSword(gp);
		currentShield = new WoodenShield(gp);
		
		//projectile = new EnergyBall(gp);
		projectile = new Shuriken(gp);
		attack = getAttack(); // attack = strength + weapon attack
		defense = getDefense(); // defense = endurance + shield defense

	}

	private int getDefense() {
		return defense = endurance * currentShield.getDefenseValue();
	}

	private int getAttack() {
		attackArea = currentWeapon.attackArea;

		return attack = strength * currentWeapon.getAttackValue();
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

	public void getPlayerImage() {

		still = LoadSave.setup("/Player/still", gp.tileSize, gp.tileSize);
		avatar = LoadSave.setup("/Player/avatar", gp.tileSize, gp.tileSize);

	}

	public void getPlayerAttackImage() {
		if (currentWeapon.getType() == LoadSave.TYPE_SWORD) {
			attackUp1 = LoadSave.setup("/Player/attack_up1", gp.tileSize, gp.tileSize);
			attackUp2 = LoadSave.setup("/Player/attack_up2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = LoadSave.setup("/Player/attack_down1", gp.tileSize, gp.tileSize);
			attackDown2 = LoadSave.setup("/Player/attack_down2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = LoadSave.setup("/Player/attack_left1", gp.tileSize, gp.tileSize);
			attackLeft2 = LoadSave.setup("/Player/attack_left2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = LoadSave.setup("/Player/attack_right1", gp.tileSize, gp.tileSize);
			attackRight2 = LoadSave.setup("/Player/attack_right2", gp.tileSize * 2, gp.tileSize);
		}
		if (currentWeapon.getType() == LoadSave.TYPE_AXE) {
			attackUp1 = LoadSave.setup("/Player/attack_up1", gp.tileSize, gp.tileSize);
			attackUp2 = LoadSave.setup("/Player/axe_up2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = LoadSave.setup("/Player/attack_down1", gp.tileSize, gp.tileSize);
			attackDown2 = LoadSave.setup("/Player/axe_down2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = LoadSave.setup("/Player/attack_left1", gp.tileSize, gp.tileSize);
			attackLeft2 = LoadSave.setup("/Player/axe_left2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = LoadSave.setup("/Player/attack_right1", gp.tileSize, gp.tileSize);
			attackRight2 = LoadSave.setup("/Player/axe_right2", gp.tileSize * 2, gp.tileSize);
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
			// Check interactive tile collision
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

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
		if (gp.keyH.shotKeyPressed == true && projectile.alive == false && getShotAvailableCounter() == 30
				&& projectile.haveResource(this) == true) {
			// Set default
			projectile.set(worldX, worldY, direction, true, this);
			// subtract resource
			projectile.subtractResource(this);
			// add projectile to list
			gp.projectileList.add(projectile);
			setShotAvailableCounter(0);
			gp.playSE(10);

		}
		if (invincible == true) {
			setInvincibleCounter(getInvincibleCounter() + 1);
			if (getInvincibleCounter() > 60) {
				invincible = false;
				setInvincibleCounter(0);

			}
		}
		if (getShotAvailableCounter() < 30) {
			setShotAvailableCounter(getShotAvailableCounter() + 1);
		}
		if (life > maxLife) {
			life = maxLife;
		}
		if (mana > maxMana) {
			mana = maxMana;
		}
		if (life <= 0) {
			gp.gameState = gp.gameOverState;
			gp.ui.commandNum = -1;
			gp.stopMusic();
			gp.playSE(12);
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

			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			damageInteractiveTile(iTileIndex);

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

	private void damageInteractiveTile(int i) {
		if (i != 999 && gp.iTile[gp.currentMap][i].isDestructible() == true
				&& gp.iTile[gp.currentMap][i].isCorrectItem(this) == true
				&& gp.iTile[gp.currentMap][i].invincible == false) {
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;
			// Generate particle
			generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
			if (gp.iTile[gp.currentMap][i].life == 0) {
				gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
			}
		}
	}

	public void damageMonster(int i, int attack) {
		if (i != 999) {
			if (gp.monster[gp.currentMap][i].invincible == false) {
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if (damage < 0) {
					damage = 0;
				}
				gp.monster[gp.currentMap][i].life -= damage;
				gp.ui.addMessage(damage + " damage!");
				gp.monster[gp.currentMap][i].invincible = true;
				gp.monster[gp.currentMap][i].damageReaction();
				if (gp.monster[gp.currentMap][i].life <= 0) {
					gp.playSE(7);
					gp.monster[gp.currentMap][i].dying = true;
					gp.ui.addMessage(gp.monster[gp.currentMap][i].getName() + " is killed!");
					gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp);
					exp += gp.monster[gp.currentMap][i].exp;
					checkLevelUp();
				}
			}
		}
	}

	private void checkLevelUp() {
		if (exp >= nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp * 3;
			maxLife += 2;
			maxMana += 1;
			strength++;
			endurance++;
			attack = getAttack();
			defense = getDefense();
			life = maxLife;
			mana = maxMana;
			gp.playSE(8);
			gp.gameState = gp.informState;
			gp.ui.currentDialogue = "Level Up! \n Raise all attributes by 1 point. \n Press Enter to continue.";
		}
	}

	public void selectItem() {
		int itemIndex = gp.ui.getItemIndexonSlot(gp.ui.playerSlotCol,
				gp.ui.playerSlotRow);
		if (itemIndex < inventory.size()) {
			Item selectedItem = inventory.get(itemIndex);
			if (selectedItem.getType() == LoadSave.TYPE_AXE || selectedItem.getType() == LoadSave.TYPE_SWORD) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if (selectedItem.getType() == LoadSave.TYPE_SHIELD) {
				currentShield = selectedItem;
				defense = getDefense();
			}
			if (selectedItem.getType() == LoadSave.TYPE_CONSUMABLE) {
				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
		}
	}

	private void contactMonster(int i) {
		if (i != 999) {
			if (invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if (damage < 0) {
					damage = 0;
				}
				life -= damage;
				gp.playSE(5);
				invincible = true;
				setInvincibleCounter(0);

			}

		}
	}

	public void pickupObject(int i) {
		if (i != 999) {
			// PICKUP ONLY ITEMS
			if (gp.obj[gp.currentMap][i].getType() == LoadSave.TYPE_PICKUPONLY) {
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
			} else {
				String text;
				if (inventory.size() != maxInventorySize) {
					inventory.add(gp.obj[gp.currentMap][i]);
					gp.playSE(1);
					text = "You got a " + gp.obj[gp.currentMap][i].getName() + "!";
				} else {
					text = "Can't carry more items!";
				}
				gp.ui.addMessage(text);
				gp.obj[gp.currentMap][i] = null;
			}
		}

	}

	public void interactNPC(int i) {
		if (gp.keyH.enterPressed == true) {
			if (i != 999) {
				attackCanceled = true;

				gp.gameState = gp.dialogueState;
				gp.npc[gp.currentMap][i].speak();

			}
		}

	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		updateAnimationTick();
		BufferedImage[][] animations = getAnimations();
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		switch (direction) {
			case "up":
				if (attacking == false) {
					if (keyH.isUpPressed() == true) {
						image = animations[1][aniIndex];
					} else {
						image = animations[1][0];
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
					;
					if (keyH.isDownPressed() == true) {
						image = animations[0][aniIndex];
					} else {
						image = animations[0][0];
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
					if (keyH.isLeftPressed() == true) {
						image = animations[2][aniIndex];
					} else {
						image = animations[2][0];
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
					if (keyH.isRightPressed() == true) {
						image = animations[3][aniIndex];
					} else {
						image = animations[3][0];
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

	// Getter and setter
	public int getScreenX() {
		return screenX;
	}

	public int getScreenY() {
		return screenY;
	}

	public int getHasKey() {
		return hasKey;
	}

	public void setHasKey(int hasKey) {
		this.hasKey = hasKey;
	}

	public boolean isAttackCanceled() {
		return attackCanceled;
	}

	public void setAttackCanceled(boolean attackCanceled) {
		this.attackCanceled = attackCanceled;
	}

}
