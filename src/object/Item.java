package object;

import java.awt.image.BufferedImage;

import entity.Entity;

import main.GamePanel;;

public class Item extends Entity {
    public BufferedImage image, image2, image3;
    public String name;
    GamePanel gp;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int type;

    public Item(GamePanel gp) {
        super(gp);
    }

}
