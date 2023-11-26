# spring6-rest-mvc

Project Lombok Implementation of 
@Data -> creation of POJO getters, setters, no args Constructor, canEqual, Equals, HashCode and toString implementation
@AllArgsContructor -> Creation of Dependency Injection using constructor based autowiring.
@Builder -> Creation of Builder Pattern for initialzing POJO using .builder() and followed by all setters to set the required fields
@Slf4j -> creates Logger and wires up the logging properties from application.properties


We can use Delombok to add all the lombok generated code to the .java file itself.
This is good if we wanted to modify the implementation provided by lombok.

Spring Boot Dev Tools -> to implement live reload, recompiling the file using 
build menu automatically triggers the server to do a live reload. On IntelliJ Utimate
some additional configurations can be done which is supported under spring configuration
On Update -> update classes and resources
On 