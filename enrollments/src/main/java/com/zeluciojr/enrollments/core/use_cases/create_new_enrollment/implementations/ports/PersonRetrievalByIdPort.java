package com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.implementations.ports;

import com.cae.ports.FunctionPort;
import com.zeluciojr.enrollments.core.entities.Person;

import java.util.Optional;
import java.util.UUID;

public abstract class PersonRetrievalByIdPort extends FunctionPort<UUID, Optional<Person>> {
}
