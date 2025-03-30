package com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.implementations;

import com.cae.mapped_exceptions.specifics.NotFoundMappedException;
import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.core.entities.Enrollment;
import com.zeluciojr.enrollments.core.entities.Role;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.CreateNewEnrollmentExperienceUseCase;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.implementations.ports.EnrollmentRetrievalByIdPort;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.implementations.ports.EnrollmentUpdatePort;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.implementations.ports.RoleRetrievalByIdPort;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.io.inputs.CreateNewEnrollmentExperienceUseCaseInput;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.io.outputs.CreateNewEnrollmentExperienceUseCaseOutput;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * This class implements the CreateNewEnrollmentExperienceUseCase, containing all the internal logic for its operation.
 * Since it is a FunctionUseCase, its purpose is to supply something, based on its input.
 * <p>
 * Important: Avoid hardcoding any sensitive information in this code. The auto-documentation
 * process will analyze this section of the source code if the GPT mode is enabled.
 */
@RequiredArgsConstructor
public class CreateNewEnrollmentExperienceUseCaseImplementation extends CreateNewEnrollmentExperienceUseCase {

    private final EnrollmentRetrievalByIdPort enrollmentRetrievalByIdPort;
    private final RoleRetrievalByIdPort roleRetrievalByIdPort;
    private final EnrollmentUpdatePort enrollmentUpdatePort;

    @Override
    protected CreateNewEnrollmentExperienceUseCaseOutput applyInternalLogic(
            CreateNewEnrollmentExperienceUseCaseInput input,
            ExecutionContext context) {
        var enrollment = this.findEnrollmentBy(input.getEnrollmentId(), context);
        var role = this.findRoleBy(input.getRoleId(), context);
        var newXp = enrollment.addNewExperienceAs(role);
        this.update(enrollment, context);
        return new CreateNewEnrollmentExperienceUseCaseOutput(newXp.getId().toString());
    }

    private Enrollment findEnrollmentBy(String enrollmentId, ExecutionContext context) {
        return this.enrollmentRetrievalByIdPort.executePortOn(UUID.fromString(enrollmentId), context)
                .orElseThrow(() -> new NotFoundMappedException(
                        "Couldn't find enrollment by ID of " + enrollmentId
                ));
    }

    private Role findRoleBy(String roleId, ExecutionContext context) {
        return this.roleRetrievalByIdPort.executePortOn(UUID.fromString(roleId), context)
                .orElseThrow(() -> new NotFoundMappedException(
                        "Couldn't find role by ID of " + roleId
                ));
    }

    private void update(
            Enrollment enrollment,
            ExecutionContext context) {
        this.enrollmentUpdatePort.executePortOn(enrollment, context);
    }
}
