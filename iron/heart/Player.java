/*
 * class of the real player
 * 
 */
package iron.heart;

import java.awt.Graphics;

/**
 *
 * @author setes
 */
public class Player extends PlayerBasic{
    
    // attributes of the class
    //
    ResourceBar rb_resourcebar;
    
    // default-constructor
    //
    public Player(){
        super();
    }// default-constructor
    
    // constructor
    //
    public Player(String ID, String spezID, String mainpath, String typ, int res1, int res2){
        super(ID, spezID, mainpath, typ, res1, res2);
        String resourcebar = "resourcebar_sp1";
        if(spezID == "F")
            resourcebar = "resourcebar_sp2";
        rb_resourcebar = new ResourceBar(resourcebar, s_mainpath, s_typ, 5, 5,
                r_resources.getmaxPopulation());
    }// constructor
    
    @Override
    public void Draw(Graphics g){
        super.Draw(g);
        rb_resourcebar.Draw(g, r_resources.getOre(), r_resources.getGas(),
                r_resources.getPopulation());
    }// Draw
}// class Player
