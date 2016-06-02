package Entities;

import gfx.Screen;
import level.Level;

public class Monster extends Mob {

    public Monster(Level level, String name, int x, int y, int speed) {
        super(level, name, x, y, speed);
    }

    public boolean hasCollided(int xa, int ya) {
        return false;
    }

    public void tick() {
    }

    public void render(Screen screen) {
    }

}
