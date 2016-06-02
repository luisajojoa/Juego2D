package gfx;
import gfx.Screen;
import Entities.Player;
import Tile.Tile;


public class HUD {

    private int [] color = new int[4];
    
    private Player player;
    
    public HUD(Screen screen) {
        color[0]= -1;
        color[1]= 0x0000;
        color[2]= 0b111110000000000;
        color[3]= 0b111111111111111;
    }

    public void renderHUD(Screen screen, int vida) {
        vida = vida /10;
        for (int i = 0; i < vida/2; i++)
            screen.render(screen.xOffset + i*10, screen.yOffset + 4, 8, 0, color);

        for (int i = (vida / 2); i < 4; i++)
            screen.render(screen.xOffset + i*10, screen.yOffset + 4, 11, 0, color);
            
        if(vida % 2 == 1) screen.render(screen.xOffset + (vida / 2) * 10, screen.yOffset + 4, 10, 0, color);
        for (int i = 0; i < screen.height; i++) 
            screen.render(8*73, i*8, 0, vida);
    }

    
    
}
