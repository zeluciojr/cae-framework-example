package com.zeluciojr.enrollments.core.use_cases.end_enrollment.implementations;

import com.cae.mapped_exceptions.specifics.NotFoundMappedException;
import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.core.entities.Enrollment;
import com.zeluciojr.enrollments.core.use_cases.end_enrollment.EndEnrollmentUseCase;
import com.zeluciojr.enrollments.core.use_cases.end_enrollment.implementations.ports.EnrollmentRetrievalByIdPort;
import com.zeluciojr.enrollments.core.use_cases.end_enrollment.implementations.ports.EnrollmentUpdatePort;
import com.zeluciojr.enrollments.core.use_cases.end_enrollment.io.inputs.EndEnrollmentUseCaseInput;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * This class implements the EndEnrollmentUseCase, containing all the internal logic for its operation.
 * Since it is a FunctionUseCase, its purpose is to supply something, based on its input.
 * <p>
 * Important: Avoid hardcoding any sensitive information in this code. The auto-documentation
 * process will analyze this section of the source code if the GPT mode is enabled.
 */
@RequiredArgsConstructor
public class EndEnrollmentUseCaseImplementation extends EndEnrollmentUseCase {

    private final EnrollmentRetrievalByIdPort enrollmentRetrievalByIdPort;
    private final EnrollmentUpdatePort enrollmentUpdatePort;

    @Override
    protected void applyInternalLogic(
            EndEnrollmentUseCaseInput input,
            ExecutionContext context) {
        var enrollmentToEnd = this.getEnrollmentBy(input.getEnrollmentId(), context);
        enrollmentToEnd.end();
        this.update(enrollmentToEnd, context);
    }

    private Enrollment getEnrollmentBy(String enrollmentId, ExecutionContext context) {
        return this.enrollmentRetrievalByIdPort.executePortOn(UUID.fromString(enrollmentId), context)
                .orElseThrow(() -> new NotFoundMappedException(
                        "Couldn't find enrollment of ID " + enrollmentId
                ));
    }

    private void update(Enrollment enrollmentToEnd, ExecutionContext context) {
        this.enrollmentUpdatePort.executePortOn(enrollmentToEnd, context);
    }
}
