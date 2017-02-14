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
        Exemple ex1 = arbre.getExemple1();
        Exemple ex2 = arbre.getExemple2();
        
        //ex1.printStats();
        ex2.getArbre();
        //ex2.printStats();
    }
    
    public Exemple getExemple1 () 
    {
        int[] refValues   = {1, 1, 1, 1, 0, 0, 0, 0, 0};
        int[] attr1Values = {0, 1, 0, 1, 0, 1, 0, 1, 0};
        ExampleLineAttribut ref = new ExampleLineAttribut("Reponse", refValues, "vrai", "faux", null);
        ExampleLineAttribut attr1 = new ExampleLineAttribut("Bleu",attr1Values, "clair", "fonce", null, ref);
        
        List<ExampleLineAttribut> exemples = new ArrayList<ExampleLineAttribut>();
        exemples.add(attr1);
        exemples.add(ref);
        
        Exemple exemple = new Exemple(exemples);
        
        
        return exemple;
    }
        
    public Exemple getExemple2 () 
    {
        int[] refValues   = {0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0};
        int[] attr1Values = {0, 0, 1, 2, 2, 2, 1, 0, 0, 2, 0, 1, 1, 2, 1};
        int[] attr2Values = {0, 0, 0, 1, 2, 2, 2, 1, 2, 1, 1, 1, 0, 1, 1};
        int[] attr3Values = {0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1};
        int[] attr4Values = {0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1};
        
        ExampleLineAttribut ref = new ExampleLineAttribut("jouer", refValues, "oui", "non", null);
        ExampleLineAttribut attr1 = new ExampleLineAttribut("Ciel",attr1Values, "soleil", "couvert", "pluie", ref);
        ExampleLineAttribut attr2 = new ExampleLineAttribut("Temperature",attr2Values, "chaud", "doux", "froid", ref);
        ExampleLineAttribut attr3 = new ExampleLineAttribut("humidite",attr3Values, "elevee", "normal", null, ref);
        ExampleLineAttribut attr4 = new ExampleLineAttribut("vent",attr4Values, "faible", "fort", null, ref);
        
        List<ExampleLineAttribut> exemples = new ArrayList<ExampleLineAttribut>();
        exemples.add(attr1);
        exemples.add(attr2);
        exemples.add(attr3);
        exemples.add(attr4);
        exemples.add(ref);
        
        Exemple exemple = new Exemple(exemples);
        
        
        return exemple;
    }
    
}
