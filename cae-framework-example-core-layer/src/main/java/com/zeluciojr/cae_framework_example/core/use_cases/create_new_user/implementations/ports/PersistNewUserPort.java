package com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.implementations.ports;

import com.cae.ports.specifics.functions.FunctionPort;
import com.zeluciojr.cae_framework_example.core.entities.User;

public abstract class PersistNewUserPort extends FunctionPort<User, Long> {
}
