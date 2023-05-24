package Patterns.TemplateMethod;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

class Square extends Shape {
    public Square(double x, double y, double dx, double dy) {
        super(x, y, dx, dy);
    }

    @Override
    protected Rectangle createShape() {
        return new Rectangle(10,10, Color.BLUE);
    }
}
