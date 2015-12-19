import io.github.mosser.arduinoml.kernel.structural.SIGNAL;
import v1.ArduinoML;
import v1.BrickBuilder;
import v1.StateBuilder;

/**
 * @author Marc Karassev
 */
public class SwitchV1 extends ArduinoML {

    @Override
    public void build() {
        // reprise du DSL Scala de Mosser sans les declare
        BrickBuilder button = aSensor().named("button").boundToPin(9);
        BrickBuilder led = anActuator().named("led").boundToPin(12);
        StateBuilder on = state().named("on").executing(action().brick(led).value(SIGNAL.HIGH));
        StateBuilder off = state().named("off").executing(action().brick(led).value(SIGNAL.LOW)); // sale mais respecte le principe de ce DSL
        setInitial(off);
        transitions(transition().from(off).to(on).when(condition().brick(button).value(SIGNAL.HIGH)),
                      transition().from(on).to(off).when(condition().brick(button).value(SIGNAL.HIGH)));
        // on peut aussi imaginer des méthodes states() et bricks()
        exportToWiring();


    }

    public static void main(String[] args) {
        //Switch switchArduino = arduinoML();
        SwitchV1 switchV1Arduino = new SwitchV1();
        switchV1Arduino.build();

        // reprise du DSL Scala de Mosser avec sensor et actuator aulieu de brick et virer executing et les parenthèses de when
        /*
        Brick button = aSensor().named("button").boundToPin(9).build();
        Actuator led = anActuator().named("led").boundToPin(12);
        State on = state().named("on").action().actuator(led).value(HIGH);
        State off = state().named("off").action().actuator(led).value(LOW);
        off.isInitial();
        transitions(from(off).to(on).when().sensor(button).value(HIGH),
                    from(on).to(off).when(button, HIGH));
        // on peut aussi imaginer des méthodes states(), actuators(), sensors() voire bricks() (?)
        exportToWiring();
        */

        // full chaînage, dur à coder ? -> rajouter des end()
        /*
        arduinoML()
            .brick(sensor)
                .named("button")
                .boundToPin(9)
            .brick(actuator)
                .named("led")
                .boundToPin(12)
            .state()
                .named("on")
                .action()
                    .actuator("led")
                    .value(HIGH)
            .state()
                .named("off")
                    .actuator("led")
                    .value(LOW)
            .initialState("off")
            .transition()
                .from("off")
                .to("on")
                .when()
                    .sensor("button")
                    .value(HIGH)
            .transition()
                .from("on")
                .to("off")
                .when()
                    .sensor("button")
                    .value(HIGH);
        */
        //arduinoML.declare()
    }
}
