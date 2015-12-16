package v2;

import io.github.mosser.arduinoml.kernel.App;
import io.github.mosser.arduinoml.kernel.behavioral.State;
import io.github.mosser.arduinoml.kernel.generator.ToWiring;
import io.github.mosser.arduinoml.kernel.structural.Brick;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Marc Karassev
 */
public abstract class ArduinoML {

    // Attributes

    private App app;
    private v2.BrickBuilder currentBrickBuilder;
    private StateBuilder currentStateBuilder;

    // Constructors

    public ArduinoML() {
        app = new App();
        app.setBricks(new ArrayList<>());
        app.setStates(new ArrayList<>());
        currentBrickBuilder = null;
        currentStateBuilder = null;
    }

    // Abstract methods

    public abstract void build();

    // DSL methods

    public BrickBuilder aSensor() {
        flush();
        currentBrickBuilder = new BrickBuilder(BrickKind.SENSOR);
        return currentBrickBuilder;
    }

    public BrickBuilder anActuator() {
        flush();
        currentBrickBuilder = new BrickBuilder(v2.BrickKind.ACTUATOR);
        return currentBrickBuilder;
    }

    public StateBuilder state() {
        flush();
        currentStateBuilder = new StateBuilder();
        return currentStateBuilder;
    }

    public v2.ActionBuilder action() {
        return new v2.ActionBuilder(currentStateBuilder);
    }

    public void setInitial(StateBuilder stateBuilder) {
        flush();
        app.setInitial(getStateByName(stateBuilder.getObject().getName()));
    }

    public void transitions(TransitionBuilder... transitionBuilders) {
        State state;

        for (TransitionBuilder transitionBuilder: transitionBuilders) {
            state = getStateByName(transitionBuilder.getFrom().getName());
            if (state != null) {
                state.setTransition(transitionBuilder.getObject());
            }
        }
    }

    public TransitionBuilder transition() {
        return new TransitionBuilder(this);
    }

	void onTransitionEnd(TransitionBuilder transitionBuilder) {
		State state = getStateByName(transitionBuilder.getFrom().getName());
		if (state != null) {
			state.setTransition(transitionBuilder.getObject());
		}
	}

    public v2.ConditionBuilder condition() {
        return new v2.ConditionBuilder();
    }

    public void exportToWiring() {
        flush();
        ToWiring codeGen = new ToWiring();
        app.accept(codeGen);
        System.out.println(codeGen.getResult());
    }

    // Private methods

    private void flush() {
        if (currentBrickBuilder != null) {
            List<Brick> bricks = app.getBricks();

            bricks.add(currentBrickBuilder.getObject());
            app.setBricks(bricks);
            currentBrickBuilder = null;
        }
        if (currentStateBuilder != null) {
            List<State> states = app.getStates();

            states.add(currentStateBuilder.getObject());
            app.setStates(states);
            currentStateBuilder = null;
        }
    }

    private State getStateByName(String name) {
        for (State state: app.getStates()) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }
}
