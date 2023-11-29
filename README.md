# spring6-rest-mvc

Project Lombok Implementation of
@Data -> creation of POJO getters, setters, no args Constructor, canEqual, Equals, HashCode and toString implementation
@AllArgsContructor -> Creation of Dependency Injection using constructor based autowiring.
@Builder -> Creation of Builder Pattern for initialzing POJO using .builder() and followed by all setters to set the
required fields
@Slf4j -> creates Logger and wires up the logging properties from application.properties

We can use Delombok to add all the lombok generated code to the .java file itself.
This is good if we wanted to modify the implementation provided by lombok.

Spring Boot Dev Tools -> to implement live reload, recompiling the file using
build menu automatically triggers the server to do a live reload. On IntelliJ Utimate
some additional configurations can be done which is supported under spring configuration
On Update -> update classes and resources
On

@PostMapping(overrides the @RequestMapping with POST as requestMethod) Added -> Added @RequestBody for parsing the
request body sent by the user
in a Post Request
For the Service Layer Implementation we implemented a builder to add the new value into our existing map
Mockito Types of Mocks
Dummy - Object used just to get the code to compile
Fake - An object that has an implementation, but not production ready
Stub - An object with pre-defined answers to method calls
Mock - An Object with Pre-defined answers to method calls and has expectations of executions
Can Throw an exception if an unexpected invocation is detected
Spy - In Mockito Spies are Mock like wrappers around the actual object

Verify - Used to Verify Number of Method invocations
Argument Matcher -
Argument Captor -

Junit - > MockMVC -> Constroller under Test -> Mockito