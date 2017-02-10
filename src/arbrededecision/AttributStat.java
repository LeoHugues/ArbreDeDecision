/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbrededecision;

/**
 *
 * @author lehug
 */
public class AttributStat {
    private String name;
    private int nbValue0;
    private int nbValue1;
    private int nbValue2;

    public AttributStat(String name) {
        this.name = name;
    }
    
    public int getNbApparition() {
        return nbValue0 + nbValue1 + nbValue2;
    }
    
    public double getAntropieVal1 () {
        float pV0 = this.nbValue0 / this.getNbApparition();
        float pV1 = this.nbValue1 / this.getNbApparition();
        float pV2 = this.nbValue2 / this.getNbApparition();
        
        return -pV0 * this.log2(pV0) - pV1 * this.log2(pV1) - pV2 * this.log2(pV2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbValue0() {
        return nbValue0;
    }

    public void incrementNbValue0() {
        this.nbValue0++;
    }

    public int getNbValue1() {
        return nbValue1;
    }

    public void incrementNbValue1() {
        this.nbValue1++;
    }

    public int getNbValue2() {
        return nbValue2;
    }

    public void incrementNbValue2() {
        this.nbValue2++;
    }
    
    private double log2(float n)
    {
        return (Math.log(n) / Math.log(2));
    }
}
