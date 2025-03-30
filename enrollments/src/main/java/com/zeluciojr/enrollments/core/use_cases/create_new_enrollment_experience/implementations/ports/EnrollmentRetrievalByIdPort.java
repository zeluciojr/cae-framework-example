package com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.implementations.ports;

import com.cae.ports.FunctionPort;
import com.zeluciojr.enrollments.core.entities.Enrollment;

import java.util.Optional;
import java.util.UUID;

public abstract class EnrollmentRetrievalByIdPort extends FunctionPort<UUID, Optional<Enrollment>> {
}
