package Flight_Mgnt_System;

abstract class FlightMode {
    protected FlightControl context;

    abstract void handle();

    public FlightMode(FlightControl context) {
        this.context = context;
    }
}

class IdleMode extends FlightMode {

    public IdleMode(FlightControl context) {
        super(context);
    }

    @Override
    public void handle() {
        System.out.println("Idle Mode: Aircraft is on the ground. Preparing for takeoff...");
        context.setMode(new TakeoffMode(context));
    }
}

class TakeoffMode extends FlightMode {

    public TakeoffMode(FlightControl context) {
        super(context);
    }

    @Override
    public void handle() {
        System.out.println("Takeoff Mode: Aircraft is lifting off. Climbing to cruising altitude...");
        context.setMode(new CruiseMode(context));
    }
}

class CruiseMode extends FlightMode {

    public CruiseMode(FlightControl context) {
        super(context);
    }

    @Override
    public void handle() {
        System.out.println("Cruise Mode: Aircraft is flying at a steady altitude.");
        context.setMode(new LandingMode(context));
    }
}

class LandingMode extends FlightMode {

    public LandingMode(FlightControl context) {
        super(context);
    }

    @Override
    public void handle() {
        System.out.println("Landing Mode: Aircraft is descending to land. Returning to idle...");
        context.setMode(new IdleMode(context));
    }
}

public class FlightControl {
    private FlightMode currentMode;

    public FlightControl() {
        this.currentMode = new IdleMode(this);
    }

    public void setMode(FlightMode mode) {
        this.currentMode = mode;
    }

    public void request() {
        currentMode.handle();
    }
}
