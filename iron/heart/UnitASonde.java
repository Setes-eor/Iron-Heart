/*
 * the sonde of the spezies A
 * 
 */
package iron.heart;

/**
 *
 * @author setes
 */
public class UnitASonde extends UnitCollectors{
   
    // attributes of the class
    //
    
    // constructor
    //
    public UnitASonde(String ref, int x, int y, int startX, int startY, String plID, 
            int persID, float energy){
        super(ref,x,y,startX,startY, plID, persID, energy);
        i_productionTime = 10;
    }// constructor
    
    // getter und setter
    //
    public int getProductionTime(){return i_productionTime;}
}// UnitCollectors
