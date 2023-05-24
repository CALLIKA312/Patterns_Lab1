package Patterns.State;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class PersonApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Circle head = new Circle();
        Line body = new Line();
        Line leftArm = new Line();
        Line rightArm = new Line();
        Line leftLeg = new Line();
        Line rightLeg = new Line();

        VBox buttons = new VBox();
        Button semesterButton = new Button("Семестр");
        Button vacationButton = new Button("Каникулы");
        Button examButton = new Button("Сессия");
        buttons.getChildren().addAll(semesterButton, vacationButton, examButton);

        AnchorPane mainFrame = new AnchorPane();
        mainFrame.getChildren().addAll(head, body, leftArm, rightArm, leftLeg, rightLeg);

        VBox root = new VBox();
        root.getChildren().add(mainFrame);
        root.getChildren().add(buttons);
        root.setLayoutX(150);
        root.setLayoutY(150);

        Person person = new Person(mainFrame);
        semesterButton.setOnAction(event -> person.setState(new SemesterState()));
        vacationButton.setOnAction(event -> person.setState(new VacationState()));
        examButton.setOnAction(event -> person.setState(new ExamState()));
        person.draw(mainFrame);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Person App");
        primaryStage.show();
    }

    public static void setAnchor(Node node, Double top, Double right, Double bottom, Double left) {
        if (top != null) {
            AnchorPane.setTopAnchor(node, top);
        }
        if (right != null) {
            AnchorPane.setRightAnchor(node, right);
        }
        if (bottom != null) {
            AnchorPane.setBottomAnchor(node, bottom);
        }
        if (left != null) {
            AnchorPane.setLeftAnchor(node, left);
        }
    }

}
