package v2;

import io.github.mosser.arduinoml.kernel.structural.SIGNAL;
import io.github.mosser.arduinoml.kernel.structural.Sensor;

/**
 * @author Marc Karassev
 */
public class ConditionBuilder extends v2.AbstractBuilder<v2.Condition> {

    // Constructors

    public ConditionBuilder() {
        object = new v2.Condition();
    }

    // DSL methods

    public ConditionBuilder brick(v2.BrickBuilder brickBuilder) {
        object.setSensor((Sensor) brickBuilder.getObject()); // C'est nul ici, risque de péter à l'exécution
        return this;
    }

    public ConditionBuilder value(SIGNAL signal) {
        object.setSignal(signal);
        return this;
    }
}
