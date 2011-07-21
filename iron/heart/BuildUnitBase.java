/*
 * base class for all buildings and units
 * 
 */
package iron.heart;

/**
 *
 * @author setes
 */
public abstract class BuildUnitBase extends Entity{
    
    // attributes of the class
    //
    String s_playerID;
    int i_persID;
    int i_startX;
    int i_startY;
    float f_energy;
    
    // constructor
    //
    public BuildUnitBase(String ref, int x, int y, int startX, int startY,
            String ID, int persID, float energy){
        super(ref,x,y);
        i_startX = startX;
        i_startY = startY;
        f_energy = energy;
        s_playerID = ID;
        i_persID = persID;
    }// BuildUnitBase
    
    // getter
    //
    public int getStartX(){return i_startX;}
    public int getStartY(){return i_startY;}
    public int getPersID(){return i_persID;}
}// class BuildUnitBase
