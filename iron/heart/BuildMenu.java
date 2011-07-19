/*
 * the menu to choose a build
 * 
 */
package iron.heart;

/**
 *
 * @author setes
 */
public class BuildMenu extends Menu {

    // attributes of the class
    // 
    String i_spezID;
    
    // constructor
    //
    public BuildMenu(String ref, String data, String typ, int screenW, int screenH,
            String spezId) {
        super(ref, data, typ, screenW, screenH);
        initButtons();
        b_visible = false;
    }// constructor

    @Override
    public void mouseClicked(int x, int y) {
        if (b_visible) {
            int buttonid = super.buttonClicked(x, y);

            if (buttonid == 0) {
                b_visible = false;
            }// if

            // close the game
            //
            if (buttonid == 4) {
                b_visible = false;
            }// if
        }// if
    }// mouseClicked
    // init the Buttons of the StartMenu
    //

    @Override
    public final void initButtons() {
        int x = this.getXPos() + 3;
        int y1 = this.getYPos() + 5;
        String button = "buttons/buildbutton";
        String textp = "texts/";
        addButton(s_datapath + button + s_typ, s_datapath + textp + "" + s_typ, x, y1, 0);
        int buttonheight = lb_buttons.get(0).sp_sprite.getHeight();
        int buttonwidth = lb_buttons.get(0).sp_sprite.getWidth();
        addButton(s_datapath + button + s_typ, s_datapath + textp + "" + s_typ, x + buttonwidth + 5, y1 , 1);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "" + s_typ, x + 2 * buttonwidth + 10, y1, 2);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "" + s_typ, x, y1 + buttonheight + 10, 3);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "" + s_typ, x + buttonwidth + 5, y1 + buttonheight + 10, 4);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "" + s_typ, x + 2 * buttonwidth + 10, y1 + buttonheight + 10, 5);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "" + s_typ, x, y1 + 2 * buttonheight + 15, 6);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "" + s_typ, x + buttonwidth + 5, y1 + 2 * buttonheight + 15, 7);
        addButton(s_datapath + button + s_typ, s_datapath + textp + "" + s_typ, x + 2 * buttonwidth + 10, y1 + 2 * buttonheight + 15, 8);
    }// initButtons
}// BuildMenu
