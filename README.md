# ArduinoML Java DSL

This repository contains a Java implementation of the [ArduinoML DSL][1].

ArduinoML is a *domain specific language* (DSL) which aims to model simple applications running on Arduino boards. More information can be found at the link above.

This DSL is a group work done in the [IDM1 course][5] at [Polytech Nice Sophia][7].

## Table of contents

1. [The embedded DSL](#the-embedded-dsl)
  1. [V1](#v1)
  2. [V2](#v2)
  3. [V3](#v3)
2. [Test scenario](#test-scenario)
3. [Links](#links)

## The embedded DSL

For now, just the first version is completely implemented.

### V1

This version goal was to reproduce the Scala ArduinoML DSL written by **Sébastien Mosser** [here][2].

Since it transposes a Scala DSL to a Java one, it of course looses in usability and it does not really fit Java but it was a first try in order to learn how to write a Java DSL.

The following usage example of this DSL can be found [there][3].

```Java
BrickBuilder button = aSensor().named("button").boundToPin(9);
BrickBuilder led = anActuator().named("led").boundToPin(12);
StateBuilder on = state().named("on").executing(action().brick(led).value(SIGNAL.HIGH));
StateBuilder off = state().named("off").executing(action().brick(led).value(SIGNAL.LOW));
setInitial(off);
transitions(transition().from(off).to(on).when(condition().brick(button).value(SIGNAL.HIGH)),
              transition().from(on).to(off).when(condition().brick(button).value(SIGNAL.HIGH)));
exportToWiring();
```

Several upgrades are to be implemented in the next version such as:

* hiding the *Builder classes which is a leaky abstraction
* full method chaining, here chaining and imbrication are mixed like: `state().named("off").executing(action().brick(led).value(SIGNAL.LOW))`
* full method chaining², everything in a single instruction
* make sensors and actuators appear in action definitions instead of brick() calls
* and probably many others...

### V2

The goals of this second version are to implement the following upgrades:

* full method chaining, here chaining and imbrication are mixed like: `state().named("off").executing(action().brick(led).value(SIGNAL.LOW))`
* make sensors and actuators appear in action definitions instead of brick() calls

So it should look like this way:

```java
BrickBuilder button = aSensor().named("button").boundToPin(9);
BrickBuilder led = anActuator().named("led").boundToPin(12);
StateBuilder on = state().named("on")
		.action()
			.actuator(led).value(SIGNAL.HIGH)
		.endAction();
StateBuilder off = state().named("off")
		.action()
			.actuator(led).value(SIGNAL.LOW)
		.endAction();
setInitial(off);
transition()
	.from(off).to(on).condition()
		.sensor(button).value(SIGNAL.HIGH)
	.endCondition()
.endTransition();
transition()
	.from(on).to(off).condition()
		.sensor(button).value(SIGNAL.HIGH))
	.endCondition()
.endTransition();
```

### V3

The goals of the thrird version will be to implement the following upgrades:

* hiding the *Builder classes which is a leaky abstraction
* full method chaining², everything in a single instruction

So it should look somewhat this way:

```java
arduino()
    .sensor()
        .named("button")
        .boundToPin(9)
    .actuator()
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
```

## Test Scenario

The test scenario illustrates the following simple use case: 
>Alice owns a button (a sensor) and a LED (an actuator). She wants to connect these two things together in order to switch on the LED when the button is pushed, and switch it off when the button is pressed again.

The tests generated Arduino code and can be found in [this folder][4].

To run a test scenario, execute the following command:

`mvn exec:java -Dexec.mainClass="$TestClass" -Dexec.classpathScope="test"` where `$TestClass` should be replaced by `SwitchV1` or `SwitchV2`.

## Links
* [Sébastien Mosser's DSL course][5]
* [Sébastien Mosser's ArduinoML repository][6]

[1]: https://github.com/mosser/ArduinoML-kernel/tree/master/docs#arduinoml
[2]: https://github.com/mosser/ArduinoML-kernel/tree/master/embeddeds/scala#arduinoml-implementation-using-scala
[3]: /src/test/java/fr/polytech/unice/imd1/arduinoml/java_dsl/v1/SwitchV1.java
[4]: /src/test/java/fr/polytech/unice/imd1/arduinoml/java_dsl
[5]: http://www.i3s.unice.fr/~mosser/teaching/15_16/dsl/start
[6]: https://github.com/mosser/ArduinoML-kernel
[7]: http://www.polytechnice.fr/