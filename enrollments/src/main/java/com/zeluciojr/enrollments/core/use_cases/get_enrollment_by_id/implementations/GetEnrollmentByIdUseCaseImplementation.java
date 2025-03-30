package com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id.implementations;

import com.cae.mapped_exceptions.specifics.NotFoundMappedException;
import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.core.entities.Enrollment;
import com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id.GetEnrollmentByIdUseCase;
import com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id.implementations.ports.EnrollmentRetrievalByIdPort;
import com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id.io.inputs.GetEnrollmentByIdUseCaseInput;
import com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id.io.outputs.GetEnrollmentByIdUseCaseOutput;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * This class implements the GetEnrollmentByIdUseCase, containing all the internal logic for its operation.
 * Since it is a FunctionUseCase, its purpose is to supply something, based on its input.
 * <p>
 * Important: Avoid hardcoding any sensitive information in this code. The auto-documentation
 * process will analyze this section of the source code if the GPT mode is enabled.
 */
@RequiredArgsConstructor
public class GetEnrollmentByIdUseCaseImplementation extends GetEnrollmentByIdUseCase {

    private final EnrollmentRetrievalByIdPort enrollmentRetrievalByIdPort;

    @Override
    protected GetEnrollmentByIdUseCaseOutput applyInternalLogic(
            GetEnrollmentByIdUseCaseInput input,
            ExecutionContext context) {
        var enrollment = this.getEnrollmentBy(input.getEnrollmentId(), context);
        return new GetEnrollmentByIdUseCaseOutput(enrollment);
    }

    private Enrollment getEnrollmentBy(String enrollmentId, ExecutionContext context) {
        return this.enrollmentRetrievalByIdPort.executePortOn(UUID.fromString(enrollmentId), context)
                .orElseThrow(() -> new NotFoundMappedException(
                        "Couldn't find enrollment of ID " + enrollmentId
                ));
    }
}
