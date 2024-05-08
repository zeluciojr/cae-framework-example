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
