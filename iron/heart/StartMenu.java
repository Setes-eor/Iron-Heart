/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iron.heart;

/**
 *
 * @author setes
 */
public class StartMenu extends Menu{
    
    // attributes of the class
    //
    GameCavs gc_gamecavs;
    
    // constructor
    //
    public StartMenu(String ref, String data, String typ, int screenW, int screenH,
            GameCavs gamecavs){
        super(ref, data, typ, screenW, screenH);
        initButtons();
        gc_gamecavs = gamecavs;
    }// constructor
    
    
    @Override
    public void mouseClicked(int x, int y){
        int buttonid = super.buttonClicked(x, y);
        
        // new game
        if(buttonid == 0){
                b_visible = false;
                gc_gamecavs.ng_newgame.setVisible(true);
            }// if
        // loadmenu
        if(buttonid == 1){
                b_visible = false;
                gc_gamecavs.lm_loadmenu.setVisible(true);
            }// if
        // properties
        if(buttonid == 2){
                b_visible = false;
                gc_gamecavs.pr_properties.setVisible(true);
            }// if
        // credits
        if(buttonid == 3){
                b_visible = false;
                gc_gamecavs.cr_credits.setVisible(true);
            }// if
        // close the game
        if(buttonid == 4){
          System.exit(0);              
        }
    }// mouseClicked
    // init the Buttons of the StartMenu
    //
    @Override
    public final void initButtons(){
        int x = this.getXPos() + 19;
        int y1 = this.getYPos() + 140;
        String button = "buttons/button";
        String textp = "texts/";
        addButton(s_datapath + button + s_typ, s_datapath + textp + "NewGame" + s_typ, x, y1, 0);
        int buttonheight = lb_buttons.get(0).sp_sprite.getHeight();
        addButton(s_datapath + button + s_typ , s_datapath + textp + "LoadGame" + s_typ,x, y1 + buttonheight + 10, 1);
        addButton(s_datapath + button + s_typ , s_datapath + textp + "Properties" + s_typ,x, y1 + 2 * buttonheight + 20, 2);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "Credits" + s_typ, x, y1 + 3 * buttonheight + 30, 3);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "CloseGame" + s_typ, x, y1 + 4 * buttonheight + 40, 4);        
    }// initButtons
}// class StartMenu
