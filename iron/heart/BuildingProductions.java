/*
 * the base class of all production builds
 * 
 */
package iron.heart;

/**
 *
 * @author setes
 */
public class BuildingProductions extends Buildings{
    
    // constructor
    //
    public BuildingProductions(String ref, int x, int y, int startX, int startY, String plID, 
            int persID, float energy){
        super(ref,x,y,startX,startY, plID, persID, energy);
    }// constructor
}// class BuildingProductions
