package com.zeluciojr.enrollments.core.use_cases.create_new_role.implementations;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.core.entities.Role;
import com.zeluciojr.enrollments.core.use_cases.create_new_role.CreateNewRoleUseCase;
import com.zeluciojr.enrollments.core.use_cases.create_new_role.implementations.ports.NewRolePersistencePort;
import com.zeluciojr.enrollments.core.use_cases.create_new_role.io.inputs.CreateNewRoleUseCaseInput;
import com.zeluciojr.enrollments.core.use_cases.create_new_role.io.outputs.CreateNewRoleUseCaseOutput;
import lombok.RequiredArgsConstructor;

/**
 * This class implements the CreateNewRoleUseCase, containing all the internal logic for its operation.
 * Since it is a FunctionUseCase, its purpose is to supply something, based on its input.
 * <p>
 * Important: Avoid hardcoding any sensitive information in this code. The auto-documentation
 * process will analyze this section of the source code if the GPT mode is enabled.
 */
@RequiredArgsConstructor
public class CreateNewRoleUseCaseImplementation extends CreateNewRoleUseCase {

    private final NewRolePersistencePort newRolePersistencePort;

    @Override
    protected CreateNewRoleUseCaseOutput applyInternalLogic(
            CreateNewRoleUseCaseInput input,
            ExecutionContext context) {
        var newRole = Role.createNewOne(
                input.getRoleName(),
                input.getRoleDescription()
        );
        this.save(newRole, context);
        return new CreateNewRoleUseCaseOutput(newRole.getId().toString());
    }

    private void save(Role newRole, ExecutionContext context) {
        this.newRolePersistencePort.executePortOn(newRole, context);
    }
}
