package com.metohu.wordle;

import javafx.scene.control.Label;

import java.io.FileNotFoundException;

/**
 * Cervello del gioco.
 */
public class WordleApp {

    private final WordSet wordSet; // Set di parole
    private final String wordToGuess; // Parola da indovinare
    private final WordleTable wordleTable; // Griglia di gioco

    public WordleApp(Label[][] labels) throws FileNotFoundException {
        this.wordSet = new WordSet(getClass().getResource("words.txt")); // Inizializza il set di parole
        this.wordleTable = new WordleTable(labels); // Inizializza la griglia di gioco
        this.wordToGuess = this.wordSet.getRandom().toUpperCase(); // Inizializza la parola da indovinare
        System.out.println(this.wordToGuess); // Stampa la parola da indovinare, per debug e test
    }

    /**
     * Restituisce la label della casella alla quale corrisponde la lettera passata come parametro, se non è possibile
     * aggiungere la lettera restituisce null.
     *
     * @param lettera
     * @return label della casella o null
     */
    public Label addLetter(String lettera) {
        Casella casella = wordleTable.getNextCasella(); // Ottiene la prossima casella libera nella quale viene inserita la lettera
        if (casella == null) {
            return null;
        }
        casella.setLettera(lettera); // Imposta la lettera nella casella
        return casella.getLabel(); // Restituisce la label della casella
    }

    /**
     * Rimuove la lettera dalla casella corrente e restituisce la label della casella.
     *
     * @return label della casella rimossa o null
     */
    public Label removeLetter() {
        Casella casella = wordleTable.getPreviousCasella();
        if (casella == null) {
            return null;
        }
        casella.setLettera(""); // Rimuove la lettera dalla casella
        return casella.getLabel(); // Restituisce la label della casella
    }

    /**
     * Tenta di indovinare la parola.
     *
     * @return array di caselle se la parola è possibile, null se non in lista
     */
    public Casella[] tenta() {
        if (wordSet.contains(wordleTable.getGuess())) {
            return wordleTable.testa(wordToGuess);
        }
        return null;
    }

    /**
     * Se la parola è stata indovinata o se sono stati fatti 6 tentativi ritorna true, altrimenti false.
     *
     * @return true se la partita è finita altrimenti false
     */
    public boolean isFinished() {
        return wordleTable.getGuess(true).equals(wordToGuess) || wordleTable.getTentativi() == 6;
    }

    /**
     * Se la parola è stata indovinata ritorna true, altrimenti false.
     *
     * @return true se la parola è stata indovinata altrimenti false
     */
    public boolean isWinner() {
        return wordleTable.getGuess(true).equals(wordToGuess);
    }

    /**
     * Getter della parola da indovinare.
     * @return parola da indovinare
     */
    public String getParola() {
        return wordToGuess;
    }
}
