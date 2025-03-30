package com.zeluciojr.enrollments.assemblers.use_cases.create_new_enrollment_experience;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.io.inputs.CreateNewEnrollmentExperienceUseCaseInput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class E2ETesting {

    public static void run(){
        var useCase = CreateNewEnrollmentExperienceUseCaseAssembler.V1;
        var useCaseInput = CreateNewEnrollmentExperienceUseCaseInput.builder()
                .enrollmentId(UUID.randomUUID().toString())
                .roleId(UUID.randomUUID().toString())
                .build();
        var useCaseOutput = useCase.execute(useCaseInput, ExecutionContext.ofNew());
    }

}
