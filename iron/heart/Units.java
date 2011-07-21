/*
 * base class for all Units
 * 
 */
package iron.heart;

import iron.heart.BuildUnitBase;

/**
 *
 * @author setes
 */
public class Units extends BuildUnitBase{
    // constructor
    //
    public Units(String ref, int x, int y, int startX, int startY, String plID, 
            int persID, float energy){
        super(ref,x,y,startX,startY, plID, persID, energy);
    }// constructor

    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}// Units
