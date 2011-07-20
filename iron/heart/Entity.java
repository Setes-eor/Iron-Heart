package iron.heart;


import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * base class for all entitys for ex. menus, buttons, units, ...
 * 
 */

/**
 *
 * @author setes
 */
public abstract class Entity {
    
    // attributes of the class
    //
    protected double d_xPos;      // y position
    protected double d_yPos;      // x position
    protected double d_Dx;
    protected double d_Dy;
    protected boolean b_visible;
    protected String s_ref;
    protected int i_persID;  // personal id of a entity
    
    protected Sprite sp_sprite;
        
    private Rectangle mc = new Rectangle();
    private Rectangle him = new Rectangle();
    
    // constructor
    //
    public Entity(String ref, int x, int y){
        this.sp_sprite = ImageLoader.get().getSprite(ref);
        d_xPos = x;
        d_yPos = y;
        b_visible = true;
        s_ref = ref;
    }// constructor
       
    // change the image of a Entity
    //
    public void changeSprite(String ref){
        this.sp_sprite = ImageLoader.get().getSprite(ref);
    }// changeSprite
    
    // setter and getter
    //
    public void setXPos(double x){d_xPos = x;}
    public void setYPos(double y){d_yPos = y;}
    public int getXPos(){return (int) d_xPos;}
    public int getYPos(){return (int) d_yPos;}
    public String getRef(){return s_ref;}
    
    // set and get visible
    //
    public void setVisible(boolean vis){b_visible = vis;}
    public boolean getVisible(){return b_visible;}
    
    public void move(long delta){
        d_xPos += (delta * d_Dx) / 1000;
        d_yPos += (delta * d_Dy) / 1000;
    }
    
    public void setHorizontalMove(double dx){
        this.d_Dx = dx;
    }
    
    public void setVerticalMove(double dy){
        this.d_Dy = dy;
    }
    
    // draw the image of the entity on the screen
    public void Draw(Graphics g){
        // muss noch angepasst werden, dass es auf alle Auflösungen passt!!!!!!!!!!!!!!!!1
        if(d_xPos >= -64 && d_yPos >= -64 && d_xPos <= 1920 + 64 && d_yPos <= 1080 + 64 && b_visible)
            sp_sprite.Draw(g,(int) d_xPos,(int) d_yPos);     
    }// Draw
       
    // collision with two entities
    //
    public boolean collisonWidth(Entity e){
        mc.setBounds((int) d_xPos, (int) d_yPos, sp_sprite.getWidth(), sp_sprite.getHeight());
        him.setBounds((int) e.d_xPos, (int) e.d_yPos, e.sp_sprite.getWidth(), e.sp_sprite.getHeight());
        
        return mc.intersects(him);
    }// collisionWidth    
    public abstract void collionWidth(Entity e);   
}// class Entity
