package object;

import java.awt.image.BufferedImage;

import entity.Entity;

import main.GamePanel;;

public class Item extends Entity {
	private BufferedImage image, image2, image3;
	private String name;
	private GamePanel gp;
	private int value;
	private int attackValue;
	private int defenseValue;
	private String description = "";
	private int useCost;
	private int price;

	// private int type;

	public Item(GamePanel gp) {
		super(gp);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getImage2() {
		return image2;
	}

	public void setImage2(BufferedImage image2) {
		this.image2 = image2;
	}

	public BufferedImage getImage3() {
		return image3;
	}

	public void setImage3(BufferedImage image3) {
		this.image3 = image3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GamePanel getGp() {
		return gp;
	}

	public void setGp(GamePanel gp) {
		this.gp = gp;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getAttackValue() {
		return attackValue;
	}

	public void setAttackValue(int attackValue) {
		this.attackValue = attackValue;
	}

	public int getDefenseValue() {
		return defenseValue;
	}

	public void setDefenseValue(int defenseValue) {
		this.defenseValue = defenseValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUseCost() {
		return useCost;
	}

	public void setUseCost(int useCost) {
		this.useCost = useCost;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
