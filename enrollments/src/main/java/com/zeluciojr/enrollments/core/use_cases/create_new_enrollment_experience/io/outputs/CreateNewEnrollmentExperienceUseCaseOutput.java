package com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.io.outputs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This class maps the output fields for your use case.
 */
@Getter
@RequiredArgsConstructor
public class CreateNewEnrollmentExperienceUseCaseOutput {

    private final String newExperienceId;

}
