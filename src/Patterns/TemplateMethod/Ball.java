package Patterns.TemplateMethod;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class Ball extends Shape {
    public Ball(double x, double y, double dx, double dy) {
        super(x, y, dx, dy);
    }

    @Override
    protected Circle createShape() {
        return new Circle(10, Color.RED);
    }
}
