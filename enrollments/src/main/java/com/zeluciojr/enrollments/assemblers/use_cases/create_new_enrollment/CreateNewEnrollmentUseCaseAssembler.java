package com.zeluciojr.enrollments.assemblers.use_cases.create_new_enrollment;

import com.cae.use_cases.assemblers.UseCaseAssembler;
import com.zeluciojr.enrollments.adapters.use_cases.create_new_enrollment.NewEnrollmentPersistencePortAdapter;
import com.zeluciojr.enrollments.adapters.use_cases.create_new_enrollment.PersonRetrievalByIdPortAdapter;
import com.zeluciojr.enrollments.adapters.use_cases.create_new_enrollment.RoleRetrievalByIdPortAdapter;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.CreateNewEnrollmentUseCase;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.implementations.CreateNewEnrollmentUseCaseImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class creates a singleton object for the CreateNewEnrollmentUseCase class.
 * You can use this class to access the instance of this use case anywhere.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateNewEnrollmentUseCaseAssembler implements UseCaseAssembler<CreateNewEnrollmentUseCase>{

    public static final CreateNewEnrollmentUseCaseAssembler SINGLETON_ASSEMBLER = new CreateNewEnrollmentUseCaseAssembler();

    public static final CreateNewEnrollmentUseCase V1 = new CreateNewEnrollmentUseCaseImplementation(
            PersonRetrievalByIdPortAdapter.SINGLETON,
            RoleRetrievalByIdPortAdapter.SINGLETON,
            NewEnrollmentPersistencePortAdapter.SINGLETON
    );

    @Override
    public CreateNewEnrollmentUseCase getDefaultAssembledInstance(){
        return V1;
    }
}