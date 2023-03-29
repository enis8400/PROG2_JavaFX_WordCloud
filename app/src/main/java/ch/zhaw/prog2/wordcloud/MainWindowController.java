package ch.zhaw.prog2.wordcloud;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainWindowController {

    public CheckMenuItem menuInitialAmount;
    public CheckMenuItem menuReturnRate;
    public CheckMenuItem menuAnnualCosts;
    public CheckMenuItem menuNumberOfYears;
    public TextField initialAmount;
    public TextField returnRateInPercent;
    public TextField annualCost;
    public TextField numberOfYears;
    public TextArea resultHistory;
    public Button closeButton;
    private final ValueHandler valueHandler = new ValueHandler();

    private record Result(String result, boolean clearNeeded, Color borderColor) {

    }

    public void showHelp() {
        String helperText = """
            Enter valid values to
            - Initial amount (> 0)
            - Return in % (can be +/- or 0)
            - Annual Costs (> 0)
            - Number of years (> 0)

            Calculate displays the annual balance development!""";
        this.showResult(helperText, Color.BLUE);
    }

    public void handleCalculate(ActionEvent actionEvent) {
        Result result = calculateResult();
        showResult(result.result, result.borderColor);
    }

    private Result calculateResult() {
        valueHandler.validateAndSetValues(initialAmount.getText(), returnRateInPercent.getText(), annualCost.getText(), numberOfYears.getText());
        String result = valueHandler.getResult();
        valueHandler.setResult(result);
        Color borderColor = switch (valueHandler.getValuesState()) {
            case UNDEFINED -> Color.TRANSPARENT;
            case OK -> Color.GREEN;
            case ERROR -> Color.RED;
        };
        return new Result(result, true, borderColor);
    }

    private void showResult(String text, Color borderColor) {
        resultHistory.setText(text);
        resultHistory.setBorder(new Border(new BorderStroke(borderColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.5))));
    }

    public void clearValuesSelected() {
        if (menuInitialAmount.isSelected()) {
            initialAmount.clear();
        }
        if (menuReturnRate.isSelected()) {
            returnRateInPercent.clear();
        }
        if (menuAnnualCosts.isSelected()) {
            annualCost.clear();
        }
        if (menuNumberOfYears.isSelected()) {
            numberOfYears.clear();
        }
    }

    public void clearResult() {
        resultHistory.clear();
        valueHandler.clearResult();
        resultHistory.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.5))));
    }

    public void handleClose(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
