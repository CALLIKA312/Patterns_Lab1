package Patterns.State;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

interface PersonState {
    void draw(Person person, AnchorPane container);
}
