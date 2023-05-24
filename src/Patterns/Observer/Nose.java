package Patterns.Observer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class Nose implements FaceObserver {
    private Circle nose;
    private boolean isRecolor;

    public Nose(Circle nose) {
        this.nose = nose;
        this.isRecolor = false;
    }

    public void changeColor() {
        isRecolor = !isRecolor;
        if (isRecolor) nose.setFill(Color.BLACK);
        else nose.setFill(Color.RED);
    }

    @Override
    public void update() {
        changeColor();
    }
}
