package Patterns.TemplateMethod;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShapeAnimationApp extends Application {
    private List<Shape> shapes = new ArrayList<>();
    int shapeType = 0;
    GraphicsContext gc;

    public ShapeAnimationApp() {
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Group shapeGroup = new Group();
        shapeGroup.setStyle("-fx-fill: gray; -fx-stroke: black;");

        Button startButton = new Button("Пуск");
        startButton.setOnAction(event -> {
            Shape shape = getRandomShape();
            shapes.add(shape);
            shapeGroup.getChildren().add(shape.getShape());
        });

        ChoiceBox<String> shapeChoiceBox = new ChoiceBox<>();
        shapeChoiceBox.getItems().addAll("Ball", "Star", "Square");
        shapeChoiceBox.setValue("Ball");

        shapeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Ball":
                    shapeType = 0;
                    return;
                case "Square":
                    shapeType = 1;
                    return;
                case "Star":
                    shapeType = 2;
            }
        });

        HBox hBox1 = new HBox();
        hBox1.setPadding(new Insets(20));
        hBox1.getChildren().addAll(startButton, shapeChoiceBox);

        Button closeButton = new Button("Закрыть");
        closeButton.setOnAction(event -> primaryStage.close());

        Button startStopButton = new Button("Пауза");
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20));
        hBox.getChildren().addAll(closeButton, startStopButton);

        root.setTop(hBox1);
        root.setBottom(hBox);
        root.setCenter(shapeGroup);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Shape shape : shapes) shape.move();
            }
        };

        var ref = new Object() {
            boolean timerStatus = true;
        };
        startStopButton.setOnAction(actionEvent -> {
            if (ref.timerStatus) timer.stop();
            else timer.start();
            ref.timerStatus = !ref.timerStatus;
        });

        timer.start();
    }


    private Shape getRandomShape() {
        Random random = new Random();
        double x = 590;
        double y = random.nextDouble() * 390;
        double dx = random.nextDouble() * 5;
        double dy = random.nextDouble() * 5;
        return switch (shapeType) {
            case 0 -> new Ball(x, y, dx, dy);
            case 1 -> new Square(x, y, dx, dy);
            case 2 -> new Star(x, y, dx, dy, gc);
            default -> throw new IllegalStateException("Unexpected value: " + shapeType);
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
