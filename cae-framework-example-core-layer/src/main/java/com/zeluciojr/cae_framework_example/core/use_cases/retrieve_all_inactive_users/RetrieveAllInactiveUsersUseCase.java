package com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users;

import com.cae.use_cases.authorization.annotations.ProtectedUseCase;
import com.cae.use_cases.specifics.suppliers.SupplierUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.retrieve_all_inactive_users.io.outputs.RetrieveAllInactiveUsersUseCaseOutput;

/**
 * <h1> An example of SupplierUseCase.
 *
 * <p> Its execution method is the SupplierUseCase::execute, which has no input and supplies output.
 * Its output is free not to extend any type. </p>
 *
 * <p> The actual implementation with its internal rules is located at the RetrieveAllInactiveUsersUseCase::applyInternalLogic method. </p>
 *
 * <p> Before the cae-framework calls the SupplierUseCase::applyInternalLogic method, if the Use Case is protected,
 * the UseCase::handleAuthorization method will be called. Once this is a protected Use Case, it will actually check whether or not the
 * UseCaseExecutionCorrelation's Actor has the required scopes to execute the use case. </p>
 */
@ProtectedUseCase(scope = {"read-all-inactive:users"})
public abstract class RetrieveAllInactiveUsersUseCase extends SupplierUseCase<RetrieveAllInactiveUsersUseCaseOutput> {
}
