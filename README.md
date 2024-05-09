## ☕ An example of application using the [cae-framework](https://github.com/clean-arch-enablers-project/cae-framework) at its 0.7.2 version.
![caelogo](https://github.com/zeluciojr/cae-framework-example/assets/60593328/def34ec8-0897-463a-bce3-031c1bfd205f)

In this example we have as layers:

<br>

- The Core layer with the application's Use Cases definition:
  - 🔗 [cae-framework-example-core-layer](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-core-layer).

<br>

- The Adapters layer with the application's Secondary Adapters:
  - 🔗 [cae-framework-example-adapters-layer](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-adapters-layer).

<br>

- The Assemblers layer with the application's Use Cases and their Adapters assembled:
  - 🔗 [cae-framework-example-assemblers-layer](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-assemblers-layer)

<br>

- The Dispatchers layer with the application's Primary Adapters:
  -   🔗 [cae-framework-example-lambda-handlers-layer](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-lambda-handlers-layer).
  -   🔗 [cae-framework-example-spring-controllers-layer](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-spring-controllers-layer).

<br>

As use cases:

<br>

- [create_new_user](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-core-layer/src/main/java/com/zeluciojr/cae_framework_example/core/use_cases/create_new_user) (FunctionUseCase)
- [make_user_inactive](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-core-layer/src/main/java/com/zeluciojr/cae_framework_example/core/use_cases/make_user_inactive) (ConsumerUseCase)
- [retrieve_all_inactive_users](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-core-layer/src/main/java/com/zeluciojr/cae_framework_example/core/use_cases/retrieve_all_inactive_users) (SupplierUseCase)
- [wipe_inactive_users](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-core-layer/src/main/java/com/zeluciojr/cae_framework_example/core/use_cases/wipe_inactive_users) (RunnableUseCase)

<br>


In this application you'll be able to take a look at:

- how use cases are declared in all four possible types.
- how to declare use cases as protected
- use case logic vs. entity logic 
- how to use ports within use cases
- how a use case implementation becomes naturally high level of abstraction, easy to be understood in terms of workflow.
- how use cases are assembled to get ready to be used in any dispatcher layer possible
- how to setup Logger configurations
- how to call the execution of use cases
- how to call the execution of a protected use case _(still not available here)_

<br>

🎆 Enjoy!

## ✍️ Snippets!

### CreateNewUserUseCase
_(Core layer)_
```java
package com.zeluciojr.cae_framework_example.core.use_cases.create_new_user;

import com.cae.use_cases.specifics.functions.FunctionUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.inputs.CreateNewUserUseCaseInput;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.outputs.CreateNewUserUseCaseOutput;


public abstract class CreateNewUserUseCase extends FunctionUseCase<CreateNewUserUseCaseInput, CreateNewUserUseCaseOutput> {}
```

### CreateNewUserUseCaseInput
_(Core layer)_
```java
package com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.inputs;

import com.cae.loggers.native_io_extraction_mode.json.sensitive.Sensitive;
import com.cae.use_cases.io.UseCaseInput;
import com.cae.use_cases.io.annotations.NotBlankInputField;
import com.cae.use_cases.io.annotations.NotNullInputField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateNewUserUseCaseInput extends UseCaseInput {

    @NotNullInputField
    @NotBlankInputField
    private String name;

    @NotNullInputField
    @NotBlankInputField
    private String email;

    @NotNullInputField
    @NotBlankInputField
    @Sensitive
    private String secret;

}

```

### CreateNewUserUseCaseOutput
_(Core layer)_
```java
package com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.outputs;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateNewUserUseCaseOutput {

    private final Long idNewUser;

}

```

### CreateNewUserUseCaseImplementation
_(Core layer)_
```java
package com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.CreateNewUserUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations.mappers.CreateNewUserUseCaseInputMapper;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations.ports.PersistNewUserPort;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.inputs.CreateNewUserUseCaseInput;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.outputs.CreateNewUserUseCaseOutput;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateNewUserUseCaseImplementation extends CreateNewUserUseCase {

    private final PersistNewUserPort persistNewUserPort;

    @Override
    protected CreateNewUserUseCaseOutput applyInternalLogic(
            CreateNewUserUseCaseInput input,
            UseCaseExecutionCorrelation correlation) {
        var userToCreate = CreateNewUserUseCaseInputMapper.mapToUserEntity(input);
        userToCreate.makeEmailAsCredentialId();
        userToCreate.becomeActive();
        userToCreate.validatePropertiesForNewUser();
        var idNewUser = this.persistNewUserPort.executePortOn(userToCreate, correlation);
        return CreateNewUserUseCaseOutput.builder()
                .idNewUser(idNewUser)
                .build();
    }
}

```

### PersistNewUserPort
_(Core layer)_
```java
package com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations.ports;

import com.cae.ports.specifics.functions.FunctionPort;
import com.zeluciojr.cae_framework_example.core.entities.User;

public abstract class PersistNewUserPort extends FunctionPort<User, Long> {}

```

### PersistNewUserPortAdapter
_(Adapters layer)_
```java
package com.zeluciojr.cae_framework_example.adapters.use_cases.create_new_user;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.core.entities.User;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations.ports.PersistNewUserPort;

public class PersistNewUserPortAdapter extends PersistNewUserPort {

    @Override
    protected Long executeLogic(User input, UseCaseExecutionCorrelation correlation) {
        //let's pretend it persists the new instance at the database and then returns its new ID
        return 1L;
    }
}

```

### PersistNewUserPortAdapterV2
_(Adapters layer)_
```java
package com.zeluciojr.cae_framework_example.adapters.use_cases.create_new_user;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.core.entities.User;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations.ports.PersistNewUserPort;

public class PersistNewUserPortAdapterV2 extends PersistNewUserPort {

    @Override
    protected Long executeLogic(User input, UseCaseExecutionCorrelation correlation) {
        //let's pretend it persists the new instance at the database and then returns its new ID somehow differently from the V1
        return 1L;
    }
}

```

### PersistNewUserPortAdapterFactory
_(Adapters layer)_
```java
package com.zeluciojr.cae_framework_example.adapters.use_cases.create_new_user.factories;

import com.zeluciojr.cae_framework_example.adapters.use_cases.create_new_user.PersistNewUserPortAdapter;
import com.zeluciojr.cae_framework_example.adapters.use_cases.create_new_user.PersistNewUserPortAdapterV2;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations.ports.PersistNewUserPort;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersistNewUserPortAdapterFactory{

    public static final PersistNewUserPort V1;
    public static final PersistNewUserPort V2;

    static {
        V1 = PersistNewUserPortAdapterFactory.initializeV1();
        V2 = PersistNewUserPortAdapterFactory.initializeV2();
    }

    private static PersistNewUserPort initializeV1() {
        return new PersistNewUserPortAdapter();
    }

    private static PersistNewUserPort initializeV2() {
        return new PersistNewUserPortAdapterV2();
    }

}

```

### LoggerAdapter
_(Adapters layer)_
```java
package com.zeluciojr.cae_framework_example.adapters.loggers;

import com.cae.loggers.Logger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggerAdapter implements Logger {

    /*
    Let's pretend that instead of using the System.out::println, here a real
    Logger library is being used
     */

    public static final Logger SINGLETON = new LoggerAdapter();

    @Override
    public void logInfo(String info) {
        System.out.println(info);
    }

    @Override
    public void logError(String error) {
        System.out.println(error);
    }

    @Override
    public void logDebug(String info) {
        System.out.println(info);
    }
}

```

### LoggerBootstrap
_(Assemblers layer)_
```java
package com.zeluciojr.cae_framework_example.assemblers.loggers;

import com.cae.loggers.IOLoggingMode;
import com.cae.loggers.LoggerProvider;
import com.zeluciojr.cae_framework_example.adapters.loggers.LoggerAdapter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggerBootstrap {

    public static void startupLoggerSettings(){
        LoggerProvider.SINGLETON
                .setProvidedInstance(LoggerAdapter.SINGLETON)
                .setIOLoggingMode(IOLoggingMode.CAE_NATIVE)
                .structuredFormat(true)
                .async(true)
                .setUseCasesLoggingIO(true)
                .setPortsLoggingIO(true);
    }

}

```

### CreateNewUserUseCaseAssembler
_(Assemblers layer)_
```java
package com.zeluciojr.cae_framework_example.assemblers.use_cases.create_new_user;

import com.cae.use_cases.assemblers.UseCaseAssembler;
import com.zeluciojr.cae_framework_example.adapters.use_cases.create_new_user.factories.PersistNewUserPortAdapterFactory;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.CreateNewUserUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations.CreateNewUserUseCaseImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateNewUserUseCaseAssembler implements UseCaseAssembler<CreateNewUserUseCase> {

    public static final CreateNewUserUseCaseAssembler SINGLETON_ASSEMBLER = new CreateNewUserUseCaseAssembler();

    public static final CreateNewUserUseCase V1;
    public static final CreateNewUserUseCase V2;

    static {
        V1 = CreateNewUserUseCaseAssembler.initializeV1();
        V2 = CreateNewUserUseCaseAssembler.initializeV2();
    }

    private static CreateNewUserUseCase initializeV1() {
        return new CreateNewUserUseCaseImplementation(
                PersistNewUserPortAdapterFactory.V1
        );
    }

    private static CreateNewUserUseCase initializeV2() {
        return new CreateNewUserUseCaseImplementation(
                PersistNewUserPortAdapterFactory.V2
        );
    }

    @Override
    public CreateNewUserUseCase getDefaultAssembledInstance() {
        return V2;
    }
}

```

### CreateNewUserSpringController
_(Dispatchers layer: Spring Controllers)_
```java
package com.zeluciojr.cae_framework_example.spring_controllers.use_cases.create_new_user;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.assemblers.use_cases.create_new_user.CreateNewUserUseCaseAssembler;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.CreateNewUserUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.inputs.CreateNewUserUseCaseInput;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.outputs.CreateNewUserUseCaseOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateNewUserSpringController {

    private static final CreateNewUserUseCase USE_CASE_V1 = CreateNewUserUseCaseAssembler.V1;
    private static final CreateNewUserUseCase USE_CASE_V2 = CreateNewUserUseCaseAssembler.V2;

    @PostMapping("/v1/users")
    public ResponseEntity<CreateNewUserUseCaseOutput> executeV1(
            @RequestHeader String correlationId,
            @RequestBody CreateNewUserUseCaseInput useCaseInput){
        var useCaseOutput = USE_CASE_V1.execute(useCaseInput, UseCaseExecutionCorrelation.of(correlationId));
        return ResponseEntity.status(201).body(useCaseOutput);
    }

    @PostMapping("/v2/users")
    public ResponseEntity<CreateNewUserUseCaseOutput> executeV2(
            @RequestHeader String correlationId,
            @RequestBody CreateNewUserUseCaseInput useCaseInput){
        var useCaseOutput = USE_CASE_V2.execute(useCaseInput, UseCaseExecutionCorrelation.of(correlationId));
        return ResponseEntity.status(201).body(useCaseOutput);
    }

}

```

### ErrorHandler
_(Dispatchers layer: Spring Controllers)_
```java
package com.zeluciojr.cae_framework_example.spring_controllers.error_handling;

import com.cae.mapped_exceptions.MappedException;
import com.cae.mapped_exceptions.specifics.InputMappedException;
import com.cae.mapped_exceptions.specifics.InternalMappedException;
import com.cae.mapped_exceptions.specifics.NotFoundMappedException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<Problem> handle(InputMappedException inputMappedException){
        return ResponseEntity.badRequest().body(Problem.of(inputMappedException));
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handle(NotFoundMappedException notFoundMappedException){
        return ResponseEntity.status(404).body(Problem.of(notFoundMappedException));
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handle(InternalMappedException internalMappedException){
        return ResponseEntity.internalServerError().body(Problem.of(internalMappedException));
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handle(Exception unexpectedException){
        return ResponseEntity.internalServerError().body(Problem.of(unexpectedException));
    }


    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class Problem{

        public static Problem of(MappedException mappedException){
            return new Problem(mappedException.getBriefPublicMessage());
        }

        public static Problem of(Exception exception){
            return new Problem(exception.getMessage());
        }

        private final String message;

    }

}

```

### Main
_(Dispatchers layer: Spring Controllers)_
```java
package com.zeluciojr;

import com.zeluciojr.cae_framework_example.assemblers.loggers.LoggerBootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        LoggerBootstrap.startupLoggerSettings();
        SpringApplication.run(Main.class);
    }
}
```
