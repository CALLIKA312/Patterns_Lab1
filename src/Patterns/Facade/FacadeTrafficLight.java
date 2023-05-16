package Patterns.Facade;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FacadeTrafficLight {

    private final ImageView redLight;
    private final ImageView yellowLight;
    private final ImageView greenLight;

    private int redLightTimer = 1;
    private int yellowLightTimer = 0;
    private int greenLightTimer = 0;

    public ImageView getRedLight() {
        return redLight;
    }

    public FacadeTrafficLight(ImageView redLight, ImageView yellowLight, ImageView greenLight) {
        this.redLight = redLight;
        this.yellowLight = yellowLight;
        this.greenLight = greenLight;
    }

    public boolean checkLight() {
        System.out.println("Горят светофоры: " +
                "красный: " + redLightTimer +
                " желтый: " + yellowLightTimer +
                " зелёный: " + greenLightTimer);
        return redLightTimer == 0;
    }

    public void changeSignal() {
        if (greenLightTimer == 7) {
            greenLightTimer = 0;
            greenLight.setImage(new Image("/pics/trafficLight/trafficLightGreenFaded.png"));
            redLightTimer = 1;
            redLight.setImage(new Image("/pics/trafficLight/trafficLightRedBright.png"));
        } else if (redLightTimer == 7 && yellowLightTimer == 2) {
            redLightTimer = 0;
            redLight.setImage(new Image("/pics/trafficLight/trafficLightRedFaded.png"));
            yellowLightTimer = 0;
            yellowLight.setImage(new Image("/pics/trafficLight/trafficLightYellowFaded.png"));
            greenLightTimer = 1;
            greenLight.setImage(new Image("/pics/trafficLight/trafficLightGreenBright.png"));
        } else if (redLightTimer >= 5) {
            redLightTimer++;
            yellowLightTimer++;
            yellowLight.setImage(new Image("/pics/trafficLight/trafficLightYellowBright.png"));
        } else if (greenLightTimer > 0) {
            greenLightTimer++;
        } else if (redLightTimer > 0) {
            redLightTimer++;
        }
    }
}
