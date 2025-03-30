package com.zeluciojr.enrollments.assemblers.use_cases.get_enrollment_by_id;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id.io.inputs.GetEnrollmentByIdUseCaseInput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class E2ETesting {

    public static void run(){
        var useCase = GetEnrollmentByIdUseCaseAssembler.V1;
        var useCaseInput = GetEnrollmentByIdUseCaseInput.builder()
                .enrollmentId(UUID.randomUUID().toString())
                .build();
        var useCaseOutput = useCase.execute(useCaseInput, ExecutionContext.ofNew());
    }

}
