package com.zeluciojr.enrollments.adapters.use_cases.get_enrollment_by_id;

import com.cae.mapped_exceptions.specifics.InternalMappedException;
import com.cae.use_cases.contexts.ExecutionContext;
import com.zeluciojr.enrollments.adapters.dependencies.db.PeopleTable;
import com.zeluciojr.enrollments.adapters.dependencies.db.RolesTable;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.EnrollmentsTableRepository;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.ExperiencesTableRepository;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.PeopleTableRepository;
import com.zeluciojr.enrollments.adapters.dependencies.db.repositories.RolesTableRepository;
import com.zeluciojr.enrollments.core.entities.Enrollment;
import com.zeluciojr.enrollments.core.entities.Experience;
import com.zeluciojr.enrollments.core.entities.Person;
import com.zeluciojr.enrollments.core.entities.Role;
import com.zeluciojr.enrollments.core.use_cases.get_enrollment_by_id.implementations.ports.EnrollmentRetrievalByIdPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class EnrollmentRetrievalByIdPortAdapter extends EnrollmentRetrievalByIdPort {

    public static final EnrollmentRetrievalByIdPort SINGLETON = new EnrollmentRetrievalByIdPortAdapter(
            EnrollmentsTableRepository.SINGLETON,
            ExperiencesTableRepository.SINGLETON,
            PeopleTableRepository.SINGLETON,
            RolesTableRepository.SINGLETON
    );

    private final EnrollmentsTableRepository enrollmentsTableRepository;
    private final ExperiencesTableRepository experiencesTableRepository;
    private final PeopleTableRepository peopleTableRepository;
    private final RolesTableRepository rolesTableRepository;


    @Override
    protected Optional<Enrollment> executeLogic(UUID input, ExecutionContext correlation) {
        return this.enrollmentsTableRepository.findById(input.toString()).map(enrollment -> {
            var experiences = this.getExperiencesFor(enrollment.getPrimaryKey());
            var person = this.getPersonFor(enrollment.getPersonId());
            var inEntityFormat = enrollment.getEntity();
            inEntityFormat.setExperiences(experiences);
            inEntityFormat.setPerson(person);
            return inEntityFormat;
        });
    }

    private List<Experience> getExperiencesFor(String enrollmentId) {
        return this.experiencesTableRepository.findAllByEnrollmentId(enrollmentId)
                .stream()
                .map(inTableFormat -> {
                    var role = this.getRoleBy(inTableFormat.getRoleId());
                    var inEntityFormat = inTableFormat.getEntity();
                    inEntityFormat.setRole(role);
                    return inEntityFormat;
                })
                .toList();
    }

    private Role getRoleBy(String roleId) {
        return this.rolesTableRepository.findById(roleId)
                .map(RolesTable::getEntity)
                .orElseThrow(() -> new InternalMappedException(
                        "Couldn't load details of enrollment",
                        "Couldn't find the role of one of its experiences. ID of the missing role: " + roleId
                ));
    }

    private Person getPersonFor(String personId) {
        return this.peopleTableRepository.findById(personId)
                .map(PeopleTable::getEntity)
                .orElseThrow(() -> new InternalMappedException(
                        "Couldn't load details of enrollment",
                        "Couldn't the owner of it (the person). ID of the missing person: " + personId
                ));
    }
}
