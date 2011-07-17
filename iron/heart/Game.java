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

/**
 *
 * @author setes
 */
public class Game {
    
    // attributes of the class
    //
    Field fi_field;
    String[] s_terraintyp = {"none","grass","earth","rock","water"};
    String s_datapath;
    String s_typ;
    int i_widthScreen;
    int i_heigthScreen;
    int i_fieldStartMaxX;
    int i_fieldStartMaxY;
    
    // constructor
    //
    public Game(String data, String typ, int screenX, int screenY){
        s_datapath = data;
        s_typ = typ;
        i_widthScreen = screenX;
        i_heigthScreen = screenY;
    }// constructor
    
    // draw all things of the game
    //
    public void Draw(Graphics g){
        fi_field.Draw(g);
    }// Draw
    
    // do something if key is pressed
    //
    public void keyPressed(String key){
        if(key == "g")
            fi_field.changeRasterVisible();
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
        Point one = fi_field.getPosLeftUp();
        Point two = fi_field.getPosRightDown();
        
        int x1 = one.x;
        int y1 = one.y;
        int x2 = two.x;
        int y2 = two.y;
        
        if(x1 <= 0 && y1 <= 0 && x2 >= i_fieldStartMaxX -  i_widthScreen &&
                y2 >= i_fieldStartMaxX - i_heigthScreen)
            fi_field.move(delta);
        
        if(x1 < 0){
            setHorizontalMove(15);
            move(delta);
        }
    }// move
    
    // load a map or a savegame
    //
    public void loadMap(String mappath) throws IOException{
        BufferedReader maploader = new BufferedReader(new FileReader(mappath));
        
        // first load the gamefield
        //
        String readwidth = maploader.readLine();
        while(!readwidth.startsWith("Map-width")){
            readwidth = maploader.readLine();
        }// while
        String split = ":";
        String[]width = readwidth.split(split);
        int mapwidth = Integer.parseInt(width[1]);
        
        String readhigth = maploader.readLine();
        String[]heigth = readhigth.split(split);
        int mapheigth = Integer.parseInt(heigth[1]);
        
        fi_field = new Field(mapwidth, mapheigth);
        
        String readfield = maploader.readLine();
        while(!readfield.startsWith("#Field")){
            readfield = maploader.readLine();
        }// while
        for(int i = 0; i < mapheigth; i++){
            String[] fieldelements = maploader.readLine().split(",");
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
        
        //fi_field.printTerrain();
    }// loadMap
    
    // save the gamestate
    //
    public void saveState(String savepath) throws IOException{
         BufferedWriter buffwri = new BufferedWriter(new FileWriter(savepath, true));
        for (int i = 0; i < 160; i++) {
            for (int j = 0; j < 240; j++) {
                buffwri.write("1:000,");
            }
            buffwri.newLine();
        }
        buffwri.flush();
        buffwri.close();
    }// saveState
}// class Game
