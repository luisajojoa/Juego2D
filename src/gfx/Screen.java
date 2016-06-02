package gfx;

public class Screen { 
 
    public static final int MAP_WIDTH = 64; 
    public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1; 
 

     public static final byte BIT_MIRROR_X = 0x01; 
     public static final byte BIT_MIRROR_Y = 0x02; 
 
     public int[] pixels; 
 
     public int xOffset = 5; 
     public int yOffset = 0; 
 
     public int width; 
     public int height; 
 
     public SpriteSheet sheet; 
 
 
     public Screen(int width, int height, SpriteSheet sheet) { 
         this.width = width; 
         this.height = height; 
         this.sheet = sheet; 
 
 
         pixels = new int[width * height]; 
     } 
    public void render(int xPos, int yPos, int tile, int color){
        render(xPos, yPos, tile, 0, -1, -1, -1, color);
    }
   
     public void render(int xPos, int yPos, int tile, int mirrorDir, int[]color){
        render(xPos, yPos, tile, mirrorDir, color[0], color[1], color[2], color[3]);
     }
     public void render(int xPos, int yPos, int tile, int mirrorDir, int color1, int color2, int color3, int color4) { 
         xPos -= xOffset; 
         yPos -= yOffset; 
         int scale = 1;
 
         boolean mirrorX = (mirrorDir & BIT_MIRROR_X) > 0; 
         boolean mirrorY = (mirrorDir & BIT_MIRROR_Y) > 0; 
 
         int scaleMap = scale - 1; 
         int xTile = tile % 32; 
         int yTile = tile / 32; 
         int tileOffset = (xTile << 3) + (yTile << 3) * sheet.width; 
         for (int y = 0; y < 8; y++) { 
             int ySheet = y; 
             if (mirrorY) 
                 ySheet = 7 - y; 
 
 
             int yPixel = y + yPos + (y * scaleMap) - ((scaleMap << 3) / 2); 
 

             for (int x = 0; x < 8; x++) { 
                 int xSheet = x; 
                 if (mirrorX) 
                     xSheet = 7 - x; 
                 int xPixel = x + xPos + (x * scaleMap) - ((scaleMap << 3) / 2); 
                 for (int yScale = 0; yScale < scale; yScale++) { 
                     if (yPixel + yScale < 0 || yPixel + yScale >= height) 
                         continue; 
                     for (int xScale = 0; xScale < scale; xScale++) { 
                         if (xPixel + xScale < 0 || xPixel + xScale >= width) 
                             continue; 
                     int col = 0;
//                     switch(sheet.pixels[xSheet + ySheet*sheet.width + tileOffset]){
//                        case 0: col = color1;   break;
//                        case 1: col = color2;   break;
//                        case 2: col = color3;   break;
//                        case 3: col = color4;   break;
//                    }
                                    if (sheet.pixels[xSheet + ySheet*sheet.width + tileOffset]!= -65281)
                       pixels[(xPixel + xScale) + (yPixel + yScale) * width] = sheet.pixels[xSheet + ySheet*sheet.width + tileOffset]; 
                     } 

                 } 
             } 
         } 
     } 
 
 
     public void setOffset(int xOffset, int yOffset) { 
         this.xOffset = xOffset; 
         this.yOffset = yOffset; 
     } 
} 
