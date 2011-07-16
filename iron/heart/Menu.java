/*
 * base for all menus
 * 
 */
package iron.heart;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setes
 */
public abstract class Menu extends Entity{
    
    // attributes of the class
    //
    protected int i_ButtonsID;
    protected int i_ButtonpNumID;
    protected List<Button> lb_buttons;
    protected List<ButtonpNum> bn_buttons;
    protected String s_typ;
    protected String s_datapath;
    protected boolean b_visible;
    
    // constructor
    //
    public Menu(String ref, String data, String typ, int screenWidth, int screenHigth){
        super(data + ref + typ, 0, 0);
        i_ButtonsID = 0;
        i_ButtonpNumID = 0;
        s_typ = typ;
        s_datapath = data;
        lb_buttons = new ArrayList<Button>();  
        bn_buttons = new ArrayList<ButtonpNum>();
               
        this.setXPos((screenWidth - this.sp_sprite.getWidth()) / 2);
        this.setYPos((screenHigth - this.sp_sprite.getWidth()) / 2);
        
        b_visible = true;
    }// constructor

    // set the buttons of the menu
    //
    protected void addButton(String ref, String tref, int x, int y){
        i_ButtonsID++;
        Button button = new Button(ref, tref, i_ButtonsID, x, y);
        
        lb_buttons.add(button);   
    }// setButton
    
    // set the buttons of the menu
    //
    protected void addButton(String ref, String tref, int x, int y, int id){
        Button button = new Button(ref, tref, id, x, y);
        
        lb_buttons.add(button);   
    }// setButton
    
    // set the buttons with num of the menu
    //
    protected void addButtonpNum(String ref, int x, int y, String data, String path,
            String typ, int resx, int resy){
        i_ButtonpNumID++;
        ButtonpNum button = new ButtonpNum(ref,x,y, i_ButtonpNumID, data, path, typ, resx, resy);
        
        bn_buttons.add(button);   
    }// setButton
    
    // set the buttons with num of the menu
    //
    protected void addButtonpNum(String ref, int x, int y, int id,String data, String path,
            String typ, int resx, int resy){
        ButtonpNum button = new ButtonpNum(ref,x,y, id, data, path, typ, resx, resy);
        
        bn_buttons.add(button);   
    }// setButton
    
    // return the buttonID by clicked on the button
    //
    public int buttonClicked(int x, int y){
        if(b_visible){
            for(int i = 0; i < lb_buttons.size(); i++){
            int x1 = (int) lb_buttons.get(i).d_xPos;
            int x2 = ( x1 + (int) lb_buttons.get(i).sp_sprite.getWidth());
            int y1 = (int) lb_buttons.get(i).d_yPos;
            int y2 = ( y1 + (int) lb_buttons.get(i).sp_sprite.getHeight());
            
            if(x >= x1 && x <= x2 && y >= y1 && y <= y2)
                return lb_buttons.get(i).getButtonID();
            }// for
        return 0;
        }// if   
        return 0;
    }// buttonClicked
    
    // draw the image of the entity on the screen
    //
    @Override
    public void Draw(Graphics g){
        if(b_visible){
             sp_sprite.Draw(g,(int) d_xPos,(int) d_yPos);   
        drawButtons(g);
        }// if
    }// Draw
    
    // draw the menu with the num buttons
    //
    public void DrawNumButtons(Graphics g, int wood, int brick, int fish, int milk,
            int slime){
        sp_sprite.Draw(g,(int) d_xPos,(int) d_yPos);   
        drawButtonspNum(g, wood, brick, fish, milk, slime);
    }// DrawNumButtons
    
    // draw the buttons
    //
    private void drawButtons(Graphics g){
        if(b_visible)
            for (int i = 0; i < lb_buttons.size(); i++) {
                lb_buttons.get(i).Draw(g);
            }// for 
    }// drawButtons
    
    // Die ganze Sache mti den buttonpnums kommt in eine extra klasse abgeleitet
    // von Menu!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
    // draw buttons with num
    //
    private void drawButtonspNum(Graphics g, int wood, int brick, int fish,
            int milk, int slime){
        bn_buttons.get(0).Draw(g, wood);
        bn_buttons.get(1).Draw(g, brick);
        bn_buttons.get(2).Draw(g, fish);
        bn_buttons.get(3).Draw(g, milk);
        bn_buttons.get(4).Draw(g, slime);
        
    }// drawButtonspNum
    
    // set and get visible
    //
    public void setVisible(boolean vis){b_visible = vis;}
    public boolean getVisible(){return b_visible;}
    
    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public abstract void initButtons();
}// class Menu
