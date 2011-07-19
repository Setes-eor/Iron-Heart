/*
 * the option menu in the game
 * 
 */
package iron.heart;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author setes
 */
public class Optionmenu extends Menu {

    // attributes of the class
    //
    GameCavs gc_gamecavs;
    Game ga_game;
    String s_mappath;

    // constructor
    //
    public Optionmenu(String ref, String data, String typ, int screenW, int screenH,
            GameCavs gamecavs, Game game, String mappath) {
        super(ref, data, typ, screenW, screenH);
        initButtons();
        gc_gamecavs = gamecavs;
        ga_game = game;
        s_mappath = mappath;
        b_visible = false;
    }// constructor

    @Override
    public void mouseClicked(int x, int y) {
        int buttonid = super.buttonClicked(x, y);

        // restart
        if (buttonid == 0) {
            try {
                gc_gamecavs.setStateActive("Start");
                gc_gamecavs.initGame(s_mappath);
            } catch (IOException ex) {
                Logger.getLogger(Optionmenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }// if
        // save
        if (buttonid == 1) {
            b_visible = false;
        }// if
        
        if(buttonid == 2){
            b_visible = false;
            gc_gamecavs.setStateActive("Start");
            gc_gamecavs.setGameNull();
            gc_gamecavs.cm_combatmenu.setVisible(false);
            gc_gamecavs.ng_newgame.setVisible(false);
            gc_gamecavs.sm_startmenu.setVisible(true);
            
        }// if
        // close game
        if (buttonid == 3) {
            System.exit(0);
        }// if
        // close
        if (buttonid == 4) {
            b_visible = false;
            ga_game.setStateActive("Game");
        }// if
    }// mouseClicked
    // init the Buttons of the StartMenu
    //

    @Override
    public final void initButtons() {
        int x = this.getXPos() + 19;
        int y1 = this.getYPos() + 140;
        String button = "buttons/button";
        String textp = "texts/";
        addButton(s_datapath + button + s_typ, s_datapath + textp + "Restart" + s_typ, x, y1, 0);
        int buttonheight = lb_buttons.get(0).sp_sprite.getHeight();
        addButton(s_datapath + button + s_typ, s_datapath + textp + "LoadGame" + s_typ, x, y1 + buttonheight + 10, 1);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "MainMenu" + s_typ, x, y1 + 2 * buttonheight + 20, 2);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "CloseGame" + s_typ, x, y1 + 3 * buttonheight + 30, 3);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "Close" + s_typ, x, y1 + 4 * buttonheight + 40, 4);
    }// initButtons
}// class Optionmenu
