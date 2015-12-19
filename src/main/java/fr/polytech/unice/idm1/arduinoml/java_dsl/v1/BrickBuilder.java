package v1;

import io.github.mosser.arduinoml.kernel.structural.Actuator;
import io.github.mosser.arduinoml.kernel.structural.Brick;
import io.github.mosser.arduinoml.kernel.structural.Sensor;

/**
 * @author Marc Karassev
 */
public class BrickBuilder extends AbstractBuilder<Brick> {

    // Constructors

    public BrickBuilder(BrickKind kind) {
        switch (kind) {
            case ACTUATOR:
                object = new Actuator();
                break;
            case SENSOR:
                object = new Sensor();
                break;
            default:
        }
    }

    // DSL methods

    public BrickBuilder named(String name) {
        object.setName(name);
        return this;
    }

    public BrickBuilder boundToPin(int pin) {
        object.setPin(pin);
        return this;
    }
}
