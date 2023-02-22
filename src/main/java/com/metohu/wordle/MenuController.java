package com.metohu.wordle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller per la schermata di menu e per la schermata di riepilogo fine partita.
 */
public class MenuController {
    private Stage primaryStage; // Stage principale dell'applicazione
    private Stage stageToClose; // Stage da chiudere quando si fa una nuova partita, ovvero quello della partita precedente
    @FXML
    private Label lblResult; // Label che mostra il risultato della partita
    @FXML
    private Label lblParola; // Label che mostra la parola da indovinare



    @FXML
    protected void onStart() throws IOException { // Metodo chiamato al primo avvio dal menu
        setMainView();
    }

    @FXML
    protected void onStartEnding() throws IOException { // Metodo chiamato alla fine della partita dal menu riepilogo
        setMainView();
        this.stageToClose.close(); // Una volta creato il nuovo stage chiude quello della partita precedente
    }

    /**
     * Imposta la schermata per una nuova partita. Fa quello che fa il metodo main di MainApp ma con la schermata di gioco.
     */
    private void setMainView() throws IOException {
        /*
         * Questo metodo essenzialmente crea una nuova scena e la carica con il file fxml della schermata di gioco, poi
         * passa al controller la propria scena appena creata e lo stage della finestra corrente, scambiandosi di fatto
         * con la schermata del gioco.
         */
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GameController controller = fxmlLoader.getController(); // Ottengo il controller della schermata di gioco
        controller.setPrimaryStage(primaryStage); // Passo lo stage principale al controller
        controller.setScene(scene); // Passo la scena al controller
        this.primaryStage.setScene(scene); // Imposto allo stage attuale la scena di gioco
    }

    @FXML
    protected void onExit() {
        this.primaryStage.close();
    }

    @FXML
    protected void onExitEnding() {
        this.primaryStage.close();
        this.stageToClose.close();
    }

    /**
     * Setta lo stage principale
     *
     * @param primaryStage stage principale
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void setResult(boolean winCondition) {
        lblResult.setText(winCondition ? "Hai vinto!" : "Hai perso!");
    }


    public void setStageToClose(Stage stageToClose) {
        this.stageToClose = stageToClose;
    }

    public void setParola(String parola) {
        lblParola.setText(parola);
    }
}
