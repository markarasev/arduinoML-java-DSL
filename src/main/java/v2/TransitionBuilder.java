package v2;

import io.github.mosser.arduinoml.kernel.behavioral.State;
import io.github.mosser.arduinoml.kernel.behavioral.Transition;

/**
 * @author Marc Karassev
 */
public class TransitionBuilder extends v2.AbstractBuilder<Transition> {

    // Attributes

    private State from;

    // Constructors

    public TransitionBuilder() {
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

    // Getters and setters

    public State getFrom() {
        return from;
    }
}
