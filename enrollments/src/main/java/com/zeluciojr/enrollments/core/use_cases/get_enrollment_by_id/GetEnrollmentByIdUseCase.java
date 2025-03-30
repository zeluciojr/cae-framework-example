package com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id;

import com.cae.use_cases.FunctionUseCase;
import com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id.io.inputs.GetEnrollmentByIdUseCaseInput;
import com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id.io.outputs.GetEnrollmentByIdUseCaseOutput;

/**
 * This is the contract of the GetEnrollmentByIdUseCase.
 * Its input is mapped within the GetEnrollmentByIdUseCaseInput class.
 * The output is mapped within the GetEnrollmentByIdUseCaseOutput class.
 */
public abstract class GetEnrollmentByIdUseCase extends FunctionUseCase<
        GetEnrollmentByIdUseCaseInput,
        GetEnrollmentByIdUseCaseOutput> {}
