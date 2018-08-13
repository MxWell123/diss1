/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diss1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author davidecek
 */
public class Generator {

    public Generator() {
    }

    public int generujPocet(int pocetParkovacichMiest) {
        Random nasada = new Random();
        Random rnd1 = new Random(nasada.nextLong());
        return rnd1.nextInt(pocetParkovacichMiest) + 1;
    }

    public Random[] vygenerujRandom(int pocetParkovacichMiest) {
        Random nasada = new Random();
        Random[] random = new Random[pocetParkovacichMiest];
        for (int i = 0; i < pocetParkovacichMiest; i++) {
            random[i] = new Random(nasada.nextLong());
        }
        return random;
    }

    public int[] generujMiesta(int pocetParkovacichMiest, int pocetAut) {
        Random[] random = vygenerujRandom(pocetParkovacichMiest);
        int[] parkovisko = new int[pocetParkovacichMiest];
        ArrayList<Integer> index = new ArrayList<Integer>(pocetParkovacichMiest);

        for (int i = 0; i < pocetParkovacichMiest; i++) {
            index.add(i, i);
            parkovisko[i] = 0;
        }
        int pom = 0;
        int miesto = 0;
        for (int i = 0; i < pocetAut; i++) {
            pom = random[pocetParkovacichMiest - i - 1].nextInt(pocetParkovacichMiest - i);
            miesto = index.get(pom);
            parkovisko[miesto] = 1;
            index.remove(pom);
        }
        return parkovisko;
    }
}
