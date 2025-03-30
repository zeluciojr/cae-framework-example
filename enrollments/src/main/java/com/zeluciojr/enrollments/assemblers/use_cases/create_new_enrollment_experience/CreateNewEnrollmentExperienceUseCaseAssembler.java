package com.zeluciojr.enrollments.assemblers.use_cases.create_new_enrollment_experience;

import com.cae.use_cases.assemblers.UseCaseAssembler;
import com.zeluciojr.enrollments.adapters.use_cases.create_new_enrollment_experience.EnrollmentRetrievalByIdPortAdapter;
import com.zeluciojr.enrollments.adapters.use_cases.create_new_enrollment_experience.EnrollmentUpdatePortAdapter;
import com.zeluciojr.enrollments.adapters.use_cases.create_new_enrollment_experience.RoleRetrievalByIdPortAdapter;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.CreateNewEnrollmentExperienceUseCase;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.implementations.CreateNewEnrollmentExperienceUseCaseImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class creates a singleton object for the CreateNewEnrollmentExperienceUseCase class.
 * You can use this class to access the instance of this use case anywhere.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateNewEnrollmentExperienceUseCaseAssembler implements UseCaseAssembler<CreateNewEnrollmentExperienceUseCase>{

    public static final CreateNewEnrollmentExperienceUseCaseAssembler SINGLETON_ASSEMBLER = new CreateNewEnrollmentExperienceUseCaseAssembler();

    public static final CreateNewEnrollmentExperienceUseCase V1 = new CreateNewEnrollmentExperienceUseCaseImplementation(
            EnrollmentRetrievalByIdPortAdapter.SINGLETON,
            RoleRetrievalByIdPortAdapter.SINGLETON,
            EnrollmentUpdatePortAdapter.SINGLETON
    );

    @Override
    public CreateNewEnrollmentExperienceUseCase getDefaultAssembledInstance(){
        return V1;
    }
}