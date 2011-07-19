/*
 * manage all the things about a game
 * 
 */
package iron.heart;

import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author setes
 */
public class Game {
    
    // attributes of the class
    //
    Field fi_field;
    List<Player> lpl_players;
    List<AI> lai_ais;
    String[] s_terraintyp = {"none","grass","earth","rock","water"};
    HashMap<String,String> hm_gamestats;
    String s_datapath;
    String s_typ;
    GameCavs gc_gamecavs;
    Optionmenu op_option;
    Minimap mi_map;
    int i_widthScreen;
    int i_heigthScreen;
    int i_fieldStartMaxX;
    int i_fieldStartMaxY;
    int i_minimapxy = 267;
    
    // constructor
    //
    public Game(String data, String typ, int screenX, int screenY, GameCavs game){
        gc_gamecavs = game;
        s_datapath = data;
        s_typ = typ;
        i_widthScreen = screenX;
        i_heigthScreen = screenY;
        lpl_players = new ArrayList<Player>();
        lai_ais = new ArrayList<AI>();
         
       
        setStateActive("Game");  
        
    }// constructor
    
    // init the menus
    //
    public void initMenus(String menubackground, String menupath, String mappath){
        op_option = null;
        op_option = new Optionmenu(menubackground, s_datapath + menupath, s_typ,
                i_widthScreen, i_heigthScreen, gc_gamecavs, this, mappath);
    }// initMenus
    
    // open and closethe option menu
    //
    public void setOptionvisible(boolean vis){
        op_option.setVisible(vis);
    }// setOptionvisible
    
    // draw all things of the game
    //
    public void Draw(Graphics g){
        fi_field.Draw(g);
        for(int i = 0; i < lpl_players.size(); i++)
            lpl_players.get(i).Draw(g);
        for(int i = 0; i < lai_ais.size(); i++)
            lai_ais.get(i).Draw(g);
        if(hm_gamestats.get("Option") == "active")
            op_option.Draw(g);
        mi_map.Draw(g);
    }// Draw
    
    // do something if key is pressed
    //
    public void keyPressed(String key){
        if(key == "g" && hm_gamestats.get("Game") == "active")
            fi_field.changeRasterVisible();
        if(key == "b" && hm_gamestats.get("Game") == "active" )
            lpl_players.get(0).keyPressed(key);
    }// keyPressed
    
    // set horizontal and vertical move of the field, buildings, ...
    //
    public void setHorizontalMove(double dx){
        fi_field.setHorizontalMove(dx);
    }// setHorizontalMove
    
    public void setVerticalMove(double dy){
        fi_field.setVerticalMove(dy);
    }// setVerticalMove
    
    // move all
    //
    public void move(long delta){
        
       if(hm_gamestats.get("Game") == "active"){
           
      
            //Point one = fi_field.getPosLeftUp();
            //Point two = fi_field.getPosRightDown();

            //int x1 = one.x;
            //int y1 = one.y;
            //int x2 = two.x;
            //int y2 = two.y;

            //if(x1 <= 0 && y1 <= 0 && x2 >= i_fieldStartMaxX -  i_widthScreen &&
              //   y2 >= i_fieldStartMaxX - i_heigthScreen)
             fi_field.move(delta);

            //if(x1 <= -5){
            // setHorizontalMove(15);
            //fi_field.move(delta);
           // }
         }
    }// move
    
    // move the mouse
    //
    public void mouseMove(int mouseX, int mouseY){
         if(hm_gamestats.get("Game") == "active")
            lpl_players.get(0).mouseMove(mouseX, mouseY);
         
         if (mouseX <= 20) {
            setHorizontalMove(15);
        } else if (mouseX >= 20) {
            setHorizontalMove(0);
        }
        if (mouseX >= i_widthScreen - 20) {
            setHorizontalMove(-15);
        } else if (mouseX <= i_widthScreen - 10) {
            setHorizontalMove(0);
        }
        if (mouseY <= 20.0) {
            setVerticalMove(15);
        } else if (mouseY >= 20.0) {
            setVerticalMove(0);
        }
        if (mouseY >= i_heigthScreen - 20) {
            setVerticalMove(-15);
        } else if (mouseY <= i_heigthScreen - 20) {
            setVerticalMove(0);
        }
    }// mouseMove
    
    // do something when the mouse clicked
    //
    public void mouseClicked(int mouseX, int mouseY){
        if(hm_gamestats.get("Option") == "active"){
            op_option.mouseClicked(mouseX, mouseY);
        }// if
        if(hm_gamestats.get("Game") == "active"){
            lpl_players.get(0).mouseClicked(mouseX, mouseY);
        }
    }// mouseClicked
    
    // load a map or a savegame
    //
    public void loadMap(String mappath) throws IOException{
        //saveState(mappath);
        BufferedReader maploader = new BufferedReader(new FileReader(mappath));
        
        // first load the gamefield
        //
        String readwidth = maploader.readLine();
        while(!readwidth.startsWith("Map-width")){
            readwidth = maploader.readLine();
        }// while
        String split = ":";
        String splitel = ",";
        String[]width = readwidth.split(split);
        int mapwidth = Integer.parseInt(width[1]);
        
        String readhigth = maploader.readLine();
        String[]heigth = readhigth.split(split);
        int mapheigth = Integer.parseInt(heigth[1]);
        
        fi_field = new Field(mapwidth, mapheigth);
        
        // read in players
        //
        String readplayer = maploader.readLine();
        while(!readplayer.startsWith("#Players")){
            readplayer = maploader.readLine();
        }// while
        String[]player = maploader.readLine().split(splitel);
        for(int i = 0; i < player.length; i++){
            String[] playerinfos = player[i].split(split);
            String playerID = playerinfos[0];
            String[] playermaininfo = playerID.split("#");
            String playerid = playermaininfo[0];
            String playerspez = playermaininfo[1];
            String[] resources = playerinfos[1].split(";");
            int res1 = Integer.parseInt(resources[0]);
            int res2 = Integer.parseInt(resources[1]);
            
            if(player[i].startsWith("P")){
                Player playere = new Player(playerid, playerspez, s_datapath, s_typ, res1, res2,
                        i_widthScreen, i_heigthScreen, fi_field);
                lpl_players.add(playere);
            }// if
            else if(player[i].startsWith("AI")){
                AI aie = new AI(playerid, playerspez, s_datapath, s_typ, res1, res2,
                        i_widthScreen, i_heigthScreen);
                lai_ais.add(aie);
            }// else if 
        }// for
        
        
        String readfield = maploader.readLine();
        while(!readfield.startsWith("#Field")){
            readfield = maploader.readLine();
        }// while
        for(int i = 0; i < mapheigth; i++){
            String[] fieldelements = maploader.readLine().split(splitel);
            for (int j = 0; j < mapwidth; j++) {
                String[] fieldelement = fieldelements[j].split(split);
                int terrainid = Integer.parseInt(fieldelement[0]);
                String build = fieldelement[1];
                String buildtyp = "none";
                if(build == "000")
                    buildtyp = "none";
                fi_field.initFieldElement(s_terraintyp[terrainid], buildtyp, j, i);
            }// for
        }// for 
        maploader.close();
        fi_field.initTerrain(s_datapath, s_typ);
        Point two = fi_field.getPosRightDown();

        int i_fieldStartMaxX = two.x;
        int i_fieldStartMaxY = two.y;
        
        mi_map = new Minimap(s_datapath + "gfx/displays/minimap" + s_typ ,
                i_widthScreen  - i_minimapxy - 5, 5);
        //fi_field.printTerrain();
    }// loadMap
    
    // save the gamestate
    //
    public void saveState(String savepath) throws IOException{
         BufferedWriter buffwri = new BufferedWriter(new FileWriter(savepath, true));
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 90; j++) {
                if( j < 89)
                    buffwri.write("1:000,");
                else
                    buffwri.write("1:000,");
            }
            buffwri.newLine();
        }
        buffwri.flush();
        buffwri.close();
    }// saveState
    
    // init the stats
    //
    public void initStats() {
        hm_gamestats = new HashMap<String, String>();
        hm_gamestats.put("Game", "inactive");
        hm_gamestats.put("Option", "inactive");
    }// initStats

    // change the vale of the stats
    //
    public void setStateActive(String key) {
        initStats();
        hm_gamestats.put(key, "active");
    }// setStateActive
}// class Game
