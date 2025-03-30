# Enrollments ☕

## This is a Java project for showing how to implement an application with _Clean Architecture_ principles by using the _CAE SDK_.

But first things first:

### 💡 — What is Clean Architecture?
**🤖 ChatGPT answers:** 

Clean Architecture is a software design approach that organizes code around the business rules (use cases), separating it from implementation details like databases, frameworks, and user interfaces.

Its core principles are:

- Independence: business logic doesn't depend on external tools or technologies.

- Layers: code is structured in concentric layers — inner layers (like entities and use cases) are pure and stable, while outer layers (like UI, DB, frameworks) can change without affecting the core.

- Dependency Rule: dependencies always point inward — from outer to inner layers.

The final goal: **create systems that are flexible, testable, and resilient to change.**

### 💡 — What is CAE SDK?
The CAE SDK is a project I've been developing since 2023. Its goal is to bridge the gap between building software with Clean Architecture and delivering it quickly, minimizing friction in the development process.

Since Clean Architecture follows a well-defined pattern for modularizing applications around use cases, which internally call business entity functions and external-facing components, or secondary ports, such as databases, REST APIs, messaging systems and more, I created a component called **_UseCase_**, which is divided into four subtypes:

- **_FunctionUseCase_** – _has both **input** and **output**_
- **_ConsumerUseCase_** – _has **input** only_
- **_SupplierUseCase_** – _has **output** only_
- **_RunnableUseCase_** – _has neither **input** nor **output**_

When you create a class that extends one of these types, it automatically gains:

- A standardized API: **_UseCase_**::_execute_, which receives an **_ExecutionContext_** object containing the execution context (like a correlationId for observability and an actor for authorization purposes)

- The **Autolog** feature: every time a **_UseCase_** is executed, a log entry is generated showing the status of the execution, optionally including I/O data, latency, and failure reasons if exceptions occur

- The **Autoauth** feature: **_UseCase_** instances can restrict execution based on scope or user role. The received **_ExecutionContext_** gives access to an Actor object, which exposes:
  - **_Actor_**::_getScopes_ to verify if the actor has required scopes 
  - **_Actor_**::_getId_ to check if the actor is associated with any application-managed role required for access
 
- For **_UseCase_** instances with input: the **Autoverify** feature allows validating input payloads before the internal use case logic runs. You can define fields that must not be null, blank, or empty—even recursively through custom types
 
- The **Autonotify** feature: generates notifications when a **_UseCase_** throws a predefined exception or exceeds a latency threshold

- The **Autodoc** feature: during the build process, all **_UseCase_** instances have their metadata automatically extracted and documented into a JSON file named _cae-docfile.json_

That covers the **_UseCase_** side. Another core standardization in the CAE SDK is the componentization of **_Ports_**.

Whenever a **_UseCase_** needs to interact with the outside world (outbound flow), it can rely on **_Port_** instances, which, like **_UseCases_**, come in four subtypes:

- **_FunctionPort_**
- **_ConsumerPort_**
- **_SupplierPort_**
- **_RunnablePort_**

When a class extends one of these types, it gains:

- A standardized execution API: **_Port_**::_executePortOn_

- PortInsights: logs port execution status through **Autolog**, preserving the sequence in which ports are used in the **_UseCase_** instance's algorithm

- Optional I/O logging through **Autolog** for better observability

By reusing these components, we significantly reduce friction—there's no need to define the **_UseCase_** API from scratch every time, just extend the standard contracts. The same applies to Ports. Thanks to this reuse, you also get access to **Autofeatures** that are essential for production-ready apps—without having to build them manually—like **Autolog**, **Autoverify**, **Autonotify**, and **Autodoc** and more.

In addition to the componentization through classes and Autofeatures, the SDK also standardizes a project structure to organize these components effectively. CAE SDK projects are structured into three layers:

- **Core**: contains business entities, application rules (**_UseCase_** classes), and **_Port_** definitions
- **Adapters**: implements adapters for the **_Port_** classes, connecting them to real-world dependencies such as HTTP clients, database repositories, etc.
- **Assemblers**: acts as factories that instantiate _**UseCase**_ classes, injecting the required Adapters into each **_Port_**, and exposing standalone **_UseCase_** instances without relying on external instantiation

These three layers are organized following the vertical slice pattern, with each **_UseCase_** acting as the central axis. For example, if there’s a **_UseCase_** named _CreateNewEnrollment_, it will be split into three packages—one per layer:

- core.use_cases.**create_new_enrollment**
- adapters.use_cases.**create_new_enrollment**
- assemblers.use_cases.**create_new_enrollment**

All components related to that specific **_UseCase_** will reside exclusively within its own set of packages across the three layers. This ensures that changes made to one **_UseCase_** do not unintentionally affect others.

Within the core layer, besides the use_cases package (which contains a subpackage for each application **_UseCase_**), there's also an entities package for defining pure business logic components. These entities are independent of specific use cases and represent the most fundamental layer of the application, upon which everything else depends.

In the adapters layer, in addition to the use_cases package (with subpackages mirroring each **_UseCase_** for their respective adapters), there are two important packages:

- **autofeatures**: contains components that adapt the contracts of terminal **Autofeature** behaviors. For example: for **Autolog**, you might implement a **_DefaultLoggerAdapter_** using any logging library you prefer. For **Autonotify**, you could create an adapter that routes notifications to an observability platform like Datadog, sending them as custom metric datapoints
- **dependencies**: this is where you integrate external systems. Define database repositories, HTTP clients, Kafka or SQS producers, etc., without worrying about the Core layer’s concerns.

For the assemblers layer, besides the use_cases, the standard packages include:

- **autofeatures**: this is where you register the adapters you've created for each Autofeature, enabling the SDK to use them during execution 
- Any other packages needed to define global configuration components for the entire application

(The assemblers are the wiring layer—they connect all parts together.)

These layers can be split into separate projects/modules, linked via dependency management (e.g., using Maven). However, I prefer them to be logically separated within the same project, using package structure only. That’s perfectly fine, as long as we respect the dependency direction: always from outer layers to inner layers.

##### Structure of a UseCase in the Core Layer

A **_UseCase_** in the core layer follows this structure (consider the example _CreateNewEnrollment_):

```dtd
core
└── use_cases
    └── create_new_enrollment
        ├── implementations
        ├── io
        └── CreateNewEnrollmentUseCase.java
```

The class at the root (**_CreateNewEnrollmentUseCase_**) is abstract and acts as the external interface for the **_UseCase_**. It declares the type of the **_UseCase_**—**_FunctionUseCase_**, **_ConsumerUseCase_**, **_SupplierUseCase_**, or **_RunnableUseCase_**—based on its I/O contract (i.e., whether it has input and output, just one, or neither).

The **io** subpackage is structured like this:

```dtd
core
└── use_cases
    └── create_new_enrollment
        ├── implementations
        └── io
            ├── inputs
            │      └── CreateNewEnrollmentUseCaseInput.java
            └── outputs
                    └── CreateNewEnrollmentUseCaseOutput.java
```

This package is responsible for defining the data structures the **_UseCase_** needs to receive (input) and what it promises to return (output).

- The input class (**_CreateNewEnrollmentUseCaseInput_**) must extend the **_UseCaseInput_** type. This enables **_UseCase_** instances to call the **_UseCaseInput_**::_autoverify_ API to automatically verify that all required fields comply with the input contract rules.
- The output class is flexible and doesn't need to extend any base class.

Inside the implementations package, you'll find:

```dtd
core
└── use_cases
    └── create_new_enrollment
        └── implementations
                ├── ports
                └── CreateNewEnrollmentUseCaseImplementation.java
```

- The ports subpackage contains all the port interfaces required by the **_UseCase_** implementation.
- The **_CreateNewEnrollmentUseCaseImplementation_** class contains the actual application logic of the **_UseCase_**. This is where you call business entities and use the defined ports—essentially, it contains the application rule algorithm. This class is concrete and extends the abstract contract of the **_CreateNewEnrollmentUseCase_** class.

##### Structure of a UseCase in the Adapters Layer

Each UseCase will have its own dedicated package in the Adapters layer, which initially may be empty—just waiting for the creation of adapters for the ports declared in the Core layer.

For example, if we have three UseCases: **_CreateNewEnrollment_**, **_CreateNewRole_**, and **_EndEnrollment_**, the structure with potential adapters would look like this:

```dtd
adapters
└── use_cases
    ├── create_new_enrollment
    │   ├── NewEnrollmentPersistencePortAdapter.java
    │   ├── PersonRetrievalByIdPortAdapter.java
    │   └── RoleRetrievalByIdPortAdapter.java
    ├── create_new_role
    │   └── NewRolePersistencePortAdapter.java
    └── end_enrollment
        ├── EnrollmentRetrievalByIdPortAdapter.java
        └── EnrollmentUpdatePortAdapter.java
```

Each of these adapter classes is responsible for translating the logic expected by the Core layer into logic understood by the actual external dependencies—whether it's a database, an API, or another system.

##### Structure of a UseCase in the Assemblers Layer

The standard structure for a **_UseCase_** in the Assemblers layer is simple: it consists of a single **Assembler** class for that specific **_UseCase_**.
For example, for the **_CreateNewEnrollmentUseCase_**, the structure would be:

```dtd
assemblers
└── use_cases
    └── create_new_enrollment
        └── CreateNewEnrollmentUseCaseAssembler.java
```

The purpose of the Assembler is to provide access to a standalone instance of the **_UseCase_**—completely independent of frameworks like Spring, Micronaut, or any other that manage dependency injection through beans.

This means **_UseCase_** instances can be used freely, with any framework, in any environment—whether in a Lambda handler, a plain main method, or a Spring @RestController.

This embodies one of the most important principles of the CAE SDK: **standalone instances**.

##### Standalone Instances

**Standalone Instances** are self-contained objects that not only encapsulate all the necessary logic, but also expose themselves as **SINGLETON** instances ready for use.

In the case of **_UseCase_** instances, these are provided through **Assemblers**, since they require the injection of **Adapters** into their **Ports**.

However, for **Adapters**, they often expose themselves directly as **standalone instances** by declaring a **SINGLETON** within their own class.

Example:

```java
@RequiredArgsConstructor
public class NewEnrollmentPersistencePortAdapter extends NewEnrollmentPersistencePort {

    public static final NewEnrollmentPersistencePort SINGLETON = new NewEnrollmentPersistencePortAdapter(
        EnrollmentsTableRepository.SINGLETON,
        ExperiencesTableRepository.SINGLETON
    );

    private final EnrollmentsTableRepository enrollmentsTableRepository;
    private final ExperiencesTableRepository experiencesTableRepository;

    @Override
    protected void executeLogic(Enrollment input, ExecutionContext correlation) {
        this.enrollmentsTableRepository.createNew(new EnrollmentsTable(input));
        input.getExperiences()
             .stream()
             .map(ExperiencesTable::new)
             .forEach(this.experiencesTableRepository::createNew);
    }
}
```

The adapter above handles its own instantiation and exposes a fully functional, ready-to-use object.
This is what we refer to as a standalone instance.

Optionally, a constructor is provided to allow for dependency injection during testing (e.g., with mocked repositories).

This pattern is widely adopted in CAE-based projects and has inspired a broader philosophy of enabler components—libraries and modules built to offer out-of-the-box standalone instances, such as:

- Standalone HTTP Clients
- Standalone Database Repositories
- Standalone Retry Mechanisms
- And more

The result: plug-and-play infrastructure components that align perfectly with Clean Architecture and the CAE SDK vision.

### ▶️ Enrollments: A Sample Application Demonstrating How to Use the SDK and Apply Clean Architecture Principles

Enrollments serves as a reference implementation to showcase how the CAE SDK can be used in practice. It illustrates how to structure an application around use cases, implement ports and adapters, and leverage SDK features like Autolog, Autoverify, Autonotify, and Autodoc—all while adhering to Clean Architecture standards.

The application implements business rules related to enrollment management, including creating enrollments, closing them, handling promotions, and managing roles associated with each enrollment.

#### Entities
Here are the entities with their properties and behaviors:

```dtd
core
└── entities
    └── Countries
    ├── CPF
    ├── Enrollment
    ├── Experience
    ├── LegalId
    ├── Person
    ├── Role
    ├── UnknownLegalIdType
    └── UUIDBasedEntity
```

They all contain self-contained logic that **_UseCase_** instances can invoke to drive business behavior.

Their internal logic can be seen below:

##### LegalID, CPF & UnknownLegalIdType
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class LegalId implements Entity {

    private String value;

    public boolean isValid(){
        if (this.getValue() == null || this.getValue().isBlank())
            return false;
        return this.checkValidity();
    }

    protected abstract boolean checkValidity();

}
```
```java
public class CPF extends LegalId{

    public static final String REGEX = "^(?!.*(\\d)\\1{10})(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";

    public static CPF of(String value){
        var newCpf = new CPF();
        newCpf.setValue(value);
        return newCpf;
    }

    @Override
    protected boolean checkValidity() {
        var regexMatches = Pattern.matches(REGEX, this.getValue());
        var cleanValue = this.getValue()
                .replace(".", "")
                .replace("-", "");
        int firstCheckDigit = calculateDigit(cleanValue.substring(0, 9), 10);
        int secondCheckDigit = calculateDigit(cleanValue.substring(0, 9) + firstCheckDigit, 11);
        return regexMatches && cleanValue.endsWith("" + firstCheckDigit + secondCheckDigit);
    }

    private static int calculateDigit(String cpfSegment, int weight) {
        int sum = 0;
        for (char digitChar : cpfSegment.toCharArray()) {
            int digit = Character.getNumericValue(digitChar);
            sum += digit * weight;
            weight--;
        }
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

}
```
```java
public class UnknownLegalIdType extends LegalId{

    public static UnknownLegalIdType of(String value){
        var legalId = new UnknownLegalIdType();
        legalId.setValue(value);
        return legalId;
    }


    @Override
    protected boolean checkValidity() {
        return this.getValue() != null;
    }
}
```

##### Countries
```java
@Getter
@RequiredArgsConstructor
public enum Countries {

    BR("BR", CPF::of);

    private final String name;
    private final Function<String, LegalId> personalLegalIdConstructor;

    public static Optional<Countries> of(String value){
        return Stream.of(values())
                .filter(allowedOption -> allowedOption.getName().equalsIgnoreCase(value))
                .findFirst();
    }

    public static String getAllowedOptionsToString() {
        return Stream.of(values())
                .map(Countries::getName)
                .reduce("", (previous, next) -> previous + next + "; ");
    }
}
```

##### Person
```java
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Entity, UUIDBasedEntity {

    private UUID id;
    private LegalId legalId;
    private String fullName;
    private String preferredName;

    public static Person of(UUID id){
        return Person.builder()
                .id(id)
                .build();
    }

    public static Person createNewOne(String fullName, String preferredName, LegalId legalId){
        return Person.builder()
                .id(UUID.randomUUID())
                .fullName(fullName)
                .preferredName(preferredName)
                .legalId(legalId)
                .build();
    }

    public static Person createNewOne(String fullName, LegalId legalId){
        return Person.builder()
                .id(UUID.randomUUID())
                .fullName(fullName)
                .legalId(legalId)
                .build();
    }

    public boolean hasPreferredName(){
        return this.preferredName != null && !this.preferredName.isBlank();
    }

}
```

##### Role
```java
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Entity, UUIDBasedEntity {

    private UUID id;
    private String name;
    private String description;
    private Boolean active;

    public static Role createNewOne(String name, String description){
        return Role.builder()
                .id(UUID.randomUUID())
                .name(name)
                .description(description)
                .active(true)
                .build();
    }

    public static Role of(UUID id){
        return Role.builder()
                .id(id)
                .build();
    }

}
```

##### Enrollment
```java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment implements Entity, UUIDBasedEntity {

    private UUID id;
    private Person person;
    private Boolean active;

    @Builder.Default
    private List<Experience> experiences = new ArrayList<>();

    public static Enrollment createNewOneFor(Person person, Role role){
        var newEnrollment = Enrollment.builder()
                .id(UUID.randomUUID())
                .person(person)
                .active(true)
                .build();
        newEnrollment.addNewExperienceAs(role);
        return newEnrollment;
    }

    public static Enrollment of(UUID id){
        return Enrollment.builder()
                .id(id)
                .build();
    }

    public Experience addNewExperienceAs(Role role){
        this.getCurrentExperience().ifPresent(Experience::finish);
        var newExperience = Experience.createNewOneAs(role);
        newExperience.setEnrollment(this);
        this.experiences.add(newExperience);
        return newExperience;
    }

    public Optional<Experience> getCurrentExperience(){
        return this.experiences.stream()
                .sorted()
                .filter(Experience::isActive)
                .findFirst();
    }

    public Optional<Experience> getPreviousExperience() {
        var numberOfExperiences = this.experiences.size();
        if (this.experiences.isEmpty() || (numberOfExperiences == 1 && this.experiences.get(0).isActive()))
            return Optional.empty();
        Collections.sort(this.experiences);
        Collections.reverse(this.experiences);
        var hasOnlyOneXPWhichIsInactive = numberOfExperiences == 1;
        var indexOfPreviousXp = numberOfExperiences - (hasOnlyOneXPWhichIsInactive? 1 : 2);
        var previousXp = this.experiences.get(indexOfPreviousXp);
        return Optional.of(previousXp);
    }

    public void end(){
        this.getCurrentExperience().ifPresent(Experience::finish);
        this.active = false;
    }
}
```

##### Experience
```java
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Experience implements Entity, UUIDBasedEntity, Comparable<Experience> {

    private UUID id;
    private Role role;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Enrollment enrollment;

    public static Experience createNewOneAs(Role role) {
        return Experience.builder()
                .id(UUID.randomUUID())
                .startedAt(LocalDateTime.now())
                .role(role)
                .build();
    }

    @Override
    public int compareTo(Experience otherExperience) {
        return this.startedAt.compareTo(otherExperience.startedAt);
    }

    public void finish() {
        this.endedAt = LocalDateTime.now();
    }

    public boolean isActive(){
        return this.endedAt == null;
    }

}

```

##### UUIDBasedEntity
```java
public interface UUIDBasedEntity {

    UUID getId();

}
```

Once these logics are defined, we can expose them through UseCases. The **_UseCase_** declarations created are:

```dtd
core
└── use_cases
    └── create_new_enrollment
    ├── create_new_enrollment_experience
    ├── create_new_person
    ├── create_new_role
    ├── end_enrollment
    └── get_enrollment_by_id
```

Their logic can be observed below:

##### CreateNewPersonUseCase, Input, Output & Implementation
```java
public abstract class CreateNewPersonUseCase extends FunctionUseCase<
        CreateNewPersonUseCaseInput,
        CreateNewPersonUseCaseOutput> {}
```
```java
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewPersonUseCaseInput extends UseCaseInput {

    @Sensitive
    @NotBlankInputField
    @NotNullInputField
    private String legalId;

    @NotNullInputField
    @NotBlankInputField
    private String country;

    @NotNullInputField
    @NotBlankInputField
    private String fullName;

    private String preferredName;

}
```
```java
@Getter
@RequiredArgsConstructor
public class CreateNewPersonUseCaseOutput {

    private final String newPersonId;

}
```
```java
@RequiredArgsConstructor
public class CreateNewPersonUseCaseImplementation extends CreateNewPersonUseCase {

    private final PersonRetrievalByLegalIdPort personRetrievalByLegalIdPort;
    private final NewPersonPersistencePort newPersonPersistencePort;

    @Override
    protected CreateNewPersonUseCaseOutput applyInternalLogic(
            CreateNewPersonUseCaseInput input,
            ExecutionContext context) {
        var country = this.getCountryOutta(input);
        var legalId = this.getLegalIdFor(country, input);
        if (legalId.isValid()){
            this.checkAvailabilityOf(legalId, context);
            var newPerson = Person.createNewOne(
                    input.getFullName(),
                    input.getPreferredName(),
                    legalId
            );
            this.save(newPerson, context);
            return new CreateNewPersonUseCaseOutput(newPerson.getId().toString());
        }
        else
            throw new InputMappedException("Invalid legal ID provided");
    }

    private Countries getCountryOutta(CreateNewPersonUseCaseInput input) {
        return Countries.of(input.getCountry())
                .orElseThrow(() -> new InputMappedException(
                        "Couldn't process the informed country",
                        "Allowed options: " + Countries.getAllowedOptionsToString()
                ));
    }

    private LegalId getLegalIdFor(Countries country, CreateNewPersonUseCaseInput input) {
        return country.getPersonalLegalIdConstructor()
                .apply(input.getLegalId());
    }

    private void checkAvailabilityOf(LegalId legalId, ExecutionContext context) {
        var personWithSameLegalId = this.personRetrievalByLegalIdPort.executePortOn(legalId, context);
        if (personWithSameLegalId.isPresent())
            throw new InputMappedException("Legal ID has been taken");
    }

    private void save(Person newPerson, ExecutionContext context) {
        this.newPersonPersistencePort.executePortOn(newPerson, context);
    }
}
```

##### CreateNewRoleUseCase, Input, Output & Implementation
```java
public abstract class CreateNewRoleUseCase extends FunctionUseCase<
        CreateNewRoleUseCaseInput,
        CreateNewRoleUseCaseOutput> {}
```
```java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewRoleUseCaseInput extends UseCaseInput {

    @NotNullInputField
    @NotBlankInputField
    private String roleName;

    @NotBlankInputField
    private String roleDescription;

}

```
```java
@Getter
@RequiredArgsConstructor
public class CreateNewRoleUseCaseOutput {

    private final String newRoleId;

}
```
```java
@RequiredArgsConstructor
public class CreateNewRoleUseCaseImplementation extends CreateNewRoleUseCase {

    private final NewRolePersistencePort newRolePersistencePort;

    @Override
    protected CreateNewRoleUseCaseOutput applyInternalLogic(
            CreateNewRoleUseCaseInput input,
            ExecutionContext context) {
        var newRole = Role.createNewOne(
                input.getRoleName(),
                input.getRoleDescription()
        );
        this.save(newRole, context);
        return new CreateNewRoleUseCaseOutput(newRole.getId().toString());
    }

    private void save(Role newRole, ExecutionContext context) {
        this.newRolePersistencePort.executePortOn(newRole, context);
    }
}

```

##### CreateNewEnrollmentUseCase, Input, Output & Implementation
```java
public abstract class CreateNewEnrollmentUseCase extends FunctionUseCase<
        CreateNewEnrollmentUseCaseInput,
        CreateNewEnrollmentUseCaseOutput> {}
```
```java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewEnrollmentUseCaseInput extends UseCaseInput {

    @NotNullInputField
    @NotBlankInputField
    private String personId;

    @NotNullInputField
    @NotBlankInputField
    private String roleId;

}
```
```java
@Getter
@RequiredArgsConstructor
public class CreateNewEnrollmentUseCaseOutput {

    private final String newEnrollmentId;

}
```
```java
@RequiredArgsConstructor
public class CreateNewEnrollmentUseCaseImplementation extends CreateNewEnrollmentUseCase {

    private final PersonRetrievalByIdPort personRetrievalByIdPort;
    private final RoleRetrievalByIdPort roleRetrievalByIdPort;
    private final NewEnrollmentPersistencePort newEnrollmentPersistencePort;

    @Override
    protected CreateNewEnrollmentUseCaseOutput applyInternalLogic(
            CreateNewEnrollmentUseCaseInput input,
            ExecutionContext context) {
        var personToEnroll = this.findPersonBy(input.getPersonId(), context);
        var roleToAssign = this.findRoleBy(input.getRoleId(), context);
        var newEnrollment = Enrollment.createNewOneFor(personToEnroll, roleToAssign);
        this.save(newEnrollment, context);
        return new CreateNewEnrollmentUseCaseOutput(newEnrollment.getId().toString());
    }

    private Person findPersonBy(String personId, ExecutionContext context) {
        return this.personRetrievalByIdPort.executePortOn(UUID.fromString(personId), context)
                .orElseThrow(() -> new NotFoundMappedException(
                        "Couldn't find the person to enroll",
                        "ID provided was '" + personId + "'"
                ));
    }

    private Role findRoleBy(String roleId, ExecutionContext context) {
        return this.roleRetrievalByIdPort.executePortOn(UUID.fromString(roleId), context)
                .orElseThrow(() -> new NotFoundMappedException(
                        "Couldn't find the role to assign",
                        "ID provided was '" + roleId + "'"
                ));
    }

    private void save(Enrollment newEnrollment, ExecutionContext context) {
        this.newEnrollmentPersistencePort.executePortOn(newEnrollment, context);
    }

}
```

##### CreateNewEnrollmentExperienceUseCase, Input, Output & Implementation
```java
public abstract class CreateNewEnrollmentExperienceUseCase extends FunctionUseCase<
        CreateNewEnrollmentExperienceUseCaseInput,
        CreateNewEnrollmentExperienceUseCaseOutput> {}
```
```java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewEnrollmentExperienceUseCaseInput extends UseCaseInput {

    @NotNullInputField
    @NotBlankInputField
    private String enrollmentId;

    @NotNullInputField
    @NotBlankInputField
    private String roleId;

}
```
```java
@Getter
@RequiredArgsConstructor
public class CreateNewEnrollmentExperienceUseCaseOutput {

    private final String newExperienceId;

}

```
```java
@RequiredArgsConstructor
public class CreateNewEnrollmentExperienceUseCaseImplementation extends CreateNewEnrollmentExperienceUseCase {

    private final EnrollmentRetrievalByIdPort enrollmentRetrievalByIdPort;
    private final RoleRetrievalByIdPort roleRetrievalByIdPort;
    private final EnrollmentUpdatePort enrollmentUpdatePort;

    @Override
    protected CreateNewEnrollmentExperienceUseCaseOutput applyInternalLogic(
            CreateNewEnrollmentExperienceUseCaseInput input,
            ExecutionContext context) {
        var enrollment = this.findEnrollmentBy(input.getEnrollmentId(), context);
        var role = this.findRoleBy(input.getRoleId(), context);
        var newXp = enrollment.addNewExperienceAs(role);
        this.update(enrollment, context);
        return new CreateNewEnrollmentExperienceUseCaseOutput(newXp.getId().toString());
    }

    private Enrollment findEnrollmentBy(String enrollmentId, ExecutionContext context) {
        return this.enrollmentRetrievalByIdPort.executePortOn(UUID.fromString(enrollmentId), context)
                .orElseThrow(() -> new NotFoundMappedException(
                        "Couldn't find enrollment by ID of " + enrollmentId
                ));
    }

    private Role findRoleBy(String roleId, ExecutionContext context) {
        return this.roleRetrievalByIdPort.executePortOn(UUID.fromString(roleId), context)
                .orElseThrow(() -> new NotFoundMappedException(
                        "Couldn't find role by ID of " + roleId
                ));
    }

    private void update(
            Enrollment enrollment,
            ExecutionContext context) {
        this.enrollmentUpdatePort.executePortOn(enrollment, context);
    }
}
```

##### EndEnrollmentUseCase, Input & Implementation
```java
public abstract class EndEnrollmentUseCase extends ConsumerUseCase<EndEnrollmentUseCaseInput> {}
```
```java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndEnrollmentUseCaseInput extends UseCaseInput {

    @NotNullInputField
    @NotBlankInputField
    private String enrollmentId;

}
```
```java
@RequiredArgsConstructor
public class EndEnrollmentUseCaseImplementation extends EndEnrollmentUseCase {

    private final EnrollmentRetrievalByIdPort enrollmentRetrievalByIdPort;
    private final EnrollmentUpdatePort enrollmentUpdatePort;

    @Override
    protected void applyInternalLogic(
            EndEnrollmentUseCaseInput input,
            ExecutionContext context) {
        var enrollmentToEnd = this.getEnrollmentBy(input.getEnrollmentId(), context);
        enrollmentToEnd.end();
        this.update(enrollmentToEnd, context);
    }

    private Enrollment getEnrollmentBy(String enrollmentId, ExecutionContext context) {
        return this.enrollmentRetrievalByIdPort.executePortOn(UUID.fromString(enrollmentId), context)
                .orElseThrow(() -> new NotFoundMappedException(
                        "Couldn't find enrollment of ID " + enrollmentId
                ));
    }

    private void update(Enrollment enrollmentToEnd, ExecutionContext context) {
        this.enrollmentUpdatePort.executePortOn(enrollmentToEnd, context);
    }
}
```

##### GetEnrollmentByIdUseCase, Input, Output & Implementation
```java
public abstract class GetEnrollmentByIdUseCase extends FunctionUseCase<
        GetEnrollmentByIdUseCaseInput,
        GetEnrollmentByIdUseCaseOutput> {}
```
```java
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetEnrollmentByIdUseCaseInput extends UseCaseInput {

    @NotNullInputField
    @NotBlankInputField
    private String enrollmentId;

}
```
```java
@Getter
@RequiredArgsConstructor
public class GetEnrollmentByIdUseCaseOutput {

    private final Enrollment enrollment;

}

```
```java
@RequiredArgsConstructor
public class GetEnrollmentByIdUseCaseImplementation extends GetEnrollmentByIdUseCase {

    private final EnrollmentRetrievalByIdPort enrollmentRetrievalByIdPort;

    @Override
    protected GetEnrollmentByIdUseCaseOutput applyInternalLogic(
            GetEnrollmentByIdUseCaseInput input,
            ExecutionContext context) {
        var enrollment = this.getEnrollmentBy(input.getEnrollmentId(), context);
        return new GetEnrollmentByIdUseCaseOutput(enrollment);
    }

    private Enrollment getEnrollmentBy(String enrollmentId, ExecutionContext context) {
        return this.enrollmentRetrievalByIdPort.executePortOn(UUID.fromString(enrollmentId), context)
                .orElseThrow(() -> new NotFoundMappedException(
                        "Couldn't find enrollment of ID " + enrollmentId
                ));
    }
}
```