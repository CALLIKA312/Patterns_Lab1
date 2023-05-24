package Patterns.Observer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class FaceApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Face App");
        primaryStage.setScene(scene);

        Circle eye1 = new Circle(100, 120, 20, Color.WHITE);
        Circle eye2 = new Circle(220, 120, 20, Color.WHITE);
        Circle nose = new Circle(160, 180, 10, Color.BLACK);
        Ellipse mouth = new Ellipse(160, 240, 40, 40);
        mouth.setFill(Color.rgb(0, 138, 7));
        Circle face = new Circle(200, 200, 150);
        face.setStyle("-fx-fill: yellow; -fx-stroke: black;");


        root.getChildren().addAll(face, eye1, eye2, nose, mouth);

        Eye eyeObserver1 = new Eye(eye1);
        Eye eyeObserver2 = new Eye(eye2);
        Nose noseObserver = new Nose(nose);
        Mouth mouthObserver = new Mouth(mouth, mouth.getRadiusX(), mouth.getRadiusY());


        eye1.setOnMouseClicked(event -> {
            eyeObserver1.update();
        });

        eye2.setOnMouseClicked(event -> {
            eyeObserver2.update();
        });

        nose.setOnMouseClicked(event -> {
            noseObserver.update();
        });

        mouth.setOnMouseClicked(event -> {
            mouthObserver.update();
        });


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
