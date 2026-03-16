package Lab_7;

import java.util.*;

interface Observer {
    public void update(String s);
}

interface Subject {
    public void attach(Observer o);

    public void detach(Observer o);

    public void notifyObserver();
}

/**
 * Store a phone number, digit-by-digit
 */
class PhoneModel implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private List<Integer> digits = new ArrayList<>();

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        String dig = "";
        for (int i : digits)
            dig += Integer.toString(i);
        for (Observer o : observers) {
            o.update(dig);
        }
    }

    public void addDigit(int newDigit) {
        digits.add(newDigit);
        notifyObserver();
    }

    public List<Integer> getDigits() {
        return digits;
    }

}

/**
 * Mimic the data input ability of a physical phone's keypad;
 * however, here we're just sending it fake digits.
 */
class KeyPad {

    private final PhoneModel model;

    public KeyPad(PhoneModel model) {
        this.model = model;
    }

    public void simulateKeyPresses(int numKeyPresses) {
        final int MAX_DIGIT = 10;
        Random rnd = new Random();
        for (int i = 0; i < numKeyPresses; i++) {
            int newDigit = rnd.nextInt(MAX_DIGIT);
            System.out.println("Pressing: " + newDigit);
            model.addDigit(newDigit);
        }
    }

}

abstract class UIO implements Observer {
    protected String digits;

    @Override
    public void update(String s) {
        digits = s;
        display();
    }

    abstract public void display();
}

class DigitUIO extends UIO {
    @Override
    public void display() {
        System.out.println(digits.charAt(digits.length() - 1));
    }
}

class DialingUIO extends UIO {
    @Override
    public void display() {
        if (digits.length() != 10)
            return;
        System.out.println("Now dialing " + digits + "...");
    }
}

/**
 * Prints things out to the screen, when needed
 * Printing to the screen:
 * System.out.println("hello");
 */
class Screen implements Observer {
    private final PhoneModel model;

    @Override
    public void update(String s) {
        System.out.println(s);
    }

    public Screen(PhoneModel model) {
        this.model = model;
        Observer o1 = new DigitUIO();
        Observer o2 = new DialingUIO();
        model.attach(o1);
        model.attach(o2);
    }
}

class Q3 {
    public static void main(String[] args) {
        final int NUM_DIGITS = 10;

        // Build the object graph
        PhoneModel model = new PhoneModel();
        Screen screen = new Screen(model);
        KeyPad keyPad = new KeyPad(model);

        // Run the program
        keyPad.simulateKeyPresses(NUM_DIGITS);
    }
}
