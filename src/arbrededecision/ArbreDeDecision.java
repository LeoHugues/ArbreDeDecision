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
public class ArbreDeDecision {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ArbreDeDecision arbre = new ArbreDeDecision();
        List<Attribut> attributs = arbre.getExemple1();
        
        Exemple exemple = new Exemple(attributs);
        exemple.printStats();
    }
    
    public void getArbreDeDecision(List<Attribut> exemples) 
    {
        
    }
    
    public double getRootAntropie(List<Attribut> exemples) 
    {
        
        int nbOui       = 0;
        int nbNon       = 0;
        float pOui      = 0;
        float pNon      = 0;
        int nbExemples  = exemples.size();
        
        for (Attribut exemple : exemples) {
            if (exemple.getValue() == 1) {
                nbOui++;
            } else {
                nbNon++;
            }
        }
        
        pOui = (float) nbOui / nbExemples;
        pNon = (float) nbNon / nbExemples;
        
        return -pOui * this.log2(pOui) - pNon * this.log2(pNon);
    }
    
    public double getNodeAntropie(List<Attribut> exemples) 
    {
        return 0;
    }
    
    public Map<String, Integer> getGetAttributStats (List<Attribut> exemples) 
    {
        Map<String, Integer> attributStat = new HashMap<String, Integer>();
        
        boolean firstIteration = true;
        for (Attribut exemple : exemples) {  
            if (firstIteration) {
                attributStat.put(exemple.getName(), exemple.getValue());
                firstIteration = false;
            } else {
                boolean attributNameExist = false;
                for (Map.Entry<String, Integer> entry : attributStat.entrySet()) {
                    if (entry.getKey() == exemple.getName()) {
                        entry.setValue(entry.getValue() + 1);
                        attributNameExist = true;
                    }
                }
                
                if (!attributNameExist) {
                    attributStat.put(exemple.getName(), exemple.getValue());
                }
            }
        }
        
        return attributStat;
    }
    
    private double log2(float n)
    {
        return (Math.log(n) / Math.log(2));
    }
    
    public List<Attribut> getExemple1 () 
    {
        List<Attribut> exemples = new ArrayList<Attribut>();
        exemples.add(new Attribut("bleu", 1));
        exemples.add(new Attribut("bleu", 0));
        exemples.add(new Attribut("bleu", 1));
        exemples.add(new Attribut("bleu", 0));
        exemples.add(new Attribut("bleu", 1));
        exemples.add(new Attribut("bleu", 0));
        exemples.add(new Attribut("bleu", 1));
        exemples.add(new Attribut("bleu", 0));
        exemples.add(new Attribut("bleu", 1));
        
        return exemples;
    }
    
}
