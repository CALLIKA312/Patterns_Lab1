package Patterns.Facade;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class FacadeCar {
    private int curPosition;
    private boolean onScreen;
    private Pane carPane;
    private ImageView carView;

    public FacadeCar(int curPosition, boolean onScreen, Pane carPane, ImageView carView) {
        this.curPosition = curPosition;
        this.onScreen = onScreen;
        this.carPane = carPane;
        this.carView = carView;
    }

    public void move(boolean canMove) {
        if (onScreen) {
            switch (curPosition) {
                case 5 -> {
                    carPane.setLayoutX(390);
                    curPosition -= 1;
                }
                case 4 -> {
                    carPane.setLayoutX(300);
                    curPosition -= 1;
                }
                case 3 -> {
                    if (canMove) {
                        carPane.setLayoutX(150);
                        curPosition -= 1;
                    }
                }
                case 2 -> {
                    carPane.setLayoutX(0);
                    curPosition -= 1;
                }
                case 1 -> {
                    carPane.setLayoutX(480);
                    carView.visibleProperty().setValue(false);
                    onScreen = false;
                    curPosition = 5;
                }
            }
        } else {
            int carNum = new Random().nextInt(5);
            switch (carNum) {
                case 1 -> carView.setImage(new Image("/pics/cars/car.png"));
                case 2 -> carView.setImage(new Image("/pics/cars/car1.png"));
                case 3 -> carView.setImage(new Image("/pics/cars/car2.png"));
                case 4 -> carView.setImage(new Image("/pics/cars/car3.png"));
            }
            carView.visibleProperty().setValue(true);
            onScreen = true;
        }
        System.out.println("car curPos: " + curPosition + " carColor: " + carView.getImage().getUrl()
                .substring("file:/C:/Users/CALLIKA/IdeaProjects/Patterns_Lab1/out/production/Patterns_Lab1/pics/cars/".length()));
    }
}
