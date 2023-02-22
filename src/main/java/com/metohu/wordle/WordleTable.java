package com.metohu.wordle;

import javafx.scene.control.Label;

/**
 * Classe che rappresenta l'intera griglia del gioco.
 */
public class WordleTable {
    private final Casella[][] caselle; // Griglia di caselle
    private int riga = 0; // Riga corrente
    private int colonna = 0; // Colonna corrente


    public WordleTable(Label[][] labels) { // Ad ogni caseella viene associata una label
        caselle = new Casella[6][5];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                caselle[i][j] = new Casella(labels[i][j]);
            }
        }
    }

    /**
     * Ritorna la prima casella libera, se esiste, altrimenti null. Inoltre incrementa la colonna.
     *
     * @return La prima casella libera, se esiste, altrimenti null
     */
    public Casella getNextCasella() {
        // prima restituisci la casella, poi incrementa la colonna
        return colonna == 5 || riga == 6 ? null : caselle[riga][colonna++];
    }

    /**
     * Ritorna la prima casella occupata, se esiste, altrimenti null. Inoltre decrementa la colonna.
     *
     * @return La prima casella occupata, se esiste, altrimenti null
     */
    public Casella getPreviousCasella() {
        // prima decrementa la colonna, poi restituisci la casella
        return colonna == 0 ? null : caselle[riga][--colonna];
    }

    /**
     * Costruisce la parola a partire dalle caselle occupate.
     * @return La parola costruita o null se non completa
     */
    public String getGuess() {
        if (colonna == 5) {
            StringBuilder tentativo = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                tentativo.append(caselle[riga][i].getLettera());
            }
            return tentativo.toString(); // restituisce la parola costruita
        }
        return null; // se non sono state inserite 5 lettere
    }

    /**
     * Costruisce la parola a partire dalle caselle occupate della riga precedente.
     * @param getPrevious
     * @return
     */
    public String getGuess(boolean getPrevious) {
        if(getPrevious){
            StringBuilder tentativo = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                tentativo.append(caselle[riga-1][i].getLettera());
            }
            return tentativo.toString();
        }
        return null;
    }

    /**
     * Testa la parola passata come parametro con la parola da indovinare.
     *
     * @param parola Parola da indovinare
     * @return Array di caselle se la parola è corretta, null altrimenti
     */
    public Casella[] testa(String parola) {
        String tentativo = getGuess();
        if (tentativo != null) {
            for (int i = 0; i < 5; i++) {
                if (tentativo.charAt(i) == parola.charAt(i)) {
                    caselle[riga][i].setColore(Colori.VERDE);
                } else if (parola.contains(String.format("%c", tentativo.charAt(i)))) {
                    caselle[riga][i].setColore(Colori.GIALLO);
                } else {
                    caselle[riga][i].setColore(Colori.VIOLETTO);
                }
            }
            colonna = 0; // resetta la colonna
            return caselle[riga++]; // restituisce la riga "colorata" e incrementa la riga per la prossima parola
        }
        return null; // se non sono state inserite 5 lettere
    }

    public int getTentativi() {
        return riga;
    }
}
