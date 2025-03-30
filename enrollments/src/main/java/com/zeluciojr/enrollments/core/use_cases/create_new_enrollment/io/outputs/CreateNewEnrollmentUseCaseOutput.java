package com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.io.outputs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This class maps the output fields for your use case.
 */
@Getter
@RequiredArgsConstructor
public class CreateNewEnrollmentUseCaseOutput {

    private final String newEnrollmentId;

}
