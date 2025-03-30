package com.zeluciojr.enrollments.core.use_cases.create_new_person.implementations.ports;

import com.cae.ports.FunctionPort;
import com.zeluciojr.enrollments.core.entities.LegalId;
import com.zeluciojr.enrollments.core.entities.Person;

import java.util.Optional;

public abstract class PersonRetrievalByLegalIdPort extends FunctionPort<LegalId, Optional<Person>> {
}
