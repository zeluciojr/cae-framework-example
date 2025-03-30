package com.zeluciojr.enrollments.assemblers.use_cases.create_new_role;

import com.cae.use_cases.assemblers.UseCaseAssembler;
import com.zeluciojr.enrollments.adapters.use_cases.create_new_role.NewRolePersistencePortAdapter;
import com.zeluciojr.enrollments.core.use_cases.create_new_role.CreateNewRoleUseCase;
import com.zeluciojr.enrollments.core.use_cases.create_new_role.implementations.CreateNewRoleUseCaseImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class creates a singleton object for the CreateNewRoleUseCase class.
 * You can use this class to access the instance of this use case anywhere.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateNewRoleUseCaseAssembler implements UseCaseAssembler<CreateNewRoleUseCase>{

    public static final CreateNewRoleUseCaseAssembler SINGLETON_ASSEMBLER = new CreateNewRoleUseCaseAssembler();

    public static final CreateNewRoleUseCase V1 = new CreateNewRoleUseCaseImplementation(
            NewRolePersistencePortAdapter.SINGLETON
    );

    @Override
    public CreateNewRoleUseCase getDefaultAssembledInstance(){
        return V1;
    }
}