package com.zeluciojr.cae_framework_example.adapters.use_cases.create_new_user;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.core.entities.User;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations.ports.PersistNewUserPort;

public class PersistNewUserPortAdapter extends PersistNewUserPort {

    @Override
    protected Long executeLogic(User input, UseCaseExecutionCorrelation correlation) {
        //let's pretend it persists the new instance at the database and then returns its new ID
        return 1L;
    }
}
