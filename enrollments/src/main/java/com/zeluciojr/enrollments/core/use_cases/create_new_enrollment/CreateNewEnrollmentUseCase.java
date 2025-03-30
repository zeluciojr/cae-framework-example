package com.zeluciojr.enrollments.core.use_cases.create_new_enrollment;

import com.cae.use_cases.FunctionUseCase;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.io.inputs.CreateNewEnrollmentUseCaseInput;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.io.outputs.CreateNewEnrollmentUseCaseOutput;

/**
 * This is the contract of the CreateNewEnrollmentUseCase.
 * Its input is mapped within the CreateNewEnrollmentUseCaseInput class.
 * The output is mapped within the CreateNewEnrollmentUseCaseOutput class.
 */
public abstract class CreateNewEnrollmentUseCase extends FunctionUseCase<
        CreateNewEnrollmentUseCaseInput,
        CreateNewEnrollmentUseCaseOutput> {}
