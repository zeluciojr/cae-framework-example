package com.zeluciojr.cae_framework_example.core.use_cases.wipe_inactive_users;

import com.cae.use_cases.authorization.annotations.ProtectedUseCase;
import com.cae.use_cases.specifics.runnables.RunnableUseCase;

/**
 * <h1> An example of RunnableUseCase.
 *
 * <p> Its execution method is the RunnableUseCase::execute, which has no input nor output.</p>
 *
 * <p> The actual implementation with its internal rules is located at the WipeInactiveUsersUseCase::applyInternalLogic method. </p>
 *
 * <p> Before the cae-framework calls the RunnableUseCase::applyInternalLogic method, if the Use Case is protected,
 * the UseCase::handleAuthorization method will be called. Once this is a protected Use Case, it will actually check whether or not the
 * UseCaseExecutionCorrelation's Actor has the required scopes to execute the use case. </p>
 */
@ProtectedUseCase(scope = {"delete:users"})
public abstract class WipeInactiveUsersUseCase extends RunnableUseCase { }
