/*
 * the menu to choose a build
 * 
 */
package iron.heart;

import iron.heart.Player;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author setes
 */
public class BuildMenu extends Menu {

    // attributes of the class
    // 
    String s_mainpath;
    String i_spezID;
    Player pl_player;
    HashMap<String, String> hm_buildart;
    String s_buildingid;
    List<BasicEntity> lbe_buttonpicsBuildings;
    List<BasicEntity> lbe_buttonpicsMainUnits;
    
    // constructor
    //
    public BuildMenu(String ref, String data, String typ, int x, int y,
            String spezID, Player player) {
        super(ref, data + "menus/", typ, x, y,1);
        initButtons();
        s_mainpath = data;
        b_visible = false;
        i_spezID = spezID;
        pl_player = player;
        hm_buildart = new HashMap<String, String>();
        initBuildArts();
        s_buildingid = "none";
    }// constructor

    // init the buildarts
    //
    public void initBuildArts(){
        hm_buildart.put("main", "inactive");
        hm_buildart.put("builds", "inactive");
        hm_buildart.put("units", "inactive");
        hm_buildart.put("research", "inactive");
    }// initBuildArts
    
    // set a buildart to active
    //
    public void setBuildArtActive(String key){
        initBuildArts();
        hm_buildart.put(key, "active");
    }// setBuildArtActive
    
    // changethe visible
    //
    public void changeVisible(){
        if(b_visible){
            b_visible = false;
            pl_player.b_buildactive = false;
        }// if
        else
            b_visible = true;
    }// changeVisible
    
    @Override
    public void mouseClicked(int x, int y) {
        if (b_visible) {
            int buttonid = super.buttonClicked(x, y);
            String buildingspath = "gfx/buildings/";

            if (buttonid == 0) {
                if(hm_buildart.get("builds") == "active"){
                    pl_player.initCurser(s_mainpath + buildingspath + "a/" + "main" + s_typ
                            , x, y);
                    pl_player.b_buildactive = true;
                    s_buildingid = "main";
                }// if
                if(hm_buildart.get("main") == "active"){
                   pl_player.addUnit("sonde", pl_player.ah_headquater.getPersID());
                }// if
            }// if

            // close the game
            //
            if (buttonid == 4) {
                b_visible = false;
            }// if
        }// if
    }// mouseClicked
    
    // get the buildid
    //
    public String getBuildID(){return s_buildingid;}
    
    // add a button pic
    //
    public void addButtonpicsBuilds(String ref, int x, int y){
        BasicEntity buttonpic = new BasicEntity(ref,x,y);
        lbe_buttonpicsBuildings.add(buttonpic);
    }// add Buttonpic 
    
    // add a button pic
    //
    public void addButtonpicsUnits(String ref, int x, int y){
        BasicEntity buttonpic = new BasicEntity(ref,x,y);
        lbe_buttonpicsMainUnits.add(buttonpic);
    }// add Buttonpic 
    
    // init the Buttons of the StartMenu
    //
    @Override
    public final void initButtons() {
        int x = this.getXPos() + 8;
        int y1 = this.getYPos() + 8;
        String button = "buttons/buildbutton";
        String buttonpis = "buttons/buttonpic/";
        String textp = "texts/";
        addButton(s_datapath + button + s_typ, "", x, y1, 0);
        int buttonheight = lb_buttons.get(0).sp_sprite.getHeight();
        int buttonwidth = lb_buttons.get(0).sp_sprite.getWidth();
        addButton(s_datapath + button + s_typ, "" , x + buttonwidth + 5, y1 , 1);
        addButton(s_datapath + button + s_typ, "", x + 2 * buttonwidth + 10, y1, 2);
        addButton(s_datapath + button + s_typ, "", x, y1 + buttonheight + 5, 3);
        addButton(s_datapath + button + s_typ, "", x + buttonwidth + 5, y1 + buttonheight + 5, 4);
        addButton(s_datapath + button + s_typ, "", x + 2 * buttonwidth + 10, y1 + buttonheight + 5, 5);
        addButton(s_datapath + button + s_typ, "", x, y1 + 2 * buttonheight + 10, 6);
        addButton(s_datapath + button + s_typ, "", x + buttonwidth + 5, y1 + 2 * buttonheight + 10, 7);
        addButton(s_datapath + button + s_typ, "", x + 2 * buttonwidth + 10, y1 + 2 * buttonheight + 10, 8);
        
        lbe_buttonpicsBuildings = new ArrayList<BasicEntity>();
        lbe_buttonpicsMainUnits = new ArrayList<BasicEntity>();
        
        addButtonpicsBuilds(s_datapath + buttonpis + "main" + s_typ, x + 10, y1 + 10);
        addButtonpicsUnits(s_datapath + buttonpis + "sonde" + s_typ, x + 10, y1 + 10);
    }// initButtons
    
    // draw
    //
    @Override
    public void Draw(Graphics g){
        if(b_visible){
             sp_sprite.Draw(g,(int) d_xPos,(int) d_yPos);   
        drawButtons(g);
            if (hm_buildart.get("builds") == "active") {
                for (int i = 0; i < lbe_buttonpicsBuildings.size(); i++) {
                    lbe_buttonpicsBuildings.get(i).Draw(g);
                }
            }// if
            if (hm_buildart.get("main") == "active") {
                for (int i = 0; i < lbe_buttonpicsMainUnits.size(); i++) {
                    lbe_buttonpicsMainUnits.get(i).Draw(g);
                }
            }// if
        }// if
    }// Draw
}// BuildMenu
