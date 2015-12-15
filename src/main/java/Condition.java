import io.github.mosser.arduinoml.kernel.structural.SIGNAL;
import io.github.mosser.arduinoml.kernel.structural.Sensor;

/**
 * @author Marc Karassev
 */
public class Condition {

    // Attributes

    private Sensor sensor;
    private SIGNAL signal;

    // Getters and setters

    public Sensor getSensor() {
        return sensor;
    }

    public SIGNAL getSignal() {
        return signal;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public void setSignal(SIGNAL signal) {
        this.signal = signal;
    }
}
