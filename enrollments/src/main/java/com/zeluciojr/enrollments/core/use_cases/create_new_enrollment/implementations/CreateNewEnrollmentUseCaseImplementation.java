package com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.implementations;

import com.cae.mapped_exceptions.specifics.NotFoundMappedException;
import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.core.entities.Enrollment;
import com.zeluciojr.enrollments.core.entities.Person;
import com.zeluciojr.enrollments.core.entities.Role;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.CreateNewEnrollmentUseCase;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.implementations.ports.NewEnrollmentPersistencePort;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.implementations.ports.PersonRetrievalByIdPort;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.implementations.ports.RoleRetrievalByIdPort;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.io.inputs.CreateNewEnrollmentUseCaseInput;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.io.outputs.CreateNewEnrollmentUseCaseOutput;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * This class implements the CreateNewEnrollmentUseCase, containing all the internal logic for its operation.
 * Since it is a FunctionUseCase, its purpose is to supply something, based on its input.
 * <p>
 * Important: Avoid hardcoding any sensitive information in this code. The auto-documentation
 * process will analyze this section of the source code if the GPT mode is enabled.
 */
@RequiredArgsConstructor
public class CreateNewEnrollmentUseCaseImplementation extends CreateNewEnrollmentUseCase {

    private final PersonRetrievalByIdPort personRetrievalByIdPort;
    private final RoleRetrievalByIdPort roleRetrievalByIdPort;
    private final NewEnrollmentPersistencePort newEnrollmentPersistencePort;

    @Override
    protected CreateNewEnrollmentUseCaseOutput applyInternalLogic(
            CreateNewEnrollmentUseCaseInput input,
            ExecutionContext context) {
        var personToEnroll = this.findPersonBy(input.getPersonId(), context);
        var roleToAssign = this.findRoleBy(input.getRoleId(), context);
        var newEnrollment = Enrollment.createNewOneFor(personToEnroll, roleToAssign);
        this.save(newEnrollment, context);
        return new CreateNewEnrollmentUseCaseOutput(newEnrollment.getId().toString());
    }

    private Person findPersonBy(String personId, ExecutionContext context) {
        return this.personRetrievalByIdPort.executePortOn(UUID.fromString(personId), context)
                .orElseThrow(() -> new NotFoundMappedException(
                        "Couldn't find the person to enroll",
                        "ID provided was '" + personId + "'"
                ));
    }

    private Role findRoleBy(String roleId, ExecutionContext context) {
        return this.roleRetrievalByIdPort.executePortOn(UUID.fromString(roleId), context)
                .orElseThrow(() -> new NotFoundMappedException(
                        "Couldn't find the role to assign",
                        "ID provided was '" + roleId + "'"
                ));
    }

    private void save(Enrollment newEnrollment, ExecutionContext context) {
        this.newEnrollmentPersistencePort.executePortOn(newEnrollment, context);
    }

}
