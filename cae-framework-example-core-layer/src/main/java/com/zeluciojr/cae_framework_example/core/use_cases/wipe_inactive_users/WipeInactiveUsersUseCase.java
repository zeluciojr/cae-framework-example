package com.zeluciojr.cae_framework_example.core.use_cases.wipe_inactive_users;

import com.cae.use_cases.authorization.annotations.ProtectedUseCase;
import com.cae.use_cases.specifics.runnables.RunnableUseCase;

@ProtectedUseCase(scope = {"wipe-inactives:user"})
public abstract class WipeInactiveUsersUseCase extends RunnableUseCase { }
