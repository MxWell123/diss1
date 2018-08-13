/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diss1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davidecek
 */
public class Parkovisko extends SimulacneJadro {

    private int pocetParkovacichMiest; // pocet parkovacich miest na ulici
    private int pocetReplikacii;
    private Generator generator;
    private double vysledok1;
    private double vysledok2;
    private int pocitadlo;
    private double priebezny1;
    private double priebezny2;
    private double z;
    private GUI gui;
    private int[] strategia1;
    private int[] strategia2;
    private double presnost;
    private int counter;

    public Parkovisko(int pocetParkovacichMiest, int pocetReplikacii, GUI gui, double presnost) {
        super(pocetParkovacichMiest, pocetReplikacii, presnost);
        this.pocetParkovacichMiest = pocetParkovacichMiest;
        this.presnost = presnost;
        this.pocetReplikacii = pocetReplikacii;
        this.generator = new Generator();
        this.gui = gui;
        this.vysledok1 = 0;
        this.vysledok2 = 0;
        this.pocitadlo = 0;
        this.priebezny1 = 0;
        this.priebezny2 = 0;
        this.counter = 0;
        this.z = 0;
        strategia1 = new int[pocetParkovacichMiest + 1];
        strategia2 = new int[pocetParkovacichMiest + 1];
        for (int i = 0; i < pocetParkovacichMiest; i++) {
            strategia1[i] = 0;
            strategia2[i] = 0;
        }
    }

    public int[] dajVysledky(String strategia) {
        if (strategia.equals("jedna")) {
            return strategia1;
        } else {
            return strategia2;
        }
    }

    @Override
    public void vykonajReplikaciu() {
        int zaparkovane = generator.generujPocet(pocetParkovacichMiest);
        int[] parkovisko = generator.generujMiesta(pocetParkovacichMiest, zaparkovane);
        int pom = 0;
        int pom1 = 0;

        priebezny1 += pom = strategia1(parkovisko);
        if (pom < pocetParkovacichMiest) {
            strategia1[pom - 1]++;
        } else {
            strategia1[strategia1.length - 1]++;
        }

        priebezny2 += pom1 = strategia2(parkovisko);
        if (pom1 < pocetParkovacichMiest) {
            strategia2[pom1 - 1]++;
        } else {
            strategia2[strategia2.length - 1]++;
        }

        z = pocitadlo + 1.0;
        vysledok1 = priebezny1 / z;
        vysledok2 = priebezny2 / z;
        pocitadlo++;
        if (pocetReplikacii * (presnost / 100) < pocitadlo) {            
            if (counter > 1000) {
                try {
                    gui.vykresli(vysledok1, vysledok2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Parkovisko.class.getName()).log(Level.SEVERE, null, ex);
                }
                counter = 0;
            }
            counter++;            
        }
    }

    public int strategia1(int[] parkovisko) {
        for (int i = pocetParkovacichMiest - 1; i >= 0; i--) {
            if (parkovisko[i] == 0) {
                return i + 1;
            }
        }
        return 2 * pocetParkovacichMiest;
    }

    public int strategia2(int[] parkovisko) {
        int x = (int) Math.ceil(2 * pocetParkovacichMiest / 3.0);
        int pom = 0;
        for (int i = pocetParkovacichMiest - x - 1; i >= 0; i--) {
            if (parkovisko[i] == 0) {
                return i + 1;
            }
        }
        return 2 * pocetParkovacichMiest;
    }

    public double[] dajCelkoveVysledky() {
        double[] vysledky = new double[2];
        vysledky[0] = vysledok1;
        vysledky[1] = vysledok2;
        return vysledky;
    }

    @Override
    public void ukonciSimulaciu() {
        gui.ukonci();
    }

}
