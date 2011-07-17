/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iron.heart;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setes
 */
public class Field {
    
    // attribute of the class
    //
    FieldElement[][] fe_field;
    List<BasicEntity> lbe_terrain;
    List<BasicEntity> lbe_raster;
    int i_fieldWidth;
    int i_fieldHeigth;
    
    // default-constructor
    //
    public Field(){
        i_fieldWidth = 120;
        i_fieldHeigth = 80;
        fe_field = new FieldElement[i_fieldHeigth][i_fieldWidth];
        
        for(int i = 0; i < i_fieldHeigth; i++)
            for(int j = 0; j < i_fieldWidth; j++){
                FieldElement fieldelement = new FieldElement();
                fe_field[i][j] = fieldelement;
            }// for  
        lbe_terrain = new ArrayList<BasicEntity>();
        lbe_raster = new ArrayList<BasicEntity>();
    }// default-constructor
    
    // constructor
    //
    public Field(int width, int heigth){
        i_fieldWidth = width;
        i_fieldHeigth = heigth;
        fe_field = new FieldElement[i_fieldHeigth][i_fieldWidth];
        lbe_terrain = new ArrayList<BasicEntity>();
        lbe_raster = new ArrayList<BasicEntity>();
    }// constructor
    
    // init a fieldelement
    //
    public void initFieldElement(String terrain, String build, int x, int y){
        FieldElement fieldelement = new FieldElement(terrain,build);
        fe_field[y][x] = fieldelement;
    }// initFieldElement
    
    // init the terrain sprites
    //
    public void initTerrain(String datapath, String typ){
        String terrainpath = "gfx/terrain/";
        String terraintyp = "grassg";
        for(int i = 0; i < i_fieldHeigth; i++)
            for(int j = 0; j < i_fieldWidth; j++){
                if(fe_field[i][j].getTerrainTyp() == "")
                    terraintyp = "grass";
               BasicEntity terrain = new BasicEntity(datapath + terrainpath + terraintyp +
                       typ, j * 64, i * 64);
               lbe_terrain.add(terrain);
            }// for 
        initRaster(datapath,typ);
    }// initTerrain
    
    // init the terrain sprites
    //
    private void initRaster(String datapath, String typ){
        String rasterpath = "gfx/terrain/";
        String rastertyp = "raster";
        for(int i = 0; i < i_fieldHeigth; i++)
            for(int j = 0; j < i_fieldWidth; j++){
              
               BasicEntity raster = new BasicEntity(datapath + rasterpath + rastertyp +
                       typ, j * 64, i * 64);
               lbe_raster.add(raster);
            }// for  
    }// initTerrain
    
    // draw the terrain ...
    //
    public void Draw(Graphics g){
        for(int i = 0; i < i_fieldHeigth * i_fieldWidth; i++){
            lbe_terrain.get(i).Draw(g);
            lbe_raster.get(i).Draw(g);
        }// for 
    }// Draw
    
    // getter
    //
    public int getFieldWidth(){return i_fieldWidth;}
    public int getFieldHeigth(){return i_fieldHeigth;}
    
    // print methods for debugging
    //
    public void printTerrain(){
        for(int i = 0; i < i_fieldHeigth; i++){
            for(int j = 0; j < i_fieldWidth; j++){
                if(fe_field[i][j].getTerrainTyp() == "grass")
                    System.out.print(1);
                else
                    System.out.print(0);
            }// for  
            System.out.print("\n");
        }// for
    }// printTerrain
}// class Field
