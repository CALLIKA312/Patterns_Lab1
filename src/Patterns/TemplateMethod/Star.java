package Patterns.TemplateMethod;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

class Star extends Shape {
    public Star(double x, double y, double dx, double dy, GraphicsContext gc) {
        super(x, y, dx, dy);
        this.x = x;
        this.y = y;
        this.radius = 5;
        this.color = Color.YELLOW;
        this.gc = gc;
    }


    private static final int NUM_POINTS = 10;
    private static final double INNER_RADIUS = 20.0;
    private static final double OUTER_RADIUS = 40.0;


    protected Node createShape() {
        double[] points = new double[NUM_POINTS * 2];
        for (int i = 0; i < NUM_POINTS; i++) {
            double angle = 2.0 * Math.PI * i / NUM_POINTS - Math.PI / 2.0;
            double radius = (i % 2 == 0) ? OUTER_RADIUS : INNER_RADIUS;
            points[i * 2] = x + radius * Math.cos(angle);
            points[i * 2 + 1] = y + radius * Math.sin(angle);
        }

        Polygon polygon = new Polygon(points);
        polygon.setFill(Color.YELLOW); // Set the fill color of the star
        return polygon;
    }


    private double x;
    private double y;
    private double radius;
    private Color color;
    private GraphicsContext gc;

    public void draw(GraphicsContext gc) {
        // Draw star shape
        double angle = Math.PI / 2; // Start angle
        double angleIncrement = 4 * Math.PI / 5; // Angle increment for each point of the star
        double x, y;
        gc.setFill(color);
        gc.setStroke(color);
        gc.setLineWidth(1.0);

        // Move to the first point of the star
        x = this.x + Math.cos(angle) * radius;
        y = this.y - Math.sin(angle) * radius;
        gc.beginPath();
        gc.moveTo(x, y);

        // Draw remaining points of the star
        for (int i = 0; i < 5; i++) {
            angle += angleIncrement;
            x = this.x + Math.cos(angle) * radius;
            y = this.y - Math.sin(angle) * radius;
            gc.lineTo(x, y);
        }

        gc.closePath();
        gc.fill();
    }

}

