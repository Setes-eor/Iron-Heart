/*
 * class of all AIs
 * 
 */
package iron.heart;

/**
 *
 * @author setes
 */
public class AI extends PlayerBasic{
    
    // default-constructor
    //
    public AI(){
        super();
    }// default-constructor
    
    // constructor
    //
    public AI(String ID, String spezID, String mainpath, String typ, int res1, int res2,
            int screenWidth, int screenHeigth){
        super(ID, spezID, mainpath, typ, res1, res2, screenWidth, screenHeigth);
    }// constructor
}// class AI
