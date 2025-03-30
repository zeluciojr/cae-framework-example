package com.zeluciojr.enrollments.adapters.use_cases.create_new_person;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.adapters.dependencies.db.PeopleTable;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.PeopleTableRepository;
import com.zeluciojr.enrollments.core.entities.Person;
import com.zeluciojr.enrollments.core.use_cases.create_new_person.implementations.ports.NewPersonPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NewPersonPersistencePortAdapter extends NewPersonPersistencePort {

    public static final NewPersonPersistencePort SINGLETON = new NewPersonPersistencePortAdapter(
            PeopleTableRepository.SINGLETON
    );

    private final PeopleTableRepository peopleTableRepository;

    @Override
    protected void executeLogic(Person input, ExecutionContext correlation) {
        this.peopleTableRepository.createNew(new PeopleTable(input));
    }
}
