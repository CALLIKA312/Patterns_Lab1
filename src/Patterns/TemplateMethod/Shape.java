package Patterns.TemplateMethod;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

abstract class Shape {
    private double x;
    private double y;
    private double dx;
    private double dy;
    private Node circle;

    public Shape(double x, double y, double dx, double dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.circle = createShape();
    }

    protected abstract Node createShape();

    public Node getShape() {
        return circle;
    }

    public void move() {
//        System.out.println("Name: " + this.getClass() +  " cur coordinates" + "\n" +
//                "x: " + x + "y: " + y);

        x += dx;
        y += dy;

        if (x <= 0 || x >= 600) {
            System.out.println("Name: " + this.getClass() +  " cur coordinates" + "\n" +
                    "x: " + x + "y: " + y +"\n"+
                    "change direction on x: " + dx);
            dx *= -1;
        }

        if (y <= 0 || y >= 400) {
            System.out.println("Name: " + this.getClass() +  " cur coordinates" + "\n" +
                    "x: " + x + "y: " + y +"\n"+
                    "change direction on y: " + dy);
            dy *= -1;
        }

        circle.setTranslateX(x);
        circle.setTranslateY(y);
    }
}
