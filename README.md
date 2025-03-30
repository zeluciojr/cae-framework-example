# Enrollments ☕

### This is a Java project for showing how to implement an application with _Clean Architecture_ principles by using the _CAE SDK_.

But first things first:

#### 💡 — What is Clean Architecture?
**🤖 ChatGPT answers:** 

Clean Architecture is a software design approach that organizes code around the business rules (use cases), separating it from implementation details like databases, frameworks, and user interfaces.

Its core principles are:

- Independence: business logic doesn't depend on external tools or technologies.

- Layers: code is structured in concentric layers — inner layers (like entities and use cases) are pure and stable, while outer layers (like UI, DB, frameworks) can change without affecting the core.

- Dependency Rule: dependencies always point inward — from outer to inner layers.

The final goal: **create systems that are flexible, testable, and resilient to change.**

#### 💡 — What is CAE SDK?
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

