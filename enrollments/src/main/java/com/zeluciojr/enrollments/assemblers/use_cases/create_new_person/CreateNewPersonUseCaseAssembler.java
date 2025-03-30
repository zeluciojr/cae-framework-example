package com.zeluciojr.enrollments.assemblers.use_cases.create_new_person;

import com.cae.use_cases.assemblers.UseCaseAssembler;
import com.zeluciojr.enrollments.adapters.use_cases.create_new_person.NewPersonPersistencePortAdapter;
import com.zeluciojr.enrollments.adapters.use_cases.create_new_person.PersonRetrievalByLegalIdPortAdapter;
import com.zeluciojr.enrollments.core.use_cases.create_new_person.CreateNewPersonUseCase;
import com.zeluciojr.enrollments.core.use_cases.create_new_person.implementations.CreateNewPersonUseCaseImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class creates a singleton object for the CreateNewPersonUseCase class.
 * You can use this class to access the instance of this use case anywhere.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateNewPersonUseCaseAssembler implements UseCaseAssembler<CreateNewPersonUseCase>{

    public static final CreateNewPersonUseCaseAssembler SINGLETON_ASSEMBLER = new CreateNewPersonUseCaseAssembler();

    public static final CreateNewPersonUseCase V1 = new CreateNewPersonUseCaseImplementation(
            PersonRetrievalByLegalIdPortAdapter.SINGLETON,
            NewPersonPersistencePortAdapter.SINGLETON
    );

    @Override
    public CreateNewPersonUseCase getDefaultAssembledInstance(){
        return V1;
    }
}