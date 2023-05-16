package Patterns.Facade;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.concurrent.ExecutionException;

public class FacadeController {
    public Pane carPane;
    public ImageView carView;

    public ImageView redLight;
    public ImageView yellowLight;
    public ImageView greenLight;
    public Button playBtn;

    private boolean played = false;
    private boolean carOnScreen = true;
    private int curCarLoc = 5;

    private Facade facade;

    @FXML
    public void initialize() {
        FacadeCar facadeCar = new FacadeCar(curCarLoc, carOnScreen, carPane, carView);
        FacadeTrafficLight facadeTrafficLight = new FacadeTrafficLight(redLight, yellowLight, greenLight);
        facade = new Facade(facadeCar, facadeTrafficLight);
        Thread getItemsThread = new Thread(playTask);
        getItemsThread.setDaemon(true);
        getItemsThread.start();
    }

    Task<Void> playTask = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            if (played) {
                played = false;
                facade.stop();
                //stop program
            } else {
                played = true;
                facade.play();
                //start program
            }
            return null;
        }
    };

    public void startStop(ActionEvent actionEvent) {
        try {
            playTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}


