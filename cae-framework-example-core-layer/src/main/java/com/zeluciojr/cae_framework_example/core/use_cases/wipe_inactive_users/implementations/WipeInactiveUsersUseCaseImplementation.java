package com.zeluciojr.cae_framework_example.core.use_cases.wipe_inactive_users.implementations;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.core.use_cases.wipe_inactive_users.WipeInactiveUsersUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.wipe_inactive_users.implementations.ports.DeleteOldInactiveUsersPort;
import com.zeluciojr.cae_framework_example.core.use_cases.wipe_inactive_users.implementations.ports.FetchNumberOfInactiveUsersPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class WipeInactiveUsersUseCaseImplementation extends WipeInactiveUsersUseCase {

    private static final Integer MAX_ALLOWED_AMOUNT_OF_MONTHS_TO_BE_INACTIVE = 3;

    private final FetchNumberOfInactiveUsersPort fetchNumberOfInactiveUsersPort;
    private final DeleteOldInactiveUsersPort deleteOldInactiveUsersPort;

    @Override
    protected void applyInternalLogic(UseCaseExecutionCorrelation useCaseExecutionCorrelation) {
        if (this.fetchNumberOfInactiveUsersPort.executePort(useCaseExecutionCorrelation) > 1000){
            var limitForBeingSpared = LocalDateTime.now().minusMonths(MAX_ALLOWED_AMOUNT_OF_MONTHS_TO_BE_INACTIVE);
            this.deleteOldInactiveUsersPort.executePortOn(limitForBeingSpared, useCaseExecutionCorrelation);
        }

    }
}
