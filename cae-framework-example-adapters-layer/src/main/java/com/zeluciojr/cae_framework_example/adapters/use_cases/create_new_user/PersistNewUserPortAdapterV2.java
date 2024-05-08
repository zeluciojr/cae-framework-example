package com.zeluciojr.cae_framework_example.adapters.use_cases.create_new_user;

import com.cae.use_cases.correlations.UseCaseExecutionCorrelation;
import com.zeluciojr.cae_framework_example.core.entities.User;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations.ports.PersistNewUserPort;

public class PersistNewUserPortAdapterV2 extends PersistNewUserPort {

    @Override
    protected Long executeLogic(User input, UseCaseExecutionCorrelation correlation) {
        //let's pretend it persists the new instance at the database and then returns its new ID somehow differently from the V1
        return 1L;
    }
}
