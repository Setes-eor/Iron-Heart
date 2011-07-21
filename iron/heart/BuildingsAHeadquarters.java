/*
 * the headquaters of the spezies A
 * 
 */
package iron.heart;

/**
 *
 * @author setes
 */
public class BuildingsAHeadquarters extends BuildingProductions{
    
    // attributes of the class
    //
    UnitASonde[] uas_sondes;
    int i_sondesValue = 5;
    int i_sondecounter;
    String s_unitref;
    // constructor
    //
    public BuildingsAHeadquarters(String ref, String data, String typ,int x, int y, 
            int startX, int startY, String plID, int persID, float energy) {
        super(ref, x, y, startX, startY, plID, persID, energy);
        initUnitArrays();
        s_unitref = data + "units/a/sonde" + typ;
        i_sondecounter = 0;
    }// constructor
    
    // init the unit arrays
    //
    public void initUnitArrays(){
        uas_sondes = new UnitASonde[i_sondesValue];
    }// initUnitArrays
    
    // add a sond to buildlist
    //
    public void addSondetoBuild(int persID, int energy){
        UnitASonde newSonde = new UnitASonde(s_unitref, this.getXPos() + (this.sp_sprite.getWidth() / 2),
                this.getStartY() + this.sp_sprite.getHeight() ,this.getStartX() + (this.sp_sprite.getWidth() / 2), this.getStartY(), 
                s_playerID, persID, energy);
        uas_sondes[i_sondecounter] = newSonde;
    }// addSondetoBuild
    
    // born a new sonde
    //
    public UnitASonde bornSonde(){
        UnitASonde tbornSonde = uas_sondes[i_sondecounter];
        uas_sondes[i_sondecounter] = null;
        i_sondecounter--;
        return tbornSonde;
    }// bornSonde
}// class Headquaters
