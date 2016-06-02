package Entities;

import gfx.HUD;
import level.Level;
import juego2d.Keyboard;
import gfx.Screen;
import juego2d.Game;

public class Player extends Mob{

    private Keyboard input;
    
    private Screen screen;
    private int i = 1;
    boolean shooting = false;
    private Game game;
    
    public Player(Level level, int x, int y, Keyboard input, Game game) {
        super(level, "Player", x, y, 1);
        this.input = input;
        this.game = game;
    }
    
    public void tick() {
        int xa = 0;
        int ya = 0;
        
        if (input.up.isPressed())ya--;
        if (input.down.isPressed())ya++;
        if (input.left.isPressed())xa--;
        if (input.right.isPressed())xa++;
        if (input.space.isPressed())shooting = true; else shooting = false;               
        if(xa != 0 || ya != 0){
            if(hasCollided(xa, ya)) game.vida --;
            move(xa, ya);
            isMoving = true;
        }else
            isMoving = false;
    }
    
    public void render(Screen screen) {
        this.screen = screen;
        int xTile = 0;
        int yTile = 28;
        int walkingSpeed = 4;
        int flipTop = (numSteps >> walkingSpeed)&1;
        int flipBottom = (numSteps >> walkingSpeed)&1;
        
        if(movingDir == 1){
            xTile += 2;
        } else if(movingDir > 1){
            xTile += 4 + ((numSteps >> walkingSpeed)&1)*2;
            flipTop = (movingDir - 1) % 2;  
        }
        
        int xOffset = x - 8 / 2;
        int yOffset = y - 8 / 2 - 4;
        
        int [] color = new int[4];
        color[0]= -1;
        color[1]= 0x0000;
        color[2]= 0b000001100111001;
        color[3]= 0b111111101110111;
        int [] color2 = new int [4];
        color2[0]= -1;
        color2[1]= 0;
        color2[2]= 0b000001100111001;
        color2[3]= 0b111111101110111;
        
        
        if(isMoving || (movingDir == 0 && !isMoving) ||(movingDir == 1 && !isMoving)){
            screen.render( xOffset + (8 * flipTop),       yOffset,          xTile + yTile * 32,            flipTop,    color);
            screen.render( xOffset + 8 - (8 * flipTop),   yOffset,          (xTile + 1) + yTile * 32,      flipTop,    color);
            screen.render( xOffset + 8*flipBottom,        yOffset + 8,      xTile + (yTile + 1) * 32,      flipBottom, color);
            screen.render( xOffset + 8 - 8*flipBottom,    yOffset + 8,      xTile + 1 + (yTile + 1) * 32,  flipBottom, color);
        }else if(movingDir == 3 && !isMoving){
            screen.render( xOffset + 8,    yOffset,      5 + 28 * 32,      0, color);
            screen.render( xOffset,    yOffset,      4 + 28 * 32,  0, color);
            screen.render( xOffset + 8,    yOffset + 8,      5 + 29 * 32,      0, color);
            screen.render( xOffset,    yOffset + 8,      4 + 29 * 32,  0, color);
        }else if(movingDir == 2 && !isMoving){
            screen.render( xOffset + 8,    yOffset,      4 + 28 * 32,      01, color);
            screen.render( xOffset,    yOffset,      5 + 28 * 32,  01, color);
            screen.render( xOffset + 8,    yOffset + 8,      4 + 29 * 32,      01, color);
            screen.render( xOffset,    yOffset + 8,      5 + 29 * 32,  01, color);
        }
        
        if(shooting){
            if(movingDir == 2){screen.render(xOffset-2, yOffset+9, 8 + 28*32, 0x01, color2);
            i+=4;if(i>60)i=1;
            screen.render(xOffset-3-i, yOffset+9, 9 + 28*32, 0, color2);
            }
            if(movingDir == 3){screen.render(xOffset+10, yOffset+9, 8 + 28*32, 0, color2);
            i+=4;if(i>60)i=1;
            screen.render(xOffset+11+i, yOffset+9, 9 + 28*32, 0, color2);
            }
        }else i = 1;
        
    }

    public boolean hasCollided(int xa, int ya) {
        int xMin = 0, xMax = 7, yMin = 3, yMax = 7;
        for(int x = xMin; x < xMax; x++){
            if (isSolid(xa, ya, x, yMin))return true;
            else if(isSolid(xa, ya, x, yMax))return true;
        }
        for(int y = yMin; y < yMax; y++){
            if (isSolid(xa, ya, xMin, y))return true;
            else if (isSolid(xa, ya, xMax, y))return true;
        }
        return false;
    }
}