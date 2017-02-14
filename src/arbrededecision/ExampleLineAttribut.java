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
public class ExampleLineAttribut implements Comparable<ExampleLineAttribut>{
    
    private String  attributName;
    private int[]   listValue;
    private String  nameValue0;
    private String  nameValue1;
    private String  nameValue2;
    private boolean isReferenceLine;
    private ExampleLineAttribut referenceLine;
    private double pertinence;
    private int nbValue;

    public ExampleLineAttribut(String attributName, int[] listValue, String nameValue0, String nameValue1, String nameValue2) {
        this.attributName    = attributName;
        this.listValue       = listValue;
        this.nameValue0      = nameValue0;
        this.nameValue1      = nameValue1;
        this.nameValue2      = nameValue2;
        this.isReferenceLine = true;
    }
    
    public ExampleLineAttribut(String attributName, int[] listValue, String nameValue0, String nameValue1, String nameValue2, ExampleLineAttribut referenceLine) {
        this.attributName    = attributName;
        this.listValue       = listValue;
        this.nameValue0      = nameValue0;
        this.nameValue1      = nameValue1;
        this.nameValue2      = nameValue2;
        this.referenceLine   = referenceLine;
        this.isReferenceLine = false;
        if (nameValue2 == null)
            this.nbValue = 2;
        else
            this.nbValue = 3;
        this.setPertinence();
    }
        
    public double getRootAntropie () {
        
        double pOui = (double) this.referenceLine.getNbValue(1) / this.getNbExemple();
        double pNon = (double) this.referenceLine.getNbValue(0) / this.getNbExemple();
        
        return -pOui * this.log2(pOui) - pNon * this.log2(pNon);
    }
    
    public void setPertinence () {
        // je stock le résultat des opérations (effectué dans les fonctions) pour les analyser avec le debugger.
        double entropieValue0 = this.getAntropieOfValue(0);
        double entropieValue1 = this.getAntropieOfValue(1);
        
        double ratioV0 = (double) getNbValue(0) / this.getNbExemple();
        double ratioV1 = (double) getNbValue(1) / this.getNbExemple();
        
        double v0Pert = (double) ratioV0 * entropieValue0;
        double v1Pert = (double) ratioV1 * entropieValue1;
        
        double pertinence = (double) this.getRootAntropie();
        pertinence -= v0Pert;
        pertinence -= v1Pert;
        if (this.getNbValue(2) > 0) {
            double entropieValue2 = this.getAntropieOfValue(2);
            double ratioV2 = (double) getNbValue(0) / this.getNbExemple();
            double v2Pert = (double) ratioV2 * entropieValue2;

            pertinence -= v2Pert;
        }
        
        this.pertinence = pertinence;
    }
    
    public double getAntropieOfValue (int theValue) {
        double pOui = this.getpOuiRatioOfValue(theValue);
        double pNon = this.getpNonRatioOfValue(theValue);
        
                
      /*  if (pNon != 0) {
            antropie -= (double) pNon * this.log2(pNon);
        } 
        
        if (pOui != 0) {
            antropie -= (double) pOui * this.log2(pOui);
        }
        
        return antropie;*/
        
        return (double) - pNon * this.log2(pNon) - pOui * this.log2(pOui);
    }
    
    private double getpOuiRatioOfValue(int theValue) {
        int nbOui = 0;
        for (int i = 0; i < this.getNbExemple(); i++) 
        {
            if (this.listValue[i] == theValue && this.referenceLine.listValue[i] == 1) {
                nbOui++;
            }
        }
        
        return (double) nbOui / (double) this.getNbValue(theValue);
    }
    
    private double getpNonRatioOfValue(int theValue) {
        int nbNon = 0;
        for (int i = 0; i < this.getNbExemple(); i++) 
        {
            if (this.listValue[i] == theValue && this.referenceLine.listValue[i] == 0) {
                nbNon++;
            }
        }
        
        return (double) nbNon / this.getNbValue(theValue);
    }
   
    
    public int getValueWithMinAntropie() {
        if (this.getAntropieOfValue(0) < this.getAntropieOfValue(1) && this.getAntropieOfValue(0) < this.getAntropieOfValue(2)) {
            return 0;
        } else if (this.getAntropieOfValue(1) < this.getAntropieOfValue(0) && this.getAntropieOfValue(1) < this.getAntropieOfValue(2)) {
            return 1;
        } else {
            return 2;
        }
    }
    
    public int getValueWithMaxAntropie() {
        if (this.getAntropieOfValue(0) > this.getAntropieOfValue(1) && this.getAntropieOfValue(0) > this.getAntropieOfValue(2)) {
            return 0;
        } else if (this.getAntropieOfValue(1) > this.getAntropieOfValue(0) && this.getAntropieOfValue(1) > this.getAntropieOfValue(2)) {
            return 1;
        } else {
            return 2;
        }
    }
    
    
    public int getNbExemple() {
        return this.listValue.length;
    }
    
    public int getNbValue(int theValue) {
        int i = 0;
        for (int value : this.listValue) {
            if (value == theValue) {
                i++;
            }
        }
        
        return i;
    }

    public String getAttributName() {
        return attributName;
    }

    public void setAttributName(String attributName) {
        this.attributName = attributName;
    }

    public int[] getListValue() {
        return listValue;
    }

    public void setListValue(int[] listValue) {
        this.listValue = listValue;
    }

    public String getNameValue0() {
        return nameValue0;
    }

    public void setNameValue0(String nameValue0) {
        this.nameValue0 = nameValue0;
    }

    public String getNameValue1() {
        return nameValue1;
    }

    public void setNameValue1(String nameValue1) {
        this.nameValue1 = nameValue1;
    }

    public String getNameValue2() {
        return nameValue2;
    }

    public void setNameValue2(String nameValue2) {
        this.nameValue2 = nameValue2;
    }
    
    public String getNameValue(int theValue) {
        if (theValue == 0) {
            return this.getNameValue0();
        } else if (theValue == 1) {
            return this.getNameValue1();
        } else {
            return this.getNameValue2();
        }
    }

    public boolean isIsReferenceLine() {
        return isReferenceLine;
    }

    public void setIsReferenceLine(boolean isReferenceLine) {
        this.isReferenceLine = isReferenceLine;
    }

    public ExampleLineAttribut getReferenceLine() {
        return referenceLine;
    }

    public void setReferenceLine(ExampleLineAttribut referenceLine) {
        this.referenceLine = referenceLine;
    }

    public int getNbValue() {
        return nbValue;
    }

    public void setNbValue(int nbValue) {
        this.nbValue = nbValue;
    }

    public double getPertinence() {
        return pertinence;
    }
    
    private double log2(double n)
    {
        return (Math.log(n) / Math.log(2));
    }

    @Override
    public int compareTo(ExampleLineAttribut t) {
        if (this.getPertinence() > t.getPertinence()) {
            return -1;
        } else {
            return 1;
        }
    }
}
