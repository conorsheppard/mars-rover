### Description
Mars rover application that takes in commands and moves one or more robots around Mars.

Full exercise instructions [here](exercise-description.md).

### Requirements
For best experience, a Java 18 and Maven installation are advised - alternatively you can build and run with Docker.

### Build & Run With Docker

**Please note:** The execution of this command will take a minute or so to complete as it needs to copy the
entire repository into the Docker image, and once the container starts, Maven will need to download all dependencies.<br/>
**_This is not best practice_** but overcomes the issue of not having Java and Maven pre-installed 
(usually Maven is used to build the jar file, and then solely the jar is copied into the container image).<br/>
Once complete, you will see the test output and then the application will start, allowing you to enter commands.

Make sure you have Docker installed and the Docker daemon is running, then execute:<br/>
```console
$ make docker-run
```

<img src="mars-rover.gif" style="text-align: centre;" width="80%" alt="mars rover">

### Build & Run With Maven 
Make sure you have the Maven command line tool installed: `brew install maven`<br/>
Then execute:<br/>
```console
$ make run
```

### Tests

With Docker, the tests are executed before the application starts
```console
$ make docker-run
```

With Maven
```console
$ make test
```

### *Design & Implementation*
The main components of the system are:<br/>

**RoverService:** This class is responsible for reading the input, executing the commands and printing the output.<br/>
The `executeRoverCommands()` method uses Java's functional paradigms to stream over the rovers, filter out the lost 
rovers, and execute each command for every given rover
(when a rover becomes lost, it immediately breaks and moves on to the next rover).

**RoverController:** This class encapsulates the execution of Rover commands. This makes controlling the rover separate 
from the rover itself, much like in real life if you were to use a separate controller to control a machine remotely.<br/>
It exposes one method, `executeCommands()` to execute all commands for any given Rover passed as a parameter.<br/>
Due to all of the commands being supplied up-front by the user via the console,
I found this was the cleanest way to implement the solution.

**InputReader:** Reads from `System.in` to consume user input from the console.<br/>
Its various methods catch any exceptions thrown during validation and parsing of input and pass those exceptions back
to the service class.

**InputValidator:** This class uses a regex to validate the user input. It checks the format of the input is correct but
allows for leading and trailing white spaces so as not to be too strict.<br/>
Throws `RoverInputException` in the case of erroneous input.

**Parser/RoverParser/GridParser:** `Parser` is an interface with a `parse()` method which is implemented by `RoverParser`
(to parse the rover input) and `GridParser` (to parse the grid input).<br/> 
It also contains a static method, `checkInputBounds()`, used by both implementations
to check the bounds of the coordinates entered by the user and make sure they are within the bounds of a 32-bit integer
(this was not a requirement but I thought it would be a sensible upper limit).<br/>
Throws `IntegerOverflowException` in the case of erroneous input.

**Rover:** The Rover representation which includes private fields `Orientation` (N, S, E, W), `List`**&lt;**`Command`**&gt;**
(a list of `Command` objects `TURN_LEFT`, `TURN_RIGHT`, `MOVE_FORWARD`), `Position` (x and y coordinates) & `boolean isLost`

**Grid:** The Grid representation, simply *height* and *width* integer values.<br/>

<ins>**Testing**</ins>

**InputReaderTest:** Tests the `InputReader` class by feeding input to the standard input. Checks if an error was
successfully generated (for incorrect input) or no error (for correct input).

**InputValidatorTest:** Tests the `InputValidator` class by passing an input string for validation. Checks if an error was
successfully generated (for incorrect input) or no error (for correct input).

**ParserTest:** Tests the `GridParser` & `RoverParser` classes by passing an input string to be parsed. Empty string (no error)
for a correct input and error message for a successful generation of error for incorrect input.

**RoverControllerTest:** Test cases for the rover being moved around the grid and cases for rover going off-grid.<br/>
No test case for a rover which _starts_ off-grid because this is checked outside of the `RoverController`
(when the input is read from the console in `readRoverCommands()`).

**RoverServiceTest:** Tests the `RoverService`'s `execute()` method by generating user input and feeding it to the standard
input (`executeConsoleInputAndGetStream()`).
