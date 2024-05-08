package com.zeluciojr.cae_framework_example.adapters.use_cases.make_user_inactive;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.core.entities.User;
import com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.implementations.ports.FetchUserByIdForInactivatingItPort;

import java.util.Optional;

public class FetchUserByIdForInactivatingItPortAdapter extends FetchUserByIdForInactivatingItPort {

    @Override
    protected Optional<User> executeLogic(Long input, UseCaseExecutionCorrelation correlation) {
        //Let's pretend it actually tries to find by id
        return Optional.empty();
    }
}
