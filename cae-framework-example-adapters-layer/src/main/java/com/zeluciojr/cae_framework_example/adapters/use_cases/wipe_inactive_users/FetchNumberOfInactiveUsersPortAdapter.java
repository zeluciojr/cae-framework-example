package com.zeluciojr.cae_framework_example.adapters.use_cases.wipe_inactive_users;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.core.use_cases.wipe_inactive_users.implementations.ports.FetchNumberOfInactiveUsersPort;

public class FetchNumberOfInactiveUsersPortAdapter extends FetchNumberOfInactiveUsersPort {
    @Override
    protected Long executeLogic(UseCaseExecutionCorrelation correlation) {
        //Let's pretend it actually got the number of how many inactive users there are
        return 0L;
    }
}
