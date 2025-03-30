package com.zeluciojr.enrollments.core.use_cases.end_enrollment;

import com.cae.use_cases.ConsumerUseCase;
import com.zeluciojr.enrollments.core.use_cases.end_enrollment.io.inputs.EndEnrollmentUseCaseInput;

/**
 * This is the contract of the EndEnrollmentUseCase.
 * Its input is mapped within the EndEnrollmentUseCaseInput class.
 * The output is mapped within the EndEnrollmentUseCaseOutput class.
 */
public abstract class EndEnrollmentUseCase extends ConsumerUseCase<EndEnrollmentUseCaseInput> {}
