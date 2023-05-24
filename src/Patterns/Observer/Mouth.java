package Patterns.Observer;

import javafx.scene.shape.Ellipse;

class Mouth implements FaceObserver {
    private Ellipse mouth;
    private boolean isSmiling;
    private final double radiusX;
    private final double radiusY;


    public Mouth(Ellipse mouth, double radiusX, double radiusY) {
        this.mouth = mouth;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.isSmiling = false;
    }

    public void toggle() {
        isSmiling = !isSmiling;
        mouth.setRadiusX(isSmiling ? radiusX * 2 : radiusX);
        mouth.setRadiusY(isSmiling ? radiusY * 0.5 : radiusY);
    }

    @Override
    public void update() {
        toggle();
    }
}
