package ch.zhaw.prog2.wordcloud;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

import static javafx.application.Application.launch;

public class Main {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        mainWindow(primaryStage);
    }

    private void mainWindow(Stage primaryStage) {
        try {
            URL load = getClass().getResource("MainWindow.fxml");
            FXMLLoader loader = new FXMLLoader(load);
            Pane rootNode = loader.load();

            loader.getController();
        } catch (Exception e) {
            System.out.println("Couldn't load FXML resource: " + e.getMessage());
        }
    }

}
