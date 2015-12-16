package v2;

import io.github.mosser.arduinoml.kernel.behavioral.Action;
import io.github.mosser.arduinoml.kernel.structural.Actuator;
import io.github.mosser.arduinoml.kernel.structural.SIGNAL;

/**
 * @author Marc Karassev
 */
public class ActionBuilder extends v2.AbstractBuilder<Action> {

	private final StateBuilder parentStateBuilder;

	// Constructors
	public ActionBuilder(StateBuilder sb) {
        object = new Action();
		this.parentStateBuilder = sb;
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

	public StateBuilder endAction() {
		this.parentStateBuilder.getObject().getActions().add(this.getObject());
		return this.parentStateBuilder;
	}
}
