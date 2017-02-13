/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbrededecision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lehug
 */
public class Exemple {
    private List<Attribut> exemples;
    private List<AttributStat> stats;

    public Exemple(List<Attribut> exemples) {
        this.exemples = exemples;
        this.stats = new ArrayList<AttributStat>();
        this.initAttributStats();
    }
    
    public void initAttributStats () 
    {        
        boolean firstIteration = true;
        for (Attribut exemple : this.exemples) {  
            if (firstIteration) {
                AttributStat stat = new AttributStat(exemple.getName());
                switch (exemple.getValue()) {
                    case 0 : stat.incrementNbValue0();break;
                    case 1 : stat.incrementNbValue1();break;
                    case 2 : stat.incrementNbValue2();break;
                }
                this.stats.add(stat);
                firstIteration = false;
            } else {
                boolean attributNameExist = false;
                for (AttributStat stat : this.stats) {
                    if (stat.getName() == exemple.getName()) {
                        switch (exemple.getValue()) {
                            case 0 : stat.incrementNbValue0();break;
                            case 1 : stat.incrementNbValue1();break;
                            case 2 : stat.incrementNbValue2();break;
                        }
                        attributNameExist = true;
                    }
                }
                
                if (!attributNameExist) {
                    AttributStat stat = new AttributStat(exemple.getName());
                    switch (exemple.getValue()) {
                        case 0 : stat.incrementNbValue0();break;
                        case 1 : stat.incrementNbValue1();break;
                        case 2 : stat.incrementNbValue2();break;
                    }
                    this.stats.add(stat);
                }
            }
        }
    }
    
    public void printStats() {
        for (AttributStat stat : this.stats) {
            System.out.print(stat.getName() + " : "  + "\n    valeur 0 : " + stat.getNbValue0() + "\n    valeur 1 : " + stat.getNbValue1() + "\n    valeur 2 : " + stat.getNbValue2() + "\n");
            System.out.print(stat.getRootAntropie());
        }
    }

    public List<Attribut> getExemples() {
        return exemples;
    }

    public void setExemples(List<Attribut> exemples) {
        this.exemples = exemples;
    }

    public List<AttributStat> getStats() {
        return stats;
    }

    public void setStats(List<AttributStat> stats) {
        this.stats = stats;
    }
}
