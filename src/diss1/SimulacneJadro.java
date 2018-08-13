/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diss1;

import javax.swing.SwingWorker;

/**
 *
 * @author davidecek
 */
public class SimulacneJadro {

    private int pocetReplikacii;
    private int pocetParkovacichMiest;
    private boolean ukonci;
    private boolean ukoncenieSimulacie;

    public SimulacneJadro(int pocetParkovacichMiest, int pocetReplikacii, double presnost) {
        this.pocetReplikacii = pocetReplikacii;
        this.pocetParkovacichMiest = pocetParkovacichMiest;
        ukonci = false;
        ukoncenieSimulacie = false;
    }

    public void spusti() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i < pocetReplikacii; i++) {
//                    Thread.sleep(10);
                    if (ukonci) {
                        break;
                    } else {
                        vykonajReplikaciu();
                    }
                }
                ukonciSimulaciu();
                return null;
            }
        };
        worker.execute();
    }

    public void ukonciSimulaciu() {

    }

    public void ukonci() {
        ukonci = true;
    }

    public void vykonajReplikaciu() {

    }

}
