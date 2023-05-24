package Patterns.Observer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class Eye implements FaceObserver {
    private Circle eye;
    private boolean isOpen;

    public Eye(Circle eye) {
        this.eye = eye;
        this.isOpen = true;
    }

    public void toggle() {
        isOpen = !isOpen;
        eye.setFill(isOpen ? Color.WHITE : Color.BLACK);
    }

    @Override
    public void update() {
        toggle();
    }
}
