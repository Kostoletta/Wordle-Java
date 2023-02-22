package com.metohu.wordle;

import javafx.scene.control.Label;

/**
 * Classe che rappresenta una casella della griglia di gioco.
 * Contiene l'oggetto Label che rappresenta la casella, il suo valore il colore.
 */
public class Casella {
    private final Label label; // Label della casella
    private String lettera; // Lettera della casella
    private String colore; // Colore della casella

    public Casella(Label label, String lettera, String colore) { // Costruttore con parametri
        this.lettera = lettera;
        this.colore = colore;
        this.label = label;
    }

    public Casella(Label label) { // Costruttore senza parametri
        this.lettera = "";
        this.colore = "";
        this.label = label;
    }

    public String getLettera() {
        return lettera;
    }

    public void setLettera(String lettera) {
        this.lettera = lettera;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public Label getLabel() {
        return label;
    }
}
