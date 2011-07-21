/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iron.heart;

import java.awt.Graphics;
import java.awt.Point;
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
    boolean b_rastervisible;
    
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
        b_rastervisible = true;
    }// default-constructor
    
    // constructor
    //
    public Field(int width, int heigth){
        i_fieldWidth = width;
        i_fieldHeigth = heigth;
        fe_field = new FieldElement[i_fieldHeigth][i_fieldWidth];
        lbe_terrain = new ArrayList<BasicEntity>();
        lbe_raster = new ArrayList<BasicEntity>();
        b_rastervisible = true;
    }// constructor
    
    // init a fieldelement
    //
    public void initFieldElement(String terrain, String build, int x, int y){
        FieldElement fieldelement = new FieldElement(terrain,build);
        fe_field[y][x] = fieldelement;
    }// initFieldElement
    
    
    // get the x and y from the first terrain field
    //
    public int getXFirstField(){return lbe_terrain.get(0).getXPos();}
    public int getYFirstField(){return lbe_terrain.get(0).getYPos();}
    
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
        //initRaster(datapath,typ);
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
        changeRasterVisible();
    }// initTerrain
    
    // is the field free for a build
    //
    public boolean isfieldFree(int x, int y, String build, String raster){
        // Größe der Gebäude beachten beachten !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(raster.equals("2*2")){
            if (fe_field[y / 64][x / 64].isFieldFree() && fe_field[y / 64][(x / 64) + 1].isFieldFree()
                    && fe_field[(y / 64) + 1][x / 64].isFieldFree()
                    && fe_field[(y / 64) + 1][(x / 64) + 1].isFieldFree())
                return true;
            else return false;
        }// if
        else if(raster.equals("2*1"))
        {
            if (fe_field[y / 64][x / 64].isFieldFree() && fe_field[y / 64][(x / 64) + 1].isFieldFree())
                return true;
            else 
                return false;
        }// if
        else if (raster.equals("1*1")) {
            if (fe_field[y / 64][x / 64].isFieldFree())
                return true;
            else 
                return false;
        }// if
        else if (raster.equals("2*3")) {
            if (fe_field[y / 64][x / 64].isFieldFree() && fe_field[y / 64][(x / 64) + 1].isFieldFree()
                    && fe_field[(y / 64) + 1][x / 64].isFieldFree()
                    && fe_field[(y / 64) + 1][(x / 64) + 1].isFieldFree()
                    && fe_field[(y / 64) + 2][x / 64].isFieldFree()
                    && fe_field[(y / 64) + 2][(x / 64) + 1].isFieldFree()) 
                return true;
            else
                return false;
        }// if
        else if (raster.equals("3*3")) {
            if (fe_field[y / 64][x / 64].isFieldFree() && fe_field[y / 64][(x / 64) + 1].isFieldFree()
                    && fe_field[y / 64][(x / 64) + 2].isFieldFree()
                    && fe_field[(y / 64) + 1][x / 64].isFieldFree()
                    && fe_field[(y / 64) + 1][(x / 64) + 1].isFieldFree()
                    && fe_field[(y / 64) + 1][(x / 64) + 2].isFieldFree()
                    && fe_field[(y / 64) + 2][x / 64].isFieldFree()
                    && fe_field[(y / 64) + 2][(x / 64) + 1].isFieldFree()
                    && fe_field[(y / 64) + 2][(x / 64) + 2].isFieldFree())
                return true;
            else 
                return false;
        }// if
        return false;
    }// isfieldFree
    
    // add a build
    //
    public void addBuild(int x, int y, String build, String raster){
        if (raster.equals("2*2")) {
                fe_field[y / 64][(x / 64) + 1].setBuild(build);
                fe_field[(y / 64) + 1][x / 64].setBuild(build);
                fe_field[(y / 64) + 1][(x / 64) + 1].setBuild(build);
        }// if
        else if (raster.equals("2*1")) {
                fe_field[y / 64][x / 64].setBuild(build);
        }// if
        else if (raster.equals("1*1")) {
                fe_field[y / 64][x / 64].setBuild(build);
        }// if
        else if (raster.equals("2*3")) {
                fe_field[y / 64][x / 64].setBuild(build);
                fe_field[y / 64][(x / 64) + 1].setBuild(build);
                fe_field[(y / 64) + 1][x / 64].setBuild(build);
                fe_field[(y / 64) + 1][(x / 64) + 1].setBuild(build);
                fe_field[(y / 64) + 2][x / 64].setBuild(build);
                fe_field[(y / 64) + 2][(x / 64) + 1].setBuild(build);
        }// if
        else if (raster.equals("3*3")) {
                fe_field[y / 64][x / 64].setBuild(build);
                fe_field[y / 64][(x / 64) + 1].setBuild(build);
                fe_field[y / 64][(x / 64) + 2].setBuild(build);
                fe_field[(y / 64) + 1][x / 64].setBuild(build);
                fe_field[(y / 64) + 1][(x / 64) + 1].setBuild(build);
                fe_field[(y / 64) + 1][(x / 64) + 2].setBuild(build);
                fe_field[(y / 64) + 2][x / 64].setBuild(build);
                fe_field[(y / 64) + 2][(x / 64) + 1].setBuild(build);
                fe_field[(y / 64) + 2][(x / 64) + 2].setBuild(build);  
        }// if
        //printTerrain();
    }// addBuild
    
    // calculate a point in the raster of the field
    //
    public void Raster(int mouseX, int mouseY, Player player){
        // Raster Setzung
        int rasterBigX = 0;
        int rasterSmallX = 0;
        int rasterBigY = 0;
        int rasterSmallY = 0;
        int rasterSetX = mouseX;
        int rasterSetY = mouseY;
        int rasterindex = 64;
        int width = i_fieldWidth * 64;
        int heigth = i_fieldHeigth * 64;

        
        while (rasterSetX > rasterBigX) {

            rasterSmallX = rasterBigX;
            rasterBigX += rasterindex;
            if (rasterBigX > width) {
                System.out.println("out of the field");

                break;
            }// if Auserhalb Spielfeld
        }// while RasterBerechnung

        if (rasterSetX - rasterSmallX <= rasterBigX - rasterSetX) {
            rasterSetX = rasterSmallX;
        } else {
            rasterSetX = rasterBigX;
        }

        // Raster Berechnung für Y
        while (rasterSetY > rasterBigY) {

            rasterSmallY = rasterBigY;
            rasterBigY += rasterindex; 
            if (rasterBigY > heigth ) {
                System.out.println("out of the field");

            }// if Y auserhalb Spielfeld
        }// while RasterBerechnung Y

        if (rasterSetY - rasterSmallY <= rasterBigY - rasterSetY) {
            rasterSetY = rasterSmallY;
        } else {
            rasterSetY = rasterBigY;
        }

        player.moveCurser(rasterSetX, rasterSetY);
    }// Raster
    
    // draw the terrain ...
    //
    public void Draw(Graphics g){
        for(int i = 0; i < i_fieldHeigth * i_fieldWidth; i++){
            lbe_terrain.get(i).Draw(g);
            //lbe_raster.get(i).Draw(g);
        }// for 
    }// Draw
    
    // change the visible of the raster
    //
    public void changeRasterVisible(){
        if(b_rastervisible){
            for(int i = 0; i < i_fieldHeigth * i_fieldWidth; i++){
                b_rastervisible = false;
                lbe_raster.get(i).setVisible(false);
            }// for 
        }// if
        else {
            for (int i = 0; i < i_fieldHeigth * i_fieldWidth; i++) {
                b_rastervisible = true;
                lbe_raster.get(i).setVisible(true);
            }// for 
        }// else   
    }// changeRasterVisible
    
    // set horizontal and vertical move of the field, buildings, ...
    //
    public void setHorizontalMove(double dx){
         for(int i = 0; i < i_fieldHeigth * i_fieldWidth; i++){
            lbe_terrain.get(i).setHorizontalMove(dx);
            //if(b_rastervisible)
              //  lbe_raster.get(i).setHorizontalMove(dx);
         }// for
    }// setHorizontalMove
    
    public void setVerticalMove(double dy){
        for(int i = 0; i < i_fieldHeigth * i_fieldWidth; i++){
            lbe_terrain.get(i).setVerticalMove(dy);
            //if(b_rastervisible)
              //  lbe_raster.get(i).setVerticalMove(dy);
        }// for
    }// setVerticalMove
    
    // move all the thinks
    //
    public void move(long delta){
        for(int i = 0; i < i_fieldHeigth * i_fieldWidth; i++){
            lbe_terrain.get(i).move(delta);
            //if(b_rastervisible)
              //  lbe_raster.get(i).move(delta);
        }// for
    }// move
    
    // getter
    //
    public int getFieldWidth(){return i_fieldWidth;}
    public int getFieldHeigth(){return i_fieldHeigth;}
    public Point getPosLeftUp(){return new Point(lbe_terrain.get(0).getXPos(),lbe_terrain.get(0).getYPos());}
    public Point getPosRightDown(){return new Point(lbe_terrain.get(i_fieldWidth - 1).getXPos(),
            lbe_terrain.get(i_fieldHeigth - 1).getYPos());}
    
    // print methods for debugging
    //
    public void printTerrain(){
        for(int i = 0; i < i_fieldHeigth; i++){
            for(int j = 0; j < i_fieldWidth; j++){
                if(fe_field[i][j].getBuild().equals("main"))
                    System.out.print(1);
                else
                    System.out.print(0);
            }// for  
            System.out.print("\n");
        }// for
    }// printTerrain
}// class Field
