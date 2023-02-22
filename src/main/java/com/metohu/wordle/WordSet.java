package com.metohu.wordle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe che contiene un insieme di parole prese da un file.
 */
public class WordSet {

    private final List<String> words = new ArrayList<>(); // Lista delle parole

    /**
     * Costruttore della classe WordSet
     * @param url url del file contenente le parole
     * @throws FileNotFoundException se il file non viene trovato
     */
    public WordSet(URL url) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8)));
        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine().toUpperCase());
        }
    }

    /**
     * Restituisce una parola casuale
     * @return parola casuale
     */
    public String getRandom() {
        return words.get(new Random().nextInt(words.size()));
    }

    /**
     * Controlla se la parola è presente nel set
     * @param word parola da controllare
     * @return true se la parola è presente altrimenti false
     */
    public boolean contains(String word) {
        return words.contains(word);
    }
}

