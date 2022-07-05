package monster;

import main.GamePanel;

import utilz.LoadSave;

public class Tanuki extends Monster {
    private GamePanel gp;

    public Tanuki(GamePanel gp) {
        super(gp);
        this.gp = gp;
        // TODO Auto-generated constructor stub

        setName("Tanuki");
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 5;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;

        solidArea.width = 36;
        solidArea.height = 30;
        loadAnimations(LoadSave.MONSTER_TANUKI);

    }

}
