package com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users.implementations;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users.RetrieveAllInactiveUsersUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users.implementations.ports.FetchInactiveUsersPort;
import com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users.io.outputs.RetrieveAllInactiveUsersUseCaseOutput;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RetrieveAllInactiveUsersUseCaseImplementation extends RetrieveAllInactiveUsersUseCase {

    private final FetchInactiveUsersPort fetchInactiveUsersPort;

    @Override
    protected RetrieveAllInactiveUsersUseCaseOutput applyInternalLogic(UseCaseExecutionCorrelation useCaseExecutionCorrelation) {
        var inactiveUsers = this.fetchInactiveUsersPort.executePort(useCaseExecutionCorrelation);
        return RetrieveAllInactiveUsersUseCaseOutput
                .builder()
                .inactiveUsers(inactiveUsers)
                .build();
    }
}
