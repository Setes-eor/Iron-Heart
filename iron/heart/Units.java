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
    public Units(String ref, int x, int y, float energy){
        super(ref,x,y,energy);
    }// constructor

    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}// Units
