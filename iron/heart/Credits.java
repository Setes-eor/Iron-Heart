/*
 * The credits of Iron Heart
 * 
 */
package iron.heart;

import java.awt.Graphics;

/**
 *
 * @author setes
 */
public class Credits extends Menu{
    
    // attributes of the class
    //
    GameCavs gc_gamecavs;
    BasicEntity dankepaps;
    int i_screenW;
    int i_screenH;
    
    // constructor
    //
    public Credits(String ref, String data, String typ, int screenW, int screenH,
            GameCavs gamecavs){
        super(ref, data, typ, screenW, screenH);
        initButtons();
        gc_gamecavs = gamecavs;
        
        i_screenW = screenW;
        i_screenH = screenH;
        b_visible = false;
    }// constructor
    
    
    
    public void mouseClicked(int x, int y, int mouse){
        if(b_visible){
            int buttonid = super.buttonClicked(x, y);
            // paps
            if(buttonid == 1 && mouse == 1){
                dankepaps = new BasicEntity("data/menus/buttons/.vermissedich/wacken07mitpaps.png",
                        0,0);
                dankepaps.setXPos((i_screenW - dankepaps.sp_sprite.getWidth()) / 2);
                dankepaps.setYPos((i_screenH - dankepaps.sp_sprite.getHeight()) / 2);
            }// if
            // close the game
            //
            if (buttonid == 4) {
                dankepaps = null;
                b_visible = false;
                gc_gamecavs.setmousewait(true);
                gc_gamecavs.sm_startmenu.setVisible(true);
            }// if
        }// if
    }// mouseClicked
    
    // draw the image of the entity on the screen
    //
    @Override
    public void Draw(Graphics g){
        if(b_visible){
             sp_sprite.Draw(g,(int) d_xPos,(int) d_yPos);   
             super.drawButtons(g);
             if(dankepaps != null)
                 dankepaps.Draw(g);
        }// if
    }// Draw
    
    // init the Buttons of the StartMenu
    //
    @Override
    public final void initButtons(){
        int x = this.getXPos() + 19;
        int y1 = this.getYPos() + 140;
        String button = "buttons/button";
        String textpath = "texts/credits";
        String textp = "texts/";
        addButton(s_datapath + button + s_typ, s_datapath + textp + "State1" + s_typ, x, y1, 0);
        int buttonheight = lb_buttons.get(0).sp_sprite.getHeight();
        deleteButton(0);
        addButton(s_datapath + textpath + s_typ, s_datapath + textp + "notext" + s_typ, x, y1, 1);
  
        addButton(s_datapath + button + s_typ, s_datapath + textp + "Back" + s_typ, x, y1 + 4 * buttonheight + 40, 4);         
    }// initButtons

    @Override
    public void mouseClicked(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}// class LoadMenu
