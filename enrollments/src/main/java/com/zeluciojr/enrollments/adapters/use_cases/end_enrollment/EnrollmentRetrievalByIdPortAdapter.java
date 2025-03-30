package com.zeluciojr.enrollments.adapters.use_cases.end_enrollment;

import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.adapters.dependencies.db.ExperiencesTable;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.EnrollmentsTableRepository;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.ExperiencesTableRepository;
import com.zeluciojr.enrollments.core.entities.Enrollment;
import com.zeluciojr.enrollments.core.use_cases.end_enrollment.implementations.ports.EnrollmentRetrievalByIdPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class EnrollmentRetrievalByIdPortAdapter extends EnrollmentRetrievalByIdPort {

    public static final EnrollmentRetrievalByIdPort SINGLETON = new EnrollmentRetrievalByIdPortAdapter(
            EnrollmentsTableRepository.SINGLETON,
            ExperiencesTableRepository.SINGLETON
    );

    private final EnrollmentsTableRepository enrollmentsTableRepository;
    private final ExperiencesTableRepository experiencesTableRepository;

    @Override
    protected Optional<Enrollment> executeLogic(UUID input, ExecutionContext correlation) {
        return this.enrollmentsTableRepository.findById(input.toString()).map(enrollment -> {
            var inEntityFormat = enrollment.getEntity();
            this.experiencesTableRepository.findAllByEnrollmentId(enrollment.getPrimaryKey())
                    .stream()
                    .map(ExperiencesTable::getEntity)
                    .forEach(experience -> inEntityFormat.getExperiences().add(experience));
            return inEntityFormat;
        });
    }
}
