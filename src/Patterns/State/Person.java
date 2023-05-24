package Patterns.State;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.Arrays;

class Person {
    private PersonState state;
    private AnchorPane stackPane;

    public Person(AnchorPane root) {
        state = new DefaultState();
        stackPane = root;
    }

    public void setState(PersonState state) {
        this.state = state;
        redraw();
    }

    public void draw(AnchorPane container) {
        state.draw(this, container);
    }

    private void redraw() {
        AnchorPane container = (AnchorPane) stackPane;
        container.getChildren().clear();
        draw(container);
    }
}
