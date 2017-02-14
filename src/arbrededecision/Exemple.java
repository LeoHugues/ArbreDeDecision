/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbrededecision;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author lehug
 */
public class Exemple {
    private List<ExampleLineAttribut> exemples;

    public Exemple(List<ExampleLineAttribut> exemples) {
        this.exemples = exemples;
    }
    
    public void printStats() {
        Collections.sort(exemples);
        for (ExampleLineAttribut line : this.exemples) {
            if (!line.isIsReferenceLine()) {
                System.out.print(line.getAttributName() + " : "  + "\n    pertinence : " + line.getPertinence());
                System.out.print("\n    " + line.getNameValue0() +" : " + line.getAntropieOfValue(0));
                System.out.print("\n    " + line.getNameValue1() +" : " + line.getAntropieOfValue(1));
                System.out.print("\n    " + line.getNameValue2() +" : " + line.getAntropieOfValue(2) + "\n");
            }
        }
    }
    
    public void getArbre() {
        Collections.sort(exemples);
        this.printArbreNode(0);
    }
    
    public void printArbreNode(int node) {
        ExampleLineAttribut line = this.exemples.get(node);
        if (line.isIsReferenceLine()) {
            this.printArbreNode(node + 1);
        }
        this.printSpace(node);
        System.out.print(line.getAttributName() + " : ");
        if (line.getNbValue() == 2) {
            if (line.getAntropieOfValue(0) < line.getAntropieOfValue(1)) {
                this.printSpace(node + 1);
                System.out.print(line.getNameValue0()+" : Oui");
                this.printSpace(node + 1);
                System.out.print(line.getNameValue1() +" : Non");
            } else {
                this.printSpace(node + 1);
                System.out.print(line.getNameValue0() +" : Non");
                this.printSpace(node + 1);
                System.out.print(line.getNameValue1() +" : Oui");
            }
        } else {
            int minVal = line.getValueWithMinAntropie();
            int maxVal = line.getValueWithMaxAntropie();
            int medVal = this.getMediumValue(minVal, maxVal);

            this.printSpace(node + 1);
            System.out.print(line.getNameValue(minVal) +" : Oui");
            
            this.printSpace(node + 1);
            System.out.print(line.getNameValue(medVal) +" :");
            this.printArbreNode(node + 1);
            
            this.printSpace(node + 1);
            System.out.print(line.getNameValue(maxVal) +" :");
            this.printArbreNode(node + 2);
        }
    }
    
    private void printSpace(int nbSpace) {
        System.out.print("\n");
        for (int i = 0; i < nbSpace; i++) 
            System.out.print("    ");
    }
    
    private int getMediumValue(int minVal, int maxVal) {
        if (minVal == 0 && maxVal == 1 || maxVal == 0 && minVal == 1) {
            return 2;
        } else if (minVal == 0 && maxVal == 2 || maxVal == 0 && minVal == 2) {
            return 1;
        } else {
            return 0;
        }
    }
}
