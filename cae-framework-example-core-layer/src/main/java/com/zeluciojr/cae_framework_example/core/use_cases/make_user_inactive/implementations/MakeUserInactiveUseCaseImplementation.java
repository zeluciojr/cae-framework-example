package com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.implementations;

import com.cae.mapped_exceptions.specifics.NotFoundMappedException;
import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.MakeUserInactiveUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.implementations.ports.FetchUserByIdForInactivatingItPort;
import com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.implementations.ports.SaveMakingUserInactivePort;
import com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.io.inputs.MakeUserInactiveUseCaseInput;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MakeUserInactiveUseCaseImplementation extends MakeUserInactiveUseCase {

    private final FetchUserByIdForInactivatingItPort fetchUserByIdForInactivatingItPort;
    private final SaveMakingUserInactivePort saveMakingUserInactivePort;

    @Override
    protected void applyInternalLogic(MakeUserInactiveUseCaseInput input, UseCaseExecutionCorrelation correlation) {
        var user = this.fetchUserByIdForInactivatingItPort.executePortOn(input.getUserId(), correlation)
                .orElseThrow(() -> new NotFoundMappedException("User was not found", "User ID: " + input.getUserId()));
        if (user.isActive()){
            user.becomeInactive();
            this.saveMakingUserInactivePort.executePortOn(user, correlation);
        }
    }
}
