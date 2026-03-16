package Lab_7;

class Controller {
    private static Controller singleton;
    private String name;

    private Controller(String name) {
        this.name = name;
    }

    public static Controller getController(String name) {
        if (singleton == null)
            singleton = new Controller(name);
        return singleton;
    }

    public void give() {
        System.out.println("\t" + name + " Controller releasing.");
    }
}

class MoistureSensor {
    private Controller waterController;

    public MoistureSensor() {
        waterController = Controller.getController("MS");
    }

    public void requestWater() {
        waterController.give();
    }
}

class WeatherMonitor {
    private Controller waterController;

    public WeatherMonitor() {
        waterController = Controller.getController("WM");
    }

    public void requestWater() {
        waterController.give();
    }
}

class IrrigationScheduler {
    private Controller waterController;

    public IrrigationScheduler() {
        waterController = Controller.getController("IS");
    }

    public void requestWater() {
        waterController.give();
    }
}

public class Q2 {
    public static void main(String[] args) {
        MoistureSensor sensor = new MoistureSensor();
        WeatherMonitor weather = new WeatherMonitor();
        IrrigationScheduler scheduler = new IrrigationScheduler();
        System.out.println("MoistureSensor requesting water supply");
        sensor.requestWater();
        System.out.println("WeatherMonitor requesting water supply");
        weather.requestWater();
        System.out.println("IrrigationScheduler requesting water supply");
        scheduler.requestWater();
    }
}
