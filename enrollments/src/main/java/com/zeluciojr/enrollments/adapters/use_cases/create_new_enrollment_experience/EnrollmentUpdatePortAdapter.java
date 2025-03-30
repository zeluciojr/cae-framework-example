package com.zeluciojr.enrollments.adapters.use_cases.create_new_enrollment_experience;

import com.cae.mapped_exceptions.specifics.InternalMappedException;
import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.adapters.dependencies.db.ExperiencesTable;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.ExperiencesTableRepository;
import com.zeluciojr.enrollments.core.entities.Enrollment;
import com.zeluciojr.enrollments.core.use_cases.create_new_enrollment_experience.implementations.ports.EnrollmentUpdatePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnrollmentUpdatePortAdapter extends EnrollmentUpdatePort {

    public static final EnrollmentUpdatePort SINGLETON = new EnrollmentUpdatePortAdapter(
            ExperiencesTableRepository.SINGLETON
    );

    private final ExperiencesTableRepository experiencesTableRepository;

    @Override
    protected void executeLogic(Enrollment input, ExecutionContext correlation) {
        var previousXp = input.getPreviousExperience().orElseThrow(() -> new InternalMappedException(
                "Couldn't update enrollment",
                "The previous XP must be present before attempting to update the experience list"
        ));
        var currentXp = input.getCurrentExperience().orElseThrow(() -> new InternalMappedException(
                "Couldn't update enrollment",
                "The new, current, XP must be present before attempting to update the experience list"
        ));
        this.experiencesTableRepository.update(new ExperiencesTable(previousXp));
        this.experiencesTableRepository.update(new ExperiencesTable(currentXp));
    }
}
