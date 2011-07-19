/*
 * shows a the map
 * 
 */
package iron.heart;

/**
 *
 * @author setes
 */
public class Minimap extends Entity{

    // constructor
    //
    public Minimap(String ref, int x, int y){
        super(ref,x,y);
    }// Minimap
    
    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}// Minimap
