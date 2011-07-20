/*
 * the base class of all buildings
 * 
 */
package iron.heart;

import iron.heart.BuildUnitBase;

/**
 *
 * @author setes
 */
public class Buildings extends BuildUnitBase{
    
    // constructor
    //
    public Buildings(String ref, int x, int y, float energy){
        super(ref,x,y,energy);
    }// constructor

    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}// Buildings
