package Patterns.State;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class SemesterState implements PersonState {
    @Override
    public void draw(Person person, AnchorPane container) {
        Circle head = new Circle(50, Color.LIGHTSKYBLUE);
        Line body = new Line(0, 0, 0, 100);
        Line leftArm = new Line(-50, 40, -100, 0);
        Line rightArm = new Line(50, 40, 100, 0);
        Line leftLeg = new Line(-20, 100, -40, 150);
        Line rightLeg = new Line(20, 100, 40, 150);
        //Circle zzz1 = new Circle(10, Color.GRAY);
        Text zzz1 = new Text("Z");
        zzz1.setFont(Font.font(15));
        zzz1.setLayoutX(20); zzz1.setLayoutY(-10);
        //Circle zzz2 = new Circle(15, Color.GRAY);
        Text zzz2 = new Text("Z");
        zzz2.setFont(Font.font(20));
        zzz2.setLayoutX(40); zzz2.setLayoutY(-20);
        //Circle zzz3 = new Circle(20, Color.GRAY);
        Text zzz3 = new Text("Z");
        zzz3.setFont(Font.font(15));
        zzz3.setLayoutX(60); zzz3.setLayoutY(-30);
        container.getChildren().addAll(head, body, leftArm, rightArm, leftLeg, rightLeg);
        container.getChildren().addAll(zzz1, zzz2, zzz3);
    }
}
