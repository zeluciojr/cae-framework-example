package com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id.io.outputs;

import com.zeluciojr.enrollments.core.entities.Enrollment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This class maps the output fields for your use case.
 */
@Getter
@RequiredArgsConstructor
public class GetEnrollmentByIdUseCaseOutput {

    private final Enrollment enrollment;

}
