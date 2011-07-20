/*
 * base class for player and ais
 * 
 */
package iron.heart;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author setes
 */
public class PlayerBasic {
    
    // attriubtes of the class
    //
    protected HashMap<String, String> hm_abuildings;
    protected HashMap<String, String> hm_aunits;
    protected String s_playerID;
    protected String s_speziesID;
    protected Resources r_resources;
    protected List<Buildings> lb_buildings;
    protected List<Units> lu_units;
    protected String s_mainpath;
    protected String s_typ;
    protected int i_screenWidth;
    protected int i_screenHeigth;
    protected Field fi_field;
    
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
            int screenWidth, int screenHeight, Field field){
        s_playerID = ID;
        s_speziesID = spezID;
        r_resources = new Resources(s_playerID, res1, res2);
        s_mainpath = mainpath + "gfx/";
        s_typ = typ;
        i_screenWidth = screenWidth;
        i_screenHeigth = screenHeight;
        
        if("A".equals(spezID)){
            hm_abuildings = new HashMap<String, String>();
            hm_aunits = new HashMap<String, String>();
            initABuildings();
            initAUnits();
            lb_buildings = new ArrayList<Buildings>();
            lu_units = new ArrayList<Units>();
        }// if
        fi_field = field;
    }// constructor
    
    // draw all the things of the player buldings, units, resources-bar
    //
    public void Draw(Graphics g){
        for(int i = 0; i < lb_buildings.size(); i++){
            lb_buildings.get(i).Draw(g);
        }// for
        for(int i = 0; i < lu_units.size(); i++){
            lu_units.get(i).Draw(g);
        }// for
    }// Draw
    
    // set the horizontal move of all builds and units
    //
    public void setHorizontalMove(double dx){
        for(int i = 0; i < lb_buildings.size(); i++){
            lb_buildings.get(i).setHorizontalMove(dx);
        }// for
        for(int i = 0; i < lu_units.size(); i++){
            lu_units.get(i).setHorizontalMove(dx);
        }// for
    }// setHorizontalMove
    
    public void setVerticalMove(double dy){
        for(int i = 0; i < lb_buildings.size(); i++){
            lb_buildings.get(i).setVerticalMove(dy);
        }// for
        for(int i = 0; i < lu_units.size(); i++){
            lu_units.get(i).setVerticalMove(dy);
        }// for
    }// setVerticalMove
    
    // move all units and buldings
    //
    public void Move(long delta){
        for(int i = 0; i < lb_buildings.size(); i++){
            lb_buildings.get(i).move(delta);
        }// for
        for(int i = 0; i < lu_units.size(); i++){
            lu_units.get(i).move(delta);
        }// for
    }// Move
    
    // init the hashmap with infos to the buildings of a
    //
    public void initABuildings(){
       //(name of the building, costs + raster + energy
       hm_abuildings.put("main", "ore=300,gas=0;" + "3*3;" + "100;"); 
       hm_abuildings.put("barrack", "ore=100,gas=0;" + "2*2;" + "100;"); 
       hm_abuildings.put("fabrik", "ore=150,gas=20;" + "2*2;" + "100;"); 
       hm_abuildings.put("harbor", "ore=200,gas=100;" + "2*3;" + "100;"); 
       hm_abuildings.put("gaspump", "ore=100,gas=0;" + "2*1;" + "100;"); 
       hm_abuildings.put("datanode", "ore=50,gas=0;" + "1*1;" + "100;"); 
       hm_abuildings.put("researchcenter", "ore=250,gas=100;" + "2*2;" + "100;");  
    }// initABuildings
    
    // init the hasmap with infos to the units of a
    //
    public void initAUnits(){
        //(name of the unit, picturename + costs + energy
        hm_aunits.put("sonde", "sonde;" + "ore=50,gas=0;"); 
        hm_aunits.put("soldier", "soldier;" + "ore=100,gas=0;"); 
    }// initAUnits
}// PlayerBasic
