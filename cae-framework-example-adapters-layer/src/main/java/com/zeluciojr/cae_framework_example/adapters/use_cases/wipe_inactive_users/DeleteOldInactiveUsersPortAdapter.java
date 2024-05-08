package com.zeluciojr.cae_framework_example.adapters.use_cases.wipe_inactive_users;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.core.use_cases.wipe_inactive_users.implementations.ports.DeleteOldInactiveUsersPort;

import java.time.LocalDateTime;

public class DeleteOldInactiveUsersPortAdapter extends DeleteOldInactiveUsersPort {

    @Override
    protected void executeLogic(LocalDateTime limitForBeingSpared, UseCaseExecutionCorrelation correlation) {
        //Let's pretend it actually deleted every inactive user with the inactivation date before the limit for being spared
    }
}
