package Patterns.Facade;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FacadeModel extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        String url = "/facadeView.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(url));
        primaryStage.setTitle("TrafficLights");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
