package iron.heart;


import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

/*
 * the container for the spirte images
 * 
 */

/**
 *
 * @author setes
 */
public class ImageLoader {
    
    public static ImageLoader single = new ImageLoader();
    
    public static ImageLoader get(){
        return single;
    }
    
    private HashMap<String,Sprite> Images = new HashMap<String,Sprite>();
    
    // set the sprite and return them
    //
    public Sprite getSprite(String ref){
        
        
        if(Images.get(ref) != null){
            return (Sprite) Images.get(ref); 
        }// if
        
        URL url = getClass().getClassLoader().getSystemResource(ref);        
        
        
        BufferedImage sourceImage = null;
        
        try{
            sourceImage = ImageIO.read(url);
        }
        catch(IOException e){
            System.out.print("Unable to load: " + ref);
        }// try
        
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        
        BufferedImage image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.BITMASK);
        
        image.getGraphics().drawImage(sourceImage, 0, 0, null);
        
        Sprite sprite = new Sprite(image);
        
        Images.put(ref, sprite);
        
        return sprite;
    }// getSprite
}// class ImageLoader
