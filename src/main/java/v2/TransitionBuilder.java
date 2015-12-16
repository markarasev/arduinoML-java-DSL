package v2;

import io.github.mosser.arduinoml.kernel.behavioral.State;
import io.github.mosser.arduinoml.kernel.behavioral.Transition;

/**
 * @author Marc Karassev
 */
public class TransitionBuilder extends v2.AbstractBuilder<Transition> {
	private final ArduinoML parentArduinoML;

	// Attributes

    private State from;

    // Constructors

    public TransitionBuilder(ArduinoML arduinoML) {
		this.parentArduinoML = arduinoML;	// TODO: maybe there should be an explicitly named Root element?
        object = new Transition();
    }

    // DSL methods

    public TransitionBuilder from(v2.StateBuilder stateBuilder) {
        from = stateBuilder.getObject();
        return this;
    }

    public TransitionBuilder to(v2.StateBuilder stateBuilder) {
        object.setNext(stateBuilder.getObject());
        return this;
    }

    public TransitionBuilder when(v2.ConditionBuilder conditionBuilder) {
        object.setSensor(conditionBuilder.getObject().getSensor());
        object.setValue(conditionBuilder.getObject().getSignal());
        return this;
    }

	public ArduinoML endTransition() {
		this.parentArduinoML.onTransitionEnd(this);
		return this.parentArduinoML;
	}

    // Getters and setters

    public State getFrom() {
        return from;
    }
}
