/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iron.heart;

import iron.heart.GameCavs;
import iron.heart.Menu;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author setes
 */
public class NewGameMenu extends Menu{
    
    // attributes of the class
    //
    GameCavs gc_gamecavs;
    
    // constructor
    //
    public NewGameMenu(String ref, String data, String typ, int screenW, int screenH,
            GameCavs gamecavs){
        super(ref, data, typ, screenW, screenH);
        initButtons();
        gc_gamecavs = gamecavs;
        b_visible = false;
    }// constructor
    
    
    @Override
    public void mouseClicked(int x, int y){
        if(b_visible){
            int buttonid = super.buttonClicked(x, y);

            if(buttonid == 0){
                b_visible = false;
                gc_gamecavs.cm_combatmenu.setVisible(true);
            }// if
            
            // close the game
            //
            if (buttonid == 4) {
                b_visible = false;
                gc_gamecavs.setmousewait(true);
                gc_gamecavs.sm_startmenu.setVisible(true);
            }// if
        }// if
    }// mouseClicked
    // init the Buttons of the StartMenu
    //
    @Override
    public final void initButtons(){
        int x = this.getXPos() + 19;
        int y1 = this.getYPos() + 140;
        String button = "buttons/button";
        String textp = "texts/";
        addButton(s_datapath + button + s_typ, s_datapath + textp + "AICombat" + s_typ, x, y1, 0);
        int buttonheight = lb_buttons.get(0).sp_sprite.getHeight();
        addButton(s_datapath + button + s_typ , s_datapath + textp + "Tutorial" + s_typ,x, y1 + buttonheight + 10, 1);  
        addButton(s_datapath + button + s_typ, s_datapath + textp + "Back" + s_typ, x, y1 + 4 * buttonheight + 40, 4); 
    }// initButtons
}// class StartMenu
