/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diss1;

/**
 *
 * @author davidecek
 */
public class Program {

    private Parkovisko parkovisko;

    public Program() {

    }

    public void spustSimulaciu(int pocetParkovacichMiest, int pocetReplikacii, GUI gui, double presnost) {
        parkovisko = new Parkovisko(pocetParkovacichMiest, pocetReplikacii, gui, presnost);
        parkovisko.spusti();
    }
    
    public int[] dajVysledky(String strategia){
        return parkovisko.dajVysledky(strategia);
    }
    
    public void ukonciSimulaciu(){
        parkovisko.ukonci();
    }  

    public double[] dajCelkoveVysledky() {
       return parkovisko.dajCelkoveVysledky();
    }

}
