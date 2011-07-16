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
    
    // constructor
    //
    public StartMenu(String ref, String data, String typ, int screenW, int screenH){
        super(ref, data, typ, screenW, screenH);
        initButtons();
    }// constructor
    
    // init the Buttons of the StartMenu
    //
    @Override
    public final void initButtons(){
        int x = this.getXPos() + 19;
        int y1 = this.getYPos() + 140;
        String button = "buttons/button";
        String textp = "texts/";
        addButton(s_datapath + button + s_typ, s_datapath + textp + "NewGame" + s_typ, x, y1, 1);
        int buttonheight = lb_buttons.get(0).sp_sprite.getHeight();
        addButton(s_datapath + button + s_typ , s_datapath + textp + "LoadGame" + s_typ,x, y1 + buttonheight + 10, 1);
        addButton(s_datapath + button + s_typ , s_datapath + textp + "Properties" + s_typ,x, y1 + 2 * buttonheight + 20, 1);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "Credits" + s_typ, x, y1 + 3 * buttonheight + 30, 1);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "CloseGame" + s_typ, x, y1 + 4 * buttonheight + 40, 1);        
    }// initButtons
}// class StartMenu
