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

- A standardized execution API: **_Port_**::_execute_

- PortInsights: logs port execution status through **Autolog**, preserving the sequence in which ports are used in the **_UseCase_** instance's algorithm

- Optional I/O logging through **Autolog** for better observability

By reusing these components, we significantly reduce friction—there's no need to define the **_UseCase_** API from scratch every time, just extend the standard contracts. The same applies to Ports. Thanks to this reuse, you also get access to **Autofeatures** that are essential for production-ready apps—without having to build them manually—like **Autolog**, **Autoverify**, **Autonotify**, and **Autodoc** and more.

### WORK IN PROGRESS...