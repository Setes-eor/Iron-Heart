/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iron.heart;

/**
 *
 * @author setes
 */
public class SaveMenu extends Menu {

    // attributes of the class
    //
    Game ga_game;

    // constructor
    //
    public SaveMenu(String ref, String data, String typ, int screenW, int screenH,
            Game game) {
        super(ref, data, typ, screenW, screenH);
        initButtons();
        ga_game = game;

        b_visible = false;
    }// constructor

    @Override
    public void mouseClicked(int x, int y) {
        if (b_visible) {
            int buttonid = super.buttonClicked(x, y);


            // close the game
            //
            if (buttonid == 4) {
                b_visible = false;
                ga_game.setStateActive("Game");
            }// if
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
        addButton(s_datapath + button + s_typ, s_datapath + textp + "State1" + s_typ, x, y1, 0);
        int buttonheight = lb_buttons.get(0).sp_sprite.getHeight();
        addButton(s_datapath + button + s_typ, s_datapath + textp + "State2" + s_typ, x, y1 + buttonheight + 10, 1);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "State3" + s_typ, x, y1 + 2 * buttonheight + 20, 2);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "State4" + s_typ, x, y1 + 3 * buttonheight + 30, 3);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "Back" + s_typ, x, y1 + 4 * buttonheight + 40, 4);
    }// initButtons
}// class SaveMenu
