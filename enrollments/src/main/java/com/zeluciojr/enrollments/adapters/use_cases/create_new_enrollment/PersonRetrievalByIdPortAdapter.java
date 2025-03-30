package com.zeluciojr.enrollments.adapters.use_cases.create_new_enrollment;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.adapters.dependencies.db.PeopleTable;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.PeopleTableRepository;
import com.zeluciojr.enrollments.core.entities.Person;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.implementations.ports.PersonRetrievalByIdPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class PersonRetrievalByIdPortAdapter extends PersonRetrievalByIdPort {

    public static final PersonRetrievalByIdPort SINGLETON = new PersonRetrievalByIdPortAdapter(
            PeopleTableRepository.SINGLETON
    );

    private final PeopleTableRepository peopleTableRepository;

    @Override
    protected Optional<Person> executeLogic(UUID input, ExecutionContext correlation) {
        return this.peopleTableRepository.findById(input.toString()).map(PeopleTable::getEntity);
    }

}
