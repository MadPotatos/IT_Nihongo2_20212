package entity;

import main.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;

public class Particle extends Entity {
    private Entity generator;
    private Color color;
    private int size;
    private int xd;
    private int yd;

    public Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(gp);
        this.setGenerator(generator);
        this.color = color;
        this.size = size;
        this.xd = xd;
        this.yd = yd;
        this.maxLife = maxLife;
        this.speed = speed;

        life = maxLife;
        int offset = (gp.tileSize / 2) - (size / 2);
        worldX = generator.worldX + offset;
        worldY = generator.worldY + offset;

    }

    public void update() {
        life--;
        if (life < maxLife / 3) {
            yd++;
        }
        worldX += xd * speed;
        worldY += yd * speed;
        if (life == 0) {
            alive = false;
        }

    }

    @Override
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size);
    }

    // Getter and setter
    public Entity getGenerator() {
        return generator;
    }

    public void setGenerator(Entity generator) {
        this.generator = generator;
    }

}
