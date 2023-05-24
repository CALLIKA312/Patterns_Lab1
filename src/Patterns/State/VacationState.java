package Patterns.State;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class VacationState implements PersonState {
    @Override
    public void draw(Person person, AnchorPane container) {
        Circle head = new Circle(50, Color.LIGHTSKYBLUE);
        Line body = new Line(0, 0, 0, 100);
        Line leftArm = new Line(-50, 40, -100, 0);
        Line rightArm = new Line(50, 40, 100, 0);
        Line leftLeg = new Line(-20, 100, -40, 150);
        Line rightLeg = new Line(20, 100, 40, 150);
        Ellipse smile = new Ellipse(0, 30, 20, 15);
        smile.setFill(Color.YELLOW);
        Text zzz1 = new Text("У");
        zzz1.setFont(Font.font(15));
        zzz1.setLayoutX(20); zzz1.setLayoutY(-10);
        Text zzz2 = new Text("Р");
        zzz2.setFont(Font.font(20));
        zzz2.setLayoutX(40); zzz2.setLayoutY(-20);
        Text zzz3 = new Text("А");
        zzz3.setFont(Font.font(15));
        zzz3.setLayoutX(60); zzz3.setLayoutY(-30);
        container.getChildren().addAll(head, body, leftArm, rightArm, leftLeg, rightLeg, smile);
        container.getChildren().addAll(zzz1, zzz2, zzz3);
    }
}
