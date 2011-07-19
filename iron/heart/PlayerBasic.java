/*
 * base class for player and ais
 * 
 */
package iron.heart;

import java.awt.Graphics;

/**
 *
 * @author setes
 */
public class PlayerBasic {
    
    // attriubtes of the class
    //
    String s_playerID;
    String s_speziesID;
    Resources r_resources;
    String s_mainpath;
    String s_typ;
    int i_screenWidth;
    int i_screenHeigth;
    
    // default-constructor
    //
    public PlayerBasic(){
        s_playerID = "P0";
        s_speziesID = "A";
        r_resources = new Resources();
        s_mainpath = "data/gfx/";
        s_typ = ".png";
    }// PlayerBasic
    
    // constructor
    //
    public PlayerBasic(String ID, String spezID, String mainpath, String typ, int res1, int res2,
            int screenWidth, int screenHeight){
        s_playerID = ID;
        s_speziesID = spezID;
        r_resources = new Resources(s_playerID, res1, res2);
        s_mainpath = mainpath + "gfx/";
        s_typ = typ;
        i_screenWidth = screenWidth;
        i_screenHeigth = screenHeight;
    }// constructor
    
    // draw all the things of the player buldings, units, resources-bar
    //
    public void Draw(Graphics g){
        
    }// Draw
}// PlayerBasic
