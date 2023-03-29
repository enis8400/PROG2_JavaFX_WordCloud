package ch.zhaw.prog2.wordcloud;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ResultWindowController {

    public void setResults(String resultText) {
        results.setText(resultText);
    }

    @FXML
    private TextArea results;

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) results.getScene().getWindow();
        stage.close();
    }

}
