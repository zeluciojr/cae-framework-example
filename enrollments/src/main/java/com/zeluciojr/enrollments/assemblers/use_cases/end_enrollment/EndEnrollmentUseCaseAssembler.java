package com.zeluciojr.enrollments.assemblers.use_cases.end_enrollment;

import com.cae.use_cases.assemblers.UseCaseAssembler;
import com.zeluciojr.enrollments.adapters.use_cases.end_enrollment.EnrollmentRetrievalByIdPortAdapter;
import com.zeluciojr.enrollments.adapters.use_cases.end_enrollment.EnrollmentUpdatePortAdapter;
import com.zeluciojr.enrollments.core.use_cases.end_enrollment.EndEnrollmentUseCase;
import com.zeluciojr.enrollments.core.use_cases.end_enrollment.implementations.EndEnrollmentUseCaseImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class creates a singleton object for the EndEnrollmentUseCase class.
 * You can use this class to access the instance of this use case anywhere.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EndEnrollmentUseCaseAssembler implements UseCaseAssembler<EndEnrollmentUseCase>{

    public static final EndEnrollmentUseCaseAssembler SINGLETON_ASSEMBLER = new EndEnrollmentUseCaseAssembler();

    public static final EndEnrollmentUseCase V1 = new EndEnrollmentUseCaseImplementation(
            EnrollmentRetrievalByIdPortAdapter.SINGLETON,
            EnrollmentUpdatePortAdapter.SINGLETON
    );

    @Override
    public EndEnrollmentUseCase getDefaultAssembledInstance(){
        return V1;
    }
}