package level;

import Entities.Entity;
import Tile.Tile;
import gfx.Screen;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Level {

    private byte[] tiles;
    public int width;
    public int height;
    
    private Random r = new Random();
    
   public List<Entity> entities = new ArrayList<Entity>();
    
    public Level(int width, int height) {
        
        
        
        tiles = new byte[width*height];
        this.height=height;
        this.width=width;
        this.generateLevel();
    }

    public void generateLevel() {
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(r.nextInt(200)%5!=0)
                    tiles[x + y *width] = Tile.GRASS.getID();
                else
                    tiles[x + y *width] = Tile.STONE.getID();
            }
        }
    }
    
    public void tick(){
        for (Entity e : entities) {
            e.tick();
        }
    }
  
   public void renderTiles(Screen screen, int xOffset, int yOffset) { //Camera, position of player
         if (xOffset < 0) xOffset = 0; 
         if (xOffset > ((width << 3) - screen.width)) xOffset = ((width << 3) - screen.width); 
         if (yOffset < 0) yOffset = 0; 
         if (yOffset > ((height << 3) - screen.height)) yOffset = ((height << 3) - screen.height); 

         screen.setOffset(xOffset, yOffset); 
 
         for (int y = (yOffset >> 3); y < (yOffset + screen.height >> 3) + 1; y++) { 
             for (int x = (xOffset >> 3); x < (xOffset + screen.width >> 3) + 1; x++) { 
                 getTile(x, y).render(screen, this, x << 3, y << 3); 
             } 
         } 
     } 

    public Tile getTile(int x, int y) { 
         if (0 > x || x >= width || 0 > y || y >= height) 
             return Tile.VOID; 
         return Tile.tiles[tiles[x + y * width]]; 
     } 

    
  public void renderEntities(Screen screen){
        for(Entity e: entities)
            e.render(screen);
    }
    
    public void addEntity(Entity e){
        this.entities.add(e);
    }
}