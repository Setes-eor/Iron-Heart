package iron.heart;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/*
 * the base for all visual elements
 * 
 */

/**
 *
 * @author setes
 */
public class Sprite {
    
    BufferedImage im_image;
    BufferedImage im_subimage;
    
    // constructor
    //
    public Sprite(BufferedImage image){
        this.im_image = image;
    }// constructor
    
    // getter
    public int getWidth(){return im_image.getWidth(null);}
    public int getHeight(){return im_image.getHeight(null);}
    
    // show a subimage
    //
    public void setSubImage(int x, int y, int width, int height){
        //System.out.println(x + " " + width);
        BufferedImage subimage;
        im_subimage = im_image.getSubimage(x, y, width, height);
    }// setSubImage
    
    // draw the image of the spirte
    //
    public void Draw(Graphics g, int x, int y){
        g.drawImage(im_image, x, y, null);
    }// Draw
    
    // draw the subimage
    //
    public void DrawSubImage(Graphics g, int x, int y){
        g.drawImage(im_subimage, x, y, null);
    }// DrawSubImage
}// class Sprite
