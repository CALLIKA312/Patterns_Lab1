package Patterns.Facade;

public class Facade {

    private final FacadeCar facadeCar;
    private final FacadeTrafficLight facadeTrafficLight;
    private int time;

    public Facade(FacadeCar facadeCar, FacadeTrafficLight facadeTrafficLight) {
        this.facadeCar = facadeCar;
        this.facadeTrafficLight = facadeTrafficLight;
        time = 0;
    }


    private int savedTime = 0;

    public void stop() {
        savedTime = time;
        time = Integer.MAX_VALUE;
    }

    public void play() {
        if (savedTime != 0) time = savedTime;
        while (time < 120) {
            facadeTrafficLight.changeSignal();
            facadeCar.move(facadeTrafficLight.checkLight());
            time++;
            System.out.println("Текущее время: " + time);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
