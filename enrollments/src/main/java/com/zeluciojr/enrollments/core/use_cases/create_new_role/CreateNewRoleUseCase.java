package com.zeluciojr.enrollments.core.use_cases.create_new_role;

import com.cae.use_cases.FunctionUseCase;
import com.zeluciojr.enrollments.core.use_cases.create_new_role.io.inputs.CreateNewRoleUseCaseInput;
import com.zeluciojr.enrollments.core.use_cases.create_new_role.io.outputs.CreateNewRoleUseCaseOutput;

/**
 * This is the contract of the CreateNewRoleUseCase.
 * Its input is mapped within the CreateNewRoleUseCaseInput class.
 * The output is mapped within the CreateNewRoleUseCaseOutput class.
 */
public abstract class CreateNewRoleUseCase extends FunctionUseCase<
        CreateNewRoleUseCaseInput,
        CreateNewRoleUseCaseOutput> {}
