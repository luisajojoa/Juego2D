package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class SpriteSheet {
    
    public String path;  //path of image
    public int width;    // 32 tiles(8 bit)
    public int height;   // 32 tiles(8 bit)
    
    public int[] pixels; //pixel_data of SpriteSheet

    public SpriteSheet(String path) {
        
        this.path = path;
        BufferedImage image = null;
        
        try {image = ImageIO.read(SpriteSheet.class.getResource(path));
        } catch (IOException ex) {}
        
        this.width = image.getWidth();
        //System.out.println(this.width);
        this.height = image.getHeight();
       
        pixels = image.getRGB(0, 0, width, height, null, 0, width); 
        //System.out.println(Arrays.toString(pixels));
        //System.out.println(pixels.length);                                    //= 256*256 pixeles || 32*32 tiles || 65536 pixeles

//        for(int i = 0; i < pixels.length; i++)
//            pixels[i] = ((pixels[i] & 0xff)/64);                             //negro = 0; gris = 1, gris = 2, blanco = 3  
        //System.out.println(Arrays.toString(pixels));
        
    }
}

//        intentar un if(image == null) return; por si el try catch no funciona