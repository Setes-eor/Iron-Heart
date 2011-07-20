/*
 * image of a button
 * 
 */
package iron.heart;

import iron.heart.BasicEntity;
import iron.heart.Entity;
import java.awt.Graphics;

/**
 *
 * @author setes
 */
public class Button extends Entity{
    
    // attributes of the class
    //
    BasicEntity texts;
    int i_ButtonID;
    // constructor
    //
    public Button(String ref, String textref, int id, int x, int y){
        super(ref,x,y);
        i_ButtonID = id;
        
        if(textref != ""){
            texts = new BasicEntity(textref, 0, 0);
            int xt = x + ((this.sp_sprite.getWidth() - texts.sp_sprite.getWidth()) / 2);
            int yt = y + ((this.sp_sprite.getHeight() - texts.sp_sprite.getHeight()) / 2);

            texts.setXPos(xt);
            texts.setYPos(yt);
        }// if
        
    }// // constructor

    // returns the ButtonID
    //
    public int getButtonID(){return i_ButtonID;}
    
    // draw the image of the entity on the screen
    @Override
    public void Draw(Graphics g){
        sp_sprite.Draw(g,(int) d_xPos,(int) d_yPos); 
        if(texts != null)
        texts.Draw(g);
    }// Draw
    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}// class Button
