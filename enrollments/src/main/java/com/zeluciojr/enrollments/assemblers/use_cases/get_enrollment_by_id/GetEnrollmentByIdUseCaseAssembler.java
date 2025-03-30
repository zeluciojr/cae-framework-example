package com.zeluciojr.enrollments.assemblers.use_cases.get_enrollment_by_id;

import com.zeluciojr.enrollments.adapters.use_cases.get_enrollment_by_id.EnrollmentRetrievalByIdPortAdapter;
import com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id.GetEnrollmentByIdUseCase;
import com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id.implementations.GetEnrollmentByIdUseCaseImplementation;
import com.cae.use_cases.assemblers.UseCaseAssembler;
import com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id.implementations.ports.EnrollmentRetrievalByIdPort;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class creates a singleton object for the GetEnrollmentByIdUseCase class.
 * You can use this class to access the instance of this use case anywhere.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetEnrollmentByIdUseCaseAssembler implements UseCaseAssembler<GetEnrollmentByIdUseCase>{

    public static final GetEnrollmentByIdUseCaseAssembler SINGLETON_ASSEMBLER = new GetEnrollmentByIdUseCaseAssembler();

    public static final GetEnrollmentByIdUseCase V1 = new GetEnrollmentByIdUseCaseImplementation(
            EnrollmentRetrievalByIdPortAdapter.SINGLETON
    );

    @Override
    public GetEnrollmentByIdUseCase getDefaultAssembledInstance(){
        return V1;
    }
}