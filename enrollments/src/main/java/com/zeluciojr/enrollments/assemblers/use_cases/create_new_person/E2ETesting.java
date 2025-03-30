package com.zeluciojr.enrollments.assemblers.use_cases.create_new_person;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.core.use_cases.create_new_person.io.inputs.CreateNewPersonUseCaseInput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class E2ETesting {

    public static void run(){
        var useCase = CreateNewPersonUseCaseAssembler.V1;
        var useCaseInput = CreateNewPersonUseCaseInput.builder()
                .legalId("111.222.333-44")
                .country("BR")
                .fullName("José Silva do Rego Barros")
                .preferredName("Jô Barros")
                .build();
        var useCaseOutput = useCase.execute(useCaseInput, ExecutionContext.ofNew());
    }

}
