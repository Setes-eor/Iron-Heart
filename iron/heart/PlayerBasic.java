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
    public List<BuildingsAHeadquarters> lah_headquaters;
    public BuildingsAHeadquarters ah_headquater;
    protected List<Units> lu_units;
    protected List<UnitASonde> as_sondes;
    protected String s_mainpath;
    protected String s_typ;
    protected int i_screenWidth;
    protected int i_screenHeigth;
    protected Field fi_field;
    int i_buldingPersID;
    int i_unitPersID;
    
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
            lah_headquaters = new ArrayList<BuildingsAHeadquarters>();
            lu_units = new ArrayList<Units>();
            as_sondes = new ArrayList<UnitASonde>();
        }// if
        fi_field = field;
        i_buldingPersID = 0;
        i_unitPersID = 0;
    }// constructor
    
    // add a bulding
    //
    public void addBuild(String buildinfo, String ref, String buildid, int setXPos, int setYPos){
        String[] buildinfos = buildinfo.split(";");
        String[] resources = buildinfos[0].split(",");
        String[] sres1 = resources[0].split("=");
        String[] sres2 = resources[1].split("=");
        int res1 = Integer.parseInt(sres1[1]);
        int res2 = Integer.parseInt(sres2[1]);
        int pop = 0;
        String raster = buildinfos[1];
        int energy = Integer.parseInt(buildinfos[2]);

        int xPos = setXPos + ((-1) * fi_field.getXFirstField());
        int yPos = setYPos + ((-1) * fi_field.getYFirstField());
        if (r_resources.enaughResources(res1, res2, pop)
                && fi_field.isfieldFree(xPos, yPos, buildid, raster)) {
            r_resources.payCosts(res1, res2, pop);
            fi_field.addBuild(xPos, yPos, buildid, raster);
            if(buildid.equals("main")){
                BuildingsAHeadquarters build = new BuildingsAHeadquarters(ref, s_mainpath,
                        s_typ,setXPos, setYPos, xPos, yPos,s_playerID, i_buldingPersID, energy);
                lah_headquaters.add(build);
            }// if
            else{
                Buildings build = new Buildings(ref, setXPos,
                    setYPos, xPos, yPos,s_playerID, i_buldingPersID, energy);
                lb_buildings.add(build);  
            }// else
            i_buldingPersID++;
        }// if
    }// addBuild
    
    // add a Unit
    //
    public void addUnit(String unitid, int buildid){
        String unitinfo = hm_aunits.get(unitid);
        String[] buildinfos = unitinfo.split(";");
        String[] resources = buildinfos[0].split(",");
        String[] sres1 = resources[0].split("=");
        String[] sres2 = resources[1].split("=");
        int res1 = Integer.parseInt(sres1[1]);
        int res2 = Integer.parseInt(sres2[1]);
        int pop = 0;
        int energy = Integer.parseInt(buildinfos[1]);

        if (r_resources.enaughResources(res1, res2, pop)) {
            r_resources.payCosts(res1, res2, pop);
            if(unitid.equals("sonde")){
                 for(int i = 0; i < lah_headquaters.size(); i++){
                        if(lah_headquaters.get(i).getPersID() == buildid){
                            lah_headquaters.get(i).addSondetoBuild(i_unitPersID, energy);
                        }// if
                    }// for
            }// if
            i_unitPersID++;
        }// if
    }// addUnit
    
    // search for a building or a unit by mouseclick about the positions
    //
    public String search(int xPos, int yPos){
        // search for a build
        for(int i = 0; i < lb_buildings.size(); i++){
            if(xPos >= lb_buildings.get(i).getStartX()
                    && xPos <= lb_buildings.get(i).sp_sprite.getWidth() + lb_buildings.get(i).getStartX()
                    && yPos >= lb_buildings.get(i).getStartY()
                    && yPos <= lb_buildings.get(i).sp_sprite.getHeight() + lb_buildings.get(i).getStartY()){
                return "";
            }// if
        }// for
        for(int i = 0; i < lah_headquaters.size(); i++){
            if(xPos >= lah_headquaters.get(i).getStartX()
                    && xPos <= lah_headquaters.get(i).sp_sprite.getWidth() + lah_headquaters.get(i).getStartX()
                    && yPos >= lah_headquaters.get(i).getStartY()
                    && yPos <= lah_headquaters.get(i).sp_sprite.getHeight() + lah_headquaters.get(i).getStartY()){
                ah_headquater = lah_headquaters.get(i);
                
                return "main";
            }// if
        }// for
        // search for a unit
        for(int i = 0; i < lu_units.size(); i++){
            if(xPos >= lu_units.get(i).getStartX()
                    && xPos <= lu_units.get(i).sp_sprite.getWidth() + lu_units.get(i).getStartX()
                    && yPos >= lu_units.get(i).getStartY()
                    && yPos <= lu_units.get(i).sp_sprite.getHeight() + lu_units.get(i).getStartY()){
                return "";
            }// if
        }// for
        for(int i = 0; i < as_sondes.size(); i++){
            if(xPos >= as_sondes.get(i).getStartX()
                    && xPos <= as_sondes.get(i).sp_sprite.getWidth() + as_sondes.get(i).getStartX()
                    && yPos >= as_sondes.get(i).getStartY()
                    && yPos <= as_sondes.get(i).sp_sprite.getHeight() + as_sondes.get(i).getStartY()){
                return "sonde";
            }// if
        }// for
        return "";
    }// search
    
    
    
    // draw all the things of the player buldings, units, resources-bar
    //
    public void Draw(Graphics g){
        for(int i = 0; i < lb_buildings.size(); i++){
            lb_buildings.get(i).Draw(g);
        }// for
        for(int i = 0; i < lah_headquaters.size(); i++){
            lah_headquaters.get(i).Draw(g);
            if(lah_headquaters.get(i).isTimeToBorn()){
                as_sondes.add(lah_headquaters.get(i).bornSonde());
                            ah_headquater = null;
            }// if
        }// for
        for(int i = 0; i < lu_units.size(); i++){
            lu_units.get(i).Draw(g);
        }// for
        for(int i = 0; i < as_sondes.size(); i++){
            as_sondes.get(i).Draw(g);
        }// for
        
        // nur zum test hier
        //
        
    }// Draw
    
    // set the horizontal move of all builds and units
    //
    public void setHorizontalMove(double dx){
        for(int i = 0; i < lb_buildings.size(); i++){
            lb_buildings.get(i).setHorizontalMove(dx);
        }// for
        for(int i = 0; i < lah_headquaters.size(); i++){
            lah_headquaters.get(i).setHorizontalMove(dx);
        }// for
        for(int i = 0; i < lu_units.size(); i++){
            lu_units.get(i).setHorizontalMove(dx);
        }// for
        for(int i = 0; i < as_sondes.size(); i++){
            as_sondes.get(i).setHorizontalMove(dx);
        }// for
    }// setHorizontalMove
    
    public void setVerticalMove(double dy){
        for(int i = 0; i < lb_buildings.size(); i++){
            lb_buildings.get(i).setVerticalMove(dy);
        }// for
         for(int i = 0; i < lah_headquaters.size(); i++){
            lah_headquaters.get(i).setVerticalMove(dy);
        }// for
        for(int i = 0; i < lu_units.size(); i++){
            lu_units.get(i).setVerticalMove(dy);
        }// for
        for(int i = 0; i < as_sondes.size(); i++){
            as_sondes.get(i).setVerticalMove(dy);
        }// for
    }// setVerticalMove
    
    // move all units and buldings
    //
    public void Move(long delta){
        for(int i = 0; i < lb_buildings.size(); i++){
            lb_buildings.get(i).move(delta);
        }// for
         for(int i = 0; i < lah_headquaters.size(); i++){
            lah_headquaters.get(i).move(delta);
        }// for
        for(int i = 0; i < lu_units.size(); i++){
            lu_units.get(i).move(delta);
        }// for
        for(int i = 0; i < as_sondes.size(); i++){
            as_sondes.get(i).move(delta);
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
        hm_aunits.put("sonde", "ore=50,gas=0;" + "100"); 
        hm_aunits.put("soldier", "ore=100,gas=0;" + "100"); 
    }// initAUnits
}// PlayerBasic
