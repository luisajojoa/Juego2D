package Tile;

import gfx.Screen;
import level.Level;

public class BaseTile extends Tile {
    
    protected int id;
    protected int color1;
    protected int color2;
    protected int color3;
    protected int color4;

    public BaseTile(int id, int x, int y, int color1, int color2, int color3, int color4) {
        super(id, false, false);
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
        this.id = x + y*32;
    }

    public void render(Screen screen, Level level, int x, int y) {
        screen.render(x, y, this.id, 0x00, color1, color2, color3, color4);
    }

}
