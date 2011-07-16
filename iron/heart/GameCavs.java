package iron.heart;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * show the game-field and do the game (game-loop)
 * 
 */

/**
 *
 * @author setes
 */
public class GameCavs extends Canvas implements Runnable, KeyListener, MouseMotionListener{
    
    // attributes of the class
    //
    int i_widthScreen = 1024;
    int i_heightScreen = 768; 
    int i_mouseX;
    int i_mouseY;
    long l_period = 10;
    boolean b_mouseup;
   
    BufferStrategy bs_Buffer;
    Graphics g;
    Thread t;
    
    public StartMenu sm_startmenu;
    public NewGameMenu ng_newgame;
    public LoadMenu lm_loadmenu;
    public Credits cr_credits;
    public Properties pr_properties;
    public AICombatMenu cm_combatmenu;
    
    BasicEntity be_map;
    String s_datapath = "data/";
    String s_menupath = "menus/";
    String s_menubackground = "backgrounds/mainmenu";
    String s_typ = ".png";
    HashMap<String,String> hm_stats;
    
    // constructor
    //
    public GameCavs(int x, int y){
        i_widthScreen = x;
        i_heightScreen = y;
        
        b_mouseup = true;
        
        sm_startmenu = new StartMenu(s_menubackground, s_datapath + s_menupath, s_typ,
                i_widthScreen, i_heightScreen, this);
        ng_newgame = new NewGameMenu(s_menubackground, s_datapath + s_menupath, s_typ,
                i_widthScreen, i_heightScreen, this);
        lm_loadmenu = new LoadMenu(s_menubackground, s_datapath + s_menupath, s_typ,
                i_widthScreen, i_heightScreen, this);
        pr_properties = new Properties(s_menubackground, s_datapath + s_menupath, s_typ,
                i_widthScreen, i_heightScreen, this);
        cr_credits = new Credits(s_menubackground, s_datapath + s_menupath, s_typ,
                i_widthScreen, i_heightScreen, this);
        cm_combatmenu = new AICombatMenu(s_menubackground, s_datapath + s_menupath, s_typ,
                i_widthScreen, i_heightScreen, this);
        
        this.setIgnoreRepaint(true);
        this.setBounds(0, 0, i_widthScreen, i_heightScreen);
        this.setBackground(Color.GRAY);
            
        this.setVisible(true); 
        
        addKeyListener(this);      
        // add mousemotionlistener to get x and y from the mouse
        addMouseMotionListener(this);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseClicked1(evt);
            }
       });
             
        setStateActive("Start");
        
        //initWorld();
    }// constructor
    
    // start the game and init the buffer
    //
    public void addNotify(){
        super.addNotify();
        this.createBufferStrategy(2);
        this.bs_Buffer = this.getBufferStrategy();
        
        startGame();       
    }// addNotify
    
    // start game thread
    //
    public void startGame(){
        if(t == null){
            t = new Thread(this);
            t.start();
        }// if
    }// startGame
    
    // run the game with update render and draw / the game-loop
    //
    public void run(){
        while(true){
            
            long beginTime = System.currentTimeMillis();
            
            Update();
            Render();
            Draw();
            
            long timeTaken = System.currentTimeMillis();
            long sleepTime = l_period - timeTaken;
            
            try{
                t.sleep(sleepTime);
            }// try
            catch(Exception e){              
            }// catch
        }// while
    }// run
      
    // update all the thinks of the game: for examp. positions, ressources, ....
    //
    public void Update(){
        if((hm_stats.get("Game")) == "active"){
            be_map.move(500);
            
        }// move
    }// Update
    
    // render the entities
    //
    public void Render(){
        g = bs_Buffer.getDrawGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, i_widthScreen, i_heightScreen);
        
        // Paint stuff
        if(hm_stats.get("Start") == "active"){
            sm_startmenu.Draw(g);
            ng_newgame.Draw(g);
            lm_loadmenu.Draw(g);
            pr_properties.Draw(g);
            cr_credits.Draw(g);
            cm_combatmenu.Draw(g);
        }// if  
        // show the mousepositions
        // System.out.println("mousepositions: " + i_mouseX + " " + i_mouseY);
    }// Render
    
    // draw with the double-buffer
    //
    public void Draw(){
        if(!bs_Buffer.contentsLost()){
            bs_Buffer.show();
            
            if(g != null){
                g.dispose();
            }// if
        }// if
    }// Draw

    
    public void keyTyped(KeyEvent e){
        
    }// keyTyped
    
    // do something when the mousekey is pressed
    //
    public void mouseClicked1(MouseEvent e){
        if(b_mouseup){
            System.out.println("mouse" + " " + e.getButton());
            if (hm_stats.get("Start") == "active") {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    sm_startmenu.mouseClicked(i_mouseX, i_mouseY);
                    ng_newgame.mouseClicked(i_mouseX, i_mouseY);
                    lm_loadmenu.mouseClicked(i_mouseX, i_mouseY);
                    pr_properties.mouseClicked(i_mouseX, i_mouseY);
                    cr_credits.mouseClicked(i_mouseX, i_mouseY, 2);
                    cm_combatmenu.mouseClicked(i_mouseX, i_mouseY);
                }// if
                else if (e.getButton() == MouseEvent.BUTTON3) {
                    cr_credits.mouseClicked(i_mouseX, i_mouseY, 1);
                }// if
            }// if
            b_mouseup = false;
        }// if
    }// mouseClicked1
    
    // scroll over the map by mousemovement
    //
    public void mouseMoved(final MouseEvent e) {
        i_mouseX = e.getX();
        i_mouseY = e.getY();
            
        /*if((hm_stats.get("Game")) == "active" ){
            if (i_mouseX < 10.0) {
                //moveHorizontal(15);
            } else if (i_mouseX > 500.0) {
                //resetHorizontal();
            }
            if (i_mouseX > 1900.0 - i_MoveResolutionX && be_map.getXPos() > -1050) {
                //moveHorizontal(-15);
            } else if (i_mouseX < 1900.0 - i_MoveResolutionX) {
                //resetHorizontal();
            }
            if (i_mouseY < 10.0 && be_map.getYPos() < 0) {
                //moveVertical(15);
            } else if (i_mouseY > 10.0) {
                //resetVertical();
            }
            if (i_mouseY > 1000.0 - i_MoveResolutionY && be_map.getYPos() > -1050) {
                //moveVertical(-15);
            } else if (i_mouseY < 1000.0 - i_MoveResolutionY) {
                //resetVertical();
            }
        }// if*/
        
    }// mouseMoved
      
    // do all the thinks, when a key is pressed
    //
    public void keyPressed(KeyEvent e){
       /* if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if((hm_stats.get("Start")) == "active")
                  
            if((hm_stats.get("Option")) == "active")
                 
                }*/
            
        
        if((hm_stats.get("Game")) == "active"){
            if (e.getKeyCode() == KeyEvent.VK_LEFT && be_map.getXPos() < 0) {
               // moveHorizontal(15);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && be_map.getXPos() > -1050) {
                //moveHorizontal(-15);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP && be_map.getYPos() < 0) {
                //moveVertical(15);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN && be_map.getYPos() > -1050) {
                //moveVertical(-15);
            }
            if (e.getKeyCode() == KeyEvent.VK_B) {
                
            }// if
            if (e.getKeyCode() == KeyEvent.VK_L) {
                
            }// if
            if (e.getKeyCode() == KeyEvent.VK_O) {
                setStateActive("Option");
            }// if
        }// if
        
    }// keyPressed
      
    /*public void moveHorizontal(int value){
        be_map.setHorizontalMove(value);
    }// moveUp
    
    public void moveVertical(int value){
        be_map.setVerticalMove(value);
        
    }// moveDown
    
    public void resetHorizontal(){
        be_map.setHorizontalMove(0);
        
    }// resetHorizontal
    
    public void resetVertical(){
        be_map.setVerticalMove(0);
        
    }// resetVertical*/
    
    // do all the thinks, when a key is released
    //
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //resetHorizontal();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //resetHorizontal();
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            //resetVertical();
        }// if
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            //resetVertical();
        }// if     
    }

    
    public void mouseDragged(MouseEvent me) {
        
    }

    // init the stats
    //
    public void initStats(){
        hm_stats = new HashMap<String,String>();
        hm_stats.put("Start", "inactive");
        hm_stats.put("Game", "inactive");
        hm_stats.put("Credits", "inactive");
        hm_stats.put("Load", "inactive");
        hm_stats.put("Option", "inactive");
    }// initStats
    
    // change the vale of the stats
    //
    public void setStateActive(String key){
        initStats();
        hm_stats.put(key, "active");
    }// setStateActive
    
    
    
    
   
  
}// class GameCavs


