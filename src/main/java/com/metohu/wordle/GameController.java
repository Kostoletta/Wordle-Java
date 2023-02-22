package com.metohu.wordle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GameController {

    private final Label[][] labels = new Label[6][5]; // Matrice di Label che compongono la griglia di gioco
    @FXML
    private VBox vBox;
    @FXML
    private Label hintLbl; // Label che può mostrare vari suggerimenti
    private Stage primaryStage;
    private WordleApp wordleApp; // Riferimento all'applicazione principale
    private boolean isGameFinished = false; // Flag che indica se il gioco è finito

    @FXML
    protected void initialize() throws FileNotFoundException {
        initializeLabels();
        wordleApp = new WordleApp(labels);
    }

    /**
     * Inizializza le label della griglia in una matrice di Label.
     */
    private void initializeLabels() {
        /*
         * Prende le label accedendo ai figli di hBox a loro volta figli di vBox.
         * Vengono filtrate le label in base al nome per evitare di prendere anche le eventuali label non volute per poi
         * essere inserite dentro un hashmap con chiave il loro id.
         * Il nome delle label è formato da "lbl" + riga + colonna (es. lbl00, lbl01, lbl02, lbl03, lbl04, lbl10, lbl11).
         * Il keyset dell'hashmap viene poi ordinato e le label vengono inserite nella matrice rispettando la loro posizione
         * a matrice della griglia.
         */
        HashMap<String, Label> labelMap = new HashMap<>();
        vBox.getChildren().forEach(node -> {
            if (node instanceof HBox hBox) {
                hBox.getChildren().forEach(node1 -> {
                    if (node1 instanceof Label label) {
                        String id = label.getId();
                        if (id.startsWith("lbl")) {
                            labelMap.put(id, label);
                        }
                    }
                });
            }
        });
        ArrayList<String> keys = new ArrayList<>(labelMap.keySet());
        Collections.sort(keys);
        int i = 0;
        int j = 0;
        for (String key : keys) {
            labels[i][j] = labelMap.get(key);
            j++;
            if (j == 5) {
                j = 0;
                i++;
            }
        }
    }

    /**
     * Setta il riferimento alla finestra principale
     *
     * @param primaryStage finestra principale
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Setta la scena principale con un event handler da tastiera
     *
     * @param scene scena principale
     */
    public void setScene(Scene scene) {
        scene.setOnKeyPressed(e -> {
            try {
                sortLetter(String.valueOf(e.getCode()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @FXML
    protected void onEnter() throws IOException {
        enterWord();
    }

    @FXML
    protected void onBackspace() {
        removeLetter();
    }

    @FXML
    protected void onBtnQ() {
        addLetter("Q");
    }

    @FXML
    protected void onBtnW() {
        addLetter("W");
    }

    @FXML
    protected void onBtnE() {
        addLetter("E");
    }

    @FXML
    protected void onBtnR() {
        addLetter("R");
    }

    @FXML
    protected void onBtnT() {
        addLetter("T");
    }

    @FXML
    protected void onBtnY() {
        addLetter("Y");
    }

    @FXML
    protected void onBtnU() {
        addLetter("U");
    }

    @FXML
    protected void onBtnI() {
        addLetter("I");
    }

    @FXML
    protected void onBtnO() {
        addLetter("O");
    }

    @FXML
    protected void onBtnP() {
        addLetter("P");
    }

    @FXML
    protected void onBtnA() {
        addLetter("A");
    }

    @FXML
    protected void onBtnS() {
        addLetter("S");
    }

    @FXML
    protected void onBtnD() {
        addLetter("D");
    }

    @FXML
    protected void onBtnF() {
        addLetter("F");
    }

    @FXML
    protected void onBtnG() {
        addLetter("G");
    }

    @FXML
    protected void onBtnH() {
        addLetter("H");
    }

    @FXML
    protected void onBtnJ() {
        addLetter("J");
    }

    @FXML
    protected void onBtnK() {
        addLetter("K");
    }

    @FXML
    protected void onBtnL() {
        addLetter("L");
    }

    @FXML
    protected void onBtnZ() {
        addLetter("Z");
    }

    @FXML
    protected void onBtnX() {
        addLetter("X");
    }

    @FXML
    protected void onBtnC() {
        addLetter("C");
    }

    @FXML
    protected void onBtnV() {
        addLetter("V");
    }

    @FXML
    protected void onBtnB() {
        addLetter("B");
    }

    @FXML
    protected void onBtnN() {
        addLetter("N");
    }

    @FXML
    protected void onBtnM() {
        addLetter("M");
    }

    /**
     * Smista gli input dell'utente da tastiera
     */
    private void sortLetter(String letter) throws IOException {
        String acceptedLetters = "QWERTYUIOPASDFGHJKLZXCVBNM";
        if (acceptedLetters.contains(letter.toUpperCase())) {
            addLetter(letter.toUpperCase());
        } else if (letter.equals("ENTER")) {
            enterWord();
        } else if (letter.equals("BACK_SPACE")) {
            removeLetter();
        }
    }

    /**
     * Rimuove la lettera dalla parola da indovinare
     */
    private void removeLetter() {
        if (isGameFinished)
            return;
        System.out.println("remove letter");
        hintLbl.setText("");
        Label label = wordleApp.removeLetter();
        if (label != null) {
            label.setText("");
        }
    }

    /**
     * Aggiunge una lettera alla parola da indovinare
     *
     * @param letter lettera da aggiungere
     */
    private void addLetter(String letter) {
        if (isGameFinished)
            return; // se il gioco è finito non faccio niente
        System.out.println(letter); // per motivi di debug ed "estetici"
        Label label = wordleApp.addLetter(letter);
        if (label != null) { // se la label è null vuol dire che la parola è finita
            label.setText(letter);
        }
    }

    /**
     * Invia la parola già scritta dall'utente alla fase di controllo
     */
    private void enterWord() throws IOException {
        if (isGameFinished)
            return; // se il gioco è finito non faccio niente
        System.out.println("enter word"); // per motivi di debug ed "estetici"
        Casella[] caselle = wordleApp.tenta();

        if (caselle != null) {
            for (int i = 0; i < 5; i++) {
                caselle[i].getLabel().setStyle(String.format("-fx-background-color: %s; -fx-background-radius: 7;", caselle[i].getColore()));
            }
            if (!isGameFinished && wordleApp.isFinished()) {
                isGameFinished = true;
                showResult(wordleApp.isWinner());
            }
        } else if (!isGameFinished) {
            hintLbl.setText("La parola non è in lista oppure troppo corta");
            System.out.println("Parola non in lista o troppo corta!");
        }
    }

    /**
     * Mostra il risultato della partita
     *
     * @param winCondition condizione di vittoria
     */
    private void showResult(boolean winCondition) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("end-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Wordle!");
        stage.setScene(scene);
        stage.setResizable(false);
        MenuController controller = fxmlLoader.getController();
        controller.setPrimaryStage(stage);
        controller.setStageToClose(this.primaryStage);
        controller.setResult(winCondition);
        controller.setParola(wordleApp.getParola());
        stage.show();
    }
}
