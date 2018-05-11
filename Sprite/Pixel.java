 

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Pixel
{
    private int p = 0;
    BufferedImage img;
    File f;
    public Pixel(String file){
        try{
            f = new File(file);
            img = ImageIO.read(f);
        }
        catch(IOException e){
            System.out.println(f);
        }
        
    }
    public boolean getPixel(int x, int y){
        p = img.getRGB(x,y); //gets the color for the pixel
        //if(p != -1)
        //    System.out.println(p);
        if(p < -14510000) //-1 is the code for white, so anything less than that is no walking zone
            return true;
        else
            return false;

    }
    public boolean advance(int x, int y){
        p = img.getRGB(x,y);
        //System.out.println(p);
        if(p== -14503604) 
            return true;
        return false;
    }
    public boolean advancealso(int x, int y){
        p = img.getRGB(x,y);
        //System.out.println(p);
        if(p== -65536)
            return true;
        return false;
    }
    public int test(int x, int y){
        p = img.getRGB(x,y);
        return p;
    }

}
