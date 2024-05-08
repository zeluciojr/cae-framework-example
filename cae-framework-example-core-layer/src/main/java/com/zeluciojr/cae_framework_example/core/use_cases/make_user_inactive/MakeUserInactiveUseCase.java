package com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive;

import com.cae.use_cases.authorization.annotations.ProtectedUseCase;
import com.cae.use_cases.specifics.consumers.ConsumerUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.make_user_inactive.io.inputs.MakeUserInactiveUseCaseInput;

/**
 * <h1> An example of ConsumerUseCase.
 *
 * <p> Its execution method is the ConsumerUseCase::execute, which accepts input but supplies no output.
 * Its input must extend the UseCaseInput type, just like any other Use Case type that has Input. </p>
 *
 * <p> The actual implementation with its internal rules is located at the MakeUserInactiveUseCase::applyInternalLogic method. </p>
 *
 * <p> Before the cae-framework calls the ConsumerUseCase::applyInternalLogic method,
 * it will call the UseCaseInput::validateProperties: if the input object's attributes are complacent with their annotation requirements,
 * the cae-framework will call the ConsumerUseCase::applyInternalLogic method. </p>
 *
 * <p> If the Use Case is protected, the UseCase::handleAuthorization method will also be called.
 * Since this is a protected use case, during its execution it is required to instantiate the actor within the UseCaseExecutionCorrelation object,
 * so the framework will check whether or not the actor has the necessary scopes to execute the use case.</p>
 */
@ProtectedUseCase(scope = "update:users")
public abstract class MakeUserInactiveUseCase extends ConsumerUseCase<MakeUserInactiveUseCaseInput> {
}
