/*
 * class of the resources
 * 
 */
package iron.heart;

/**
 *
 * @author setes
 */
public class Resources {
    
    // attributes of the class
    //
    String s_playerID;
    int i_ore;
    int i_gas;
    int i_population;
    int i_maxPopulation = 200;
    
    // default-constructor
    //
    public Resources(){
        s_playerID = "0";
        i_ore = 100;
        i_gas = 50;
        i_population = 0;
    }// default-constructor
    
    // constructor
    //
    public Resources(String ID, int ore, int gas){
        s_playerID = ID;
        i_ore = ore;
        i_gas = gas;
        i_population = 0;
    }// Resources
    
    // enaugh resources for a build or a unit
    //
    public boolean enaughResources(int ore, int gas, int population){
        if(i_ore - ore >= 0 && i_gas - gas >= 0 && i_population + population <= 
                i_maxPopulation)
            return true;
        else
            return false;
    }// enaughResources
    
    // add resources
    //
    public void addOre(int ore){i_ore += ore;}
    public void addGas(int gas){i_gas += gas;}
    public void addPopulation(int pop){i_population += pop;}
    
    // getter
    //
    public int getOre(){return i_ore;}
    public int getGas(){return i_gas;}
    public int getPopulation(){return i_population;}
    public int getmaxPopulation(){return i_maxPopulation;}
}// class Resources
