/**
 * Se declara BufferedImage con un modelo de color a 15 bits_RGB
 * BufferedImage is an image based of a ColorModel
 */

package juego2d;

import Entities.Monster;
import Entities.Player;
import gfx.HUD;
import gfx.Screen;
import gfx.Font;
import java.util.Arrays;
import gfx.SpriteSheet;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferUShort;
import level.Level;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 160, HEIGHT = 120, SCALE = 4;
    public static final String NAME = "Juego";
    
    Thread thread = new Thread(this);
    public Keyboard key;
    public Screen screen;
    public Level level;
    public Player player;
    public Monster monster;
    public HUD hud;
    public int vida = 40;
    public Font font;
    public boolean thread_running = false;
    public int tickCount = 0;
    
    private SpriteSheet sheet = new SpriteSheet("/sprite_sheet.png");
    
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);  //A bufferedImage is a pixel map image
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();          //represents the number of pixels are inside the image
    
    private int x = 0, y = 0;
    
    int score = 0;
    
    public Game() {
        Window window = new Window(HEIGHT, WIDTH, SCALE, NAME, this);
    }

    public synchronized void start(){
        thread_running = true;
        thread.start();
    }
    
    public synchronized void stop(){
        thread_running = false;
    }
    
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();       //Time since system startup
        double nsPerTick = 1000000000D / 60D;     //1 s / 60 --> 60 updates por segundo
        
        int ticks = 0;                           //updates
        int fps = 0;
        double delta = 0;

        key = new Keyboard(this);
        screen = new Screen(WIDTH, HEIGHT, sheet);
        level = new Level(75, 80);
        player = new Player(level, 0, 0, key, this);
        monster = new Monster(level, "bbo", x, y, 8);
        level.addEntity(player);
        hud = new HUD(screen);
        
        long lastTimer = System.currentTimeMillis();
               
        while(thread_running){
            long now = System.nanoTime();
            delta += (now - lastTime)/ nsPerTick;
            lastTime = now;
                        
            while(delta >=1){
                ticks++;
                tick();
                delta--;
            }
            try {
                thread.sleep(2);
            } catch (InterruptedException ex) { }
                fps++;
                render();
            
            if(System.currentTimeMillis() - lastTimer >= 1000){
                lastTimer += 1000;
                //System.out.println("FPS: " + fps + " Ticks:  " + ticks);
                fps = ticks = 0;
            }
        }   
        stop();
    }
    
    public void tick(){
        tickCount++;
        level.tick(); 
    }
    
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;                                                             //???
        }

        int xOffset = player.x - (screen.width/2);
        int yOffset = player.y - (screen.height/2);
      
        if(vida > 0){
        level.renderTiles(screen, xOffset, yOffset); 
        level.renderEntities(screen);
        hud.renderHUD(screen, vida);
        
        //Font.render("Nivel 10", screen, 70, 2, 0, 2);
        if(player.x < 74*8 ) score ++;
        }else{
            Font.render("Has Perdido...", screen, WIDTH/2-45+screen.xOffset, HEIGHT/2-10+screen.yOffset, 0, 1);
        }
        
        for(int y = 0; y < screen.height; y++){
            for(int x = 0; x < screen.width; x++)
 
                    pixels[x + y *WIDTH] = screen.pixels[x + y*screen.width];
        }        
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.drawString("Score" + score, 20, 70);
        g.dispose();
        bs.show();
    }
       
    public static void main(String[] args) {
        new Game().start();
    }
}
 