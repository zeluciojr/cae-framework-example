package com.zeluciojr.enrollments.assemblers.use_cases.create_new_role;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.core.use_cases.create_new_role.io.inputs.CreateNewRoleUseCaseInput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class E2ETesting {

    public static void run(){
        var useCase = CreateNewRoleUseCaseAssembler.V1;
        var useCaseInput = CreateNewRoleUseCaseInput.builder()
                .roleName("Software Engineer")
                .roleDescription("Responsible for building amazing things...... and what customers want (lol jk)")
                .build();
        var useCaseOutput = useCase.execute(useCaseInput, ExecutionContext.ofNew());
    }

}
