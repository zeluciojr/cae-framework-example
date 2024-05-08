package com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.implementations.ports;

import com.cae.ports.specifics.functions.FunctionPort;
import com.zeluciojr.cae_framework_example.core.entities.User;

import java.util.Optional;

public abstract class FetchUserByIdForInactivatingItPort extends FunctionPort<Long, Optional<User>> {}
