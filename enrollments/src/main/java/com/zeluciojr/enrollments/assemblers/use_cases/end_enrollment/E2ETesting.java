package com.zeluciojr.enrollments.assemblers.use_cases.end_enrollment;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.core.use_cases.end_enrollment.io.inputs.EndEnrollmentUseCaseInput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class E2ETesting {

    public static void run(){
        var useCase = EndEnrollmentUseCaseAssembler.V1;
        var useCaseInput = EndEnrollmentUseCaseInput.builder()
                .enrollmentId(UUID.randomUUID().toString())
                .build();
        useCase.execute(useCaseInput, ExecutionContext.ofNew());
    }

}
