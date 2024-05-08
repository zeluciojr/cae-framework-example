package com.zeluciojr.cae_framework_example.adapters.use_cases.retrieve_all_inactive_users;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.core.entities.User;
import com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users.implementations.ports.FetchInactiveUsersPort;

import java.util.List;

public class FetchInactiveUsersPortAdapter extends FetchInactiveUsersPort {
    @Override
    protected List<User> executeLogic(UseCaseExecutionCorrelation correlation) {
        //Let's pretend it actually fetches inactive users from database
        return List.of();
    }
}
