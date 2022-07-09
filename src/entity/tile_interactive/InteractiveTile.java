package entity.tile_interactive;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity {
    private GamePanel gp;
    private boolean destructible = false;

    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.setGp(gp);

    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

    public void playSE() {
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = null;
        return tile;
    }

    public void update() {
        int invincibleCounter = getInvincibleCounter();
        if (invincible == true) {

            invincibleCounter++;
            setInvincibleCounter(invincibleCounter);
            if (invincibleCounter > 20) {
                invincible = false;
                setInvincibleCounter(0);
            }
        }
    }

    // Getter and setter
    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public boolean isDestructible() {
        return destructible;
    }

    public void setDestructible(boolean destructible) {
        this.destructible = destructible;
    }

}
