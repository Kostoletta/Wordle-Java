module com.metohu.wordle {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.metohu.wordle to javafx.fxml;
    exports com.metohu.wordle;
}