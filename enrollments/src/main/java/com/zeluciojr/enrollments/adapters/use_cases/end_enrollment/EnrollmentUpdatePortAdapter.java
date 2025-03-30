package com.zeluciojr.enrollments.adapters.use_cases.end_enrollment;

import com.cae.mapped_exceptions.specifics.InternalMappedException;
import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.adapters.dependencies.db.EnrollmentsTable;
import com.zeluciojr.enrollments.adapters.dependencies.db.ExperiencesTable;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.EnrollmentsTableRepository;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.ExperiencesTableRepository;
import com.zeluciojr.enrollments.core.entities.Enrollment;
import com.zeluciojr.enrollments.core.use_cases.end_enrollment.implementations.ports.EnrollmentUpdatePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnrollmentUpdatePortAdapter extends EnrollmentUpdatePort {

    public static final EnrollmentUpdatePort SINGLETON = new EnrollmentUpdatePortAdapter(
            EnrollmentsTableRepository.SINGLETON,
            ExperiencesTableRepository.SINGLETON
    );

    private final EnrollmentsTableRepository enrollmentsTableRepository;
    private final ExperiencesTableRepository experiencesTableRepository;

    @Override
    protected void executeLogic(Enrollment input, ExecutionContext correlation) {
        var previousXp = input.getPreviousExperience().orElseThrow(() -> new InternalMappedException(
                "Couldn't update enrollment",
                "The enrollment object must return a previous experience"
        ));
        this.experiencesTableRepository.update(new ExperiencesTable(previousXp));
        this.enrollmentsTableRepository.update(new EnrollmentsTable(input));
    }
}
