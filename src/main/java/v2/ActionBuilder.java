package v2;

import io.github.mosser.arduinoml.kernel.behavioral.Action;
import io.github.mosser.arduinoml.kernel.structural.Actuator;
import io.github.mosser.arduinoml.kernel.structural.SIGNAL;

/**
 * @author Marc Karassev
 */
public class ActionBuilder extends v2.AbstractBuilder<Action> {

    // Constructors

    public ActionBuilder() {
        object = new Action();
    }

    // DSL methods

    public ActionBuilder brick(v2.BrickBuilder brickBuilder) {
        object.setActuator((Actuator) brickBuilder.getObject()); // C'est nul ici, risque de péter à l'exécution
        return this;
    }

    public ActionBuilder value(SIGNAL signal) {
        object.setValue(signal);
        return this;
    }
}
