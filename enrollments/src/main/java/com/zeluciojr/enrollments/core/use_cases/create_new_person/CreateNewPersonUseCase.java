package com.zeluciojr.enrollments.core.use_cases.create_new_person;

import com.cae.use_cases.FunctionUseCase;
import com.zeluciojr.enrollments.core.use_cases.create_new_person.io.inputs.CreateNewPersonUseCaseInput;
import com.zeluciojr.enrollments.core.use_cases.create_new_person.io.outputs.CreateNewPersonUseCaseOutput;

/**
 * This is the contract of the CreateNewPersonUseCase.
 * Its input is mapped within the CreateNewPersonUseCaseInput class.
 * The output is mapped within the CreateNewPersonUseCaseOutput class.
 */
public abstract class CreateNewPersonUseCase extends FunctionUseCase<
        CreateNewPersonUseCaseInput,
        CreateNewPersonUseCaseOutput> {}
