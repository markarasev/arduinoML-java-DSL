import io.github.mosser.arduinoml.kernel.behavioral.State;
import io.github.mosser.arduinoml.kernel.behavioral.Transition;

/**
 * @author Marc Karassev
 */
public class TransitionBuilder extends AbstractBuilder<Transition> {

    // Attributes

    private State from;

    // Constructors

    public TransitionBuilder() {
        object = new Transition();
    }

    // DSL methods

    public TransitionBuilder from(StateBuilder stateBuilder) {
        from = stateBuilder.getObject();
        return this;
    }

    public TransitionBuilder to(StateBuilder stateBuilder) {
        object.setNext(stateBuilder.getObject());
        return this;
    }

    public TransitionBuilder when(ConditionBuilder conditionBuilder) {
        object.setSensor(conditionBuilder.getObject().getSensor());
        object.setValue(conditionBuilder.getObject().getSignal());
        return this;
    }

    // Getters and setters

    public State getFrom() {
        return from;
    }
}
