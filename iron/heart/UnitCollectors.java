/*
 * the base for both spezies collectors
 * 
 */
package iron.heart;

/**
 *
 * @author setes
 */
public abstract class UnitCollectors extends Units{
    
    // attributes of the class
    //
    protected int i_backpackValue = 10;
    protected int i_backpack1;
    protected int i_backpack2;
    protected int i_productionTime;
    protected int i_collectorRate;
    
    // constructor
    //
    public UnitCollectors(String ref, int x, int y, int startX, int startY, String plID, 
            int persID, float energy){
        super(ref,x,y,startX,startY, plID, persID, energy);
    }// constructor
}// UnitCollectors
