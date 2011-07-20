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
    int i_startX;
    int i_startY;
    float f_energy;
    
    // constructor
    //
    public BuildUnitBase(String ref, int x, int y, float energy){
        super(ref,x,y);
        i_startX = x;
        i_startY = y;
        f_energy = energy;
    }// BuildUnitBase
    
    
}// class BuildUnitBase
