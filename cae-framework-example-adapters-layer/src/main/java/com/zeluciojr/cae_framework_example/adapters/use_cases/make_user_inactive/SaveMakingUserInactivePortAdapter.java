package com.zeluciojr.cae_framework_example.adapters.use_cases.make_user_inactive;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.core.entities.User;
import com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.implementations.ports.SaveMakingUserInactivePort;

public class SaveMakingUserInactivePortAdapter extends SaveMakingUserInactivePort {

    @Override
    protected void executeLogic(User input, UseCaseExecutionCorrelation correlation) {
        //Let's pretend it actually updates the user instance
    }
}
