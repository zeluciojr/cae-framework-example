package com.zeluciojr.enrollments.adapters.use_cases.create_new_person;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.adapters.dependencies.db.PeopleTable;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.PeopleTableRepository;
import com.zeluciojr.enrollments.core.entities.LegalId;
import com.zeluciojr.enrollments.core.entities.Person;
import com.zeluciojr.enrollments.core.use_cases.create_new_person.implementations.ports.PersonRetrievalByLegalIdPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PersonRetrievalByLegalIdPortAdapter extends PersonRetrievalByLegalIdPort {

    public static final PersonRetrievalByLegalIdPort SINGLETON = new PersonRetrievalByLegalIdPortAdapter(
            PeopleTableRepository.SINGLETON
    );

    private final PeopleTableRepository peopleTableRepository;

    @Override
    protected Optional<Person> executeLogic(LegalId input, ExecutionContext correlation) {
        return this.peopleTableRepository.findByLegalId(input.getValue()).map(PeopleTable::getEntity);
    }
}
