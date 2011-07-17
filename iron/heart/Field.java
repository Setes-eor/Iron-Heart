/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iron.heart;

/**
 *
 * @author setes
 */
public class Field {
    
    // attribute of the class
    //
    FieldElement[][] fe_field;
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
    }// default-constructor
    
    // constructor
    //
    public Field(int width, int heigth){
        i_fieldWidth = width;
        i_fieldHeigth = heigth;
        fe_field = new FieldElement[i_fieldHeigth][i_fieldWidth];
    }// constructor
    
    // init a fieldelement
    //
    public void initFieldElement(String terrain, String build, int x, int y){
        FieldElement fieldelement = new FieldElement(terrain,build);
        fe_field[y][x] = fieldelement;
    }// initFieldElement
    
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
