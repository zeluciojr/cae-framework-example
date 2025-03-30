package com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience;

import com.cae.use_cases.FunctionUseCase;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.io.inputs.CreateNewEnrollmentExperienceUseCaseInput;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.io.outputs.CreateNewEnrollmentExperienceUseCaseOutput;

/**
 * This is the contract of the CreateNewEnrollmentExperienceUseCase.
 * Its input is mapped within the CreateNewEnrollmentExperienceUseCaseInput class.
 * The output is mapped within the CreateNewEnrollmentExperienceUseCaseOutput class.
 */
public abstract class CreateNewEnrollmentExperienceUseCase extends FunctionUseCase<
        CreateNewEnrollmentExperienceUseCaseInput,
        CreateNewEnrollmentExperienceUseCaseOutput> {}
