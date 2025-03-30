package com.zeluciojr.enrollments.adapters.use_cases.create_new_enrollment;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.adapters.dependencies.db.EnrollmentsTable;
import com.zeluciojr.enrollments.adapters.dependencies.db.ExperiencesTable;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.EnrollmentsTableRepository;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.ExperiencesTableRepository;
import com.zeluciojr.enrollments.core.entities.Enrollment;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment.implementations.ports.NewEnrollmentPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NewEnrollmentPersistencePortAdapter extends NewEnrollmentPersistencePort {

    public static final NewEnrollmentPersistencePort SINGLETON = new NewEnrollmentPersistencePortAdapter(
            EnrollmentsTableRepository.SINGLETON,
            ExperiencesTableRepository.SINGLETON
    );

    private final EnrollmentsTableRepository enrollmentsTableRepository;
    private final ExperiencesTableRepository experiencesTableRepository;

    @Override
    protected void executeLogic(Enrollment input, ExecutionContext correlation) {
        this.enrollmentsTableRepository.createNew(new EnrollmentsTable(input));
        input.getExperiences()
                .stream()
                .map(ExperiencesTable::new)
                .forEach( this.experiencesTableRepository::createNew);
    }
}
