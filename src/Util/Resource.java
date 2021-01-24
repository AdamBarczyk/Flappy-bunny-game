package Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resource {
    public static BufferedImage getResourceImage(String path){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        }catch(IOException ex2){
            ex2.printStackTrace();
        }
        return img;
    }
}