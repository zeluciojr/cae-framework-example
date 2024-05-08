package com.zeluciojr.cae_framework_example.core.use_cases.create_new_user;

import com.cae.use_cases.specifics.functions.FunctionUseCase;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.inputs.CreateNewUserUseCaseInput;
import com.zeluciojr.cae_framework_example.core.use_cases.create_new_user.io.outputs.CreateNewUserUseCaseOutput;

/**
 * <h1> An example of FunctionUseCase.
 *
 * <p> Its execution method is the FunctionUseCase::execute, which accepts input and supplies output.
 * Its input must extend the UseCaseInput type, just like any other Use Case type that has Input.
 * Its output is free not to extend anything. </p>
 *
 * <p> The actual implementation with its internal rules is located at the CreateNewUserUseCaseImplementation::applyInternalLogic method. </p>
 *
 * <p> Before the cae-framework calls the FunctionUseCase::applyInternalLogic method,
 * it will call the UseCaseInput::validateProperties: if the input object's attributes are complacent with their annotation requirements,
 * the cae-framework will call the FunctionUseCase::applyInternalLogic method. </p>
 *
 * <p> If the Use Case is protected, the UseCase::handleAuthorization method will also be called.
 * Since this isn't a protected Use Case, it will only call the input validation. </p>
 */
public abstract class CreateNewUserUseCase extends FunctionUseCase<CreateNewUserUseCaseInput, CreateNewUserUseCaseOutput> {
}
