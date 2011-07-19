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
    BuildMenu bm_buildmenu;
    BasicEntity be_curser;
    public boolean b_buildactive;
    Field fi_field;
    
    // default-constructor
    //
    public Player(){
        super();
    }// default-constructor
    
    // constructor
    //
    public Player(String ID, String spezID, String mainpath, String typ, int res1, int res2,
            int screenWidth, int screenHeight, Field field){
        super(ID, spezID, mainpath, typ, res1, res2, screenWidth, screenHeight);
        String resourcebar = "resourcebar_sp1";
        if(spezID == "F")
            resourcebar = "resourcebar_sp2";
        rb_resourcebar = new ResourceBar(resourcebar, s_mainpath, s_typ, 5, 5,
                r_resources.getmaxPopulation());
        bm_buildmenu = new BuildMenu("backgrounds/buildmenu", mainpath ,
                typ,i_screenWidth - 267 - 5, 10 + 267, s_speziesID, this);
        b_buildactive = false;
        fi_field = field;
    }// constructor
    
    // init the Curser
    //
    public void initCurser(String ref, int x, int y){
        be_curser = new BasicEntity(ref,x,y);
        b_buildactive = true;
    }// initCurser
    
    // move the curser with the mouse
    //
    public void moveCurser(int mousex, int mousey){
        if(b_buildactive){
            be_curser.setXPos(mousex);
            be_curser.setYPos(mousey);
        }// if
    }// move Curser
    
    // do something by keypressed
    //
    public void keyPressed(String key){
        if(key == "b"){
            bm_buildmenu.setBuildArtActive("builds");
            bm_buildmenu.changeVisible();
        }// if
    }// keyPressed
    
    // do somthing by mouseclick
    //
    public void mouseClicked(int mouseX, int mouseY){
        bm_buildmenu.mouseClicked(mouseX, mouseY);
    }// mouseClicked
    
    // move the mouse
    //
    public void mouseMove(int mouseX, int mouseY){
         if(b_buildactive){
             fi_field.Raster(mouseX, mouseY, this);
         }// if
            
    }// mouseMove
    
    @Override
    public void Draw(Graphics g){
        super.Draw(g);
        rb_resourcebar.Draw(g, r_resources.getOre(), r_resources.getGas(),
                r_resources.getPopulation());
        bm_buildmenu.Draw(g);
        if(b_buildactive)
            be_curser.Draw(g);
    }// Draw
    
}// class Player
