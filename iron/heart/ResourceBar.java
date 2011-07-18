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
public class ResourceBar extends Entity{
    
    // attributes of the class
    //
    BasicEntity be_numbers;
    BasicEntity be_slash;
    List<BasicEntity> lbe_res1;
    List<BasicEntity> lbe_res2;
    List<BasicEntity> lbe_population;
    List<BasicEntity> lbe_maxPopulation;
    String s_displaypath = "displays/";
    int PanelElementCounter; 
    int i_counterRes1; 
    int i_counterRes2; 
    int i_counterPopulation;
    int i_counterMaxPopulation;
    int i_resi1X; 
    int i_numbersY; 
    int i_resi2X, i_resi2Y;
    int i_populationX;
    int i_maxPopulationX;
    int i_numnumbersresi1;
    int i_maxPopulation;
    
    //constructor
    //
    public ResourceBar(String ref,  String data, String typ, int x, int y, int maxPop){
        super(data + "displays/" + ref  + typ, x, y);
        i_numnumbersresi1 = 4;
        i_resi1X = 130;
        i_resi2X = 280;
        i_populationX = 410;
        i_maxPopulationX = i_populationX + 80;
        i_numbersY = 14;
        i_maxPopulation = maxPop;
        
        be_slash = new BasicEntity(data + s_displaypath + "slash" + typ, i_populationX + 20, i_numbersY);
        
        lbe_res1 = new ArrayList<BasicEntity>();
        lbe_res2 = new ArrayList<BasicEntity>();
        lbe_population = new ArrayList<BasicEntity>();
        lbe_maxPopulation = new ArrayList<BasicEntity>();
        
        for (int i = 0; i < i_numnumbersresi1; i++) {
            
            BasicEntity resi1 = new BasicEntity(data + s_displaypath + "numbers" + typ, i_resi1X, i_numbersY);
            BasicEntity resi2 = new BasicEntity(data + s_displaypath + "numbers" + typ, i_resi2X, i_numbersY);
            BasicEntity population = new BasicEntity(data + s_displaypath + "numbers" + typ, i_populationX, i_numbersY);
            BasicEntity maxpopulation = new BasicEntity(data + s_displaypath + "numbers" + typ, i_maxPopulationX, i_numbersY);
            
            lbe_res1.add(resi1); 
            lbe_res2.add(resi2);
            if(i < i_numnumbersresi1 - 1){
                lbe_population.add(population);
                lbe_maxPopulation.add(maxpopulation);
            }// if

            i_resi1X -= 20;
            i_resi2X -= 20;
            i_populationX -= 20;
            i_maxPopulationX -= 20;
        }// for
    }//  constructor

    public void Draw(Graphics g, int res1, int res2, int population){
        sp_sprite.Draw(g,(int) d_xPos,(int) d_yPos);
        
        for (int i = 0; i < lbe_res1.size(); i++) {

            switch (i) {
                case (0):
                    i_counterRes1 = res1 % 10;
                    i_counterRes2 = res2 % 10;
                    i_counterPopulation = population % 10;
                    i_counterMaxPopulation = i_maxPopulation % 10;
                    break;
                case (1):
                    i_counterRes1 = (res1 % 100) / 10;
                    i_counterRes2 = (res2 % 100) / 10;
                    i_counterPopulation = (population % 100) / 10;
                    i_counterMaxPopulation = (i_maxPopulation % 100) / 10;
                    break;
                case (2):
                    i_counterRes1 = (res1 % 1000) / 100;
                    i_counterRes2 = (res2 % 1000) / 100;
                    i_counterPopulation = (population % 1000) / 100;
                    i_counterMaxPopulation = (i_maxPopulation % 1000) / 100;
                    break;
                case (3):
                    i_counterRes1 = (res1 % 10000) / 1000;
                    i_counterRes2 = (res2 % 10000) / 1000;
                    i_counterPopulation = (population % 10000) / 1000;
                    i_counterMaxPopulation = (i_maxPopulation % 10000) / 1000;
                    break;
                default:
                    break;
            }// swich

            lbe_res1.get(i).sp_sprite.setSubImage(20 * i_counterRes1, 0, 20, 28);
            lbe_res1.get(i).sp_sprite.DrawSubImage(g, lbe_res1.get(i).getXPos(), lbe_res1.get(i).getYPos());
            lbe_res2.get(i).sp_sprite.setSubImage(20 * i_counterRes2 , 0, 20, 28);
            lbe_res2.get(i).sp_sprite.DrawSubImage(g, lbe_res2.get(i).getXPos(), lbe_res2.get(i).getYPos());
            if(i < lbe_res1.size() - 1){
                lbe_population.get(i).sp_sprite.setSubImage(20 * i_counterPopulation, 0, 20, 28);
                lbe_population.get(i).sp_sprite.DrawSubImage(g, lbe_population.get(i).getXPos(), lbe_population.get(i).getYPos());
                lbe_maxPopulation.get(i).sp_sprite.setSubImage(20 * i_counterMaxPopulation, 0, 20, 28);
                lbe_maxPopulation.get(i).sp_sprite.DrawSubImage(g, lbe_maxPopulation.get(i).getXPos(), lbe_maxPopulation.get(i).getYPos());
            }// if
                    
        }// for
        be_slash.Draw(g);
    }// Draw
    
    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}// class ResourceBar
