import io.github.mosser.arduinoml.kernel.behavioral.Action;
import io.github.mosser.arduinoml.kernel.behavioral.State;

/**
 * @author Marc Karassev
 */
public class StateBuilder extends AbstractBuilder<State> {

    // Constructors

    public StateBuilder() {
        object = new State();
    }

    // DSL methods

    public StateBuilder named(String name) {
        object.setName(name);
        return this;
    }

    public StateBuilder executing(ActionBuilder actionBuilder) {
        object.getActions().add(actionBuilder.getObject());
        return this;
    }
}
