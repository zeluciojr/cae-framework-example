package com.zeluciojr.enrollments.assemblers.use_cases.create_new_enrollment;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.io.inputs.CreateNewEnrollmentUseCaseInput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class E2ETesting {

    public static void run(){
        var useCase = CreateNewEnrollmentUseCaseAssembler.V1;
        var useCaseInput = CreateNewEnrollmentUseCaseInput.builder()
                .personId(UUID.randomUUID().toString())
                .roleId(UUID.randomUUID().toString())
                .build();
        var useCaseOutput = useCase.execute(useCaseInput, ExecutionContext.ofNew());
    }

}
