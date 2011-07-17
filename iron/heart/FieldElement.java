/*
 * a fieldelement of the game field
 * 
 */
package iron.heart;

import java.util.HashMap;

/**
 *
 * @author setes
 */
public class FieldElement {
    
    //attribute of the class
    //
    HashMap<String,String> hm_fieldinfo;
    
    // default-constructor
    //
    public FieldElement(){
        
        initFieldInfo("grass","free","none");
    }// FieldElment
    
    // constructor
    //
    public FieldElement(String terrain, String build){
        String blocked = "blocked";
        
        if(terrain == "water" || terrain == "rock" || build != "none")
            blocked = "blocked";
        else
            blocked = "free";
        
        initFieldInfo(terrain,blocked, build);
    }// constructor
    
    // init the fieldinfos
    //
    private void initFieldInfo(String terrain, String blocked, String build){
        hm_fieldinfo = new HashMap<String,String>();
        
        hm_fieldinfo.put("terrain", terrain);
        hm_fieldinfo.put("blocked", blocked);
        hm_fieldinfo.put("build", build);
    }// initFieldInfo
    
    // block a field
    //
    public void blockField(){
        hm_fieldinfo.put("blocked", "blocked");
    }// blockField
    
    // show is the field free or blocked
    //
    public boolean isFieldFree(){
        if(hm_fieldinfo.get("blocked") == "free")
            return true;
        else
            return false;
    }// isFieldFree
    
    // get the terraintyp of the field
    //
    public String getTerrainTyp(){
        return hm_fieldinfo.get("terrain");
    }// getTerrainTyp
    
    // set a build on the field
    //
    public void setBuild(String build, boolean destroy){
        if(!destroy){
            hm_fieldinfo.put("blocked", "blocked");
            hm_fieldinfo.put("build", build);
        }// if
        else{
            hm_fieldinfo.put("blocked", "free");
            hm_fieldinfo.put("build", "none");
        }// else      
    }// setBuild
    
    // get the build of the field
    //
    public String getBuild(){
        return hm_fieldinfo.get("build");
    }// getBuild
}// class FieldElement
