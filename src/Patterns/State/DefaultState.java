package Patterns.State;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

class DefaultState implements PersonState {
    @Override
    public void draw(Person person, AnchorPane container) {
        Circle head = new Circle(50, Color.LIGHTSKYBLUE);
        Line body = new Line(0, 0, 0, 100);
        Line leftArm = new Line(-50, 40, 0, 0);
        Line rightArm = new Line(50, 40, 0, 0);
        Line leftLeg = new Line(-20, 100, -40, 150);
        Line rightLeg = new Line(20, 100, 40, 150);

        container.getChildren().addAll(head, body, leftArm, rightArm, leftLeg, rightLeg);

    }
}
