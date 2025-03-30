package com.zeluciojr.enrollments.core.entities;

import com.cae.entities.Entity;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment implements Entity, UUIDBasedEntity {

    private UUID id;
    private Person person;
    private Boolean active;

    @Builder.Default
    private List<Experience> experiences = new ArrayList<>();

    public static Enrollment createNewOneFor(Person person, Role role){
        var newEnrollment = Enrollment.builder()
                .id(UUID.randomUUID())
                .person(person)
                .active(true)
                .build();
        newEnrollment.addNewExperienceAs(role);
        return newEnrollment;
    }

    public static Enrollment of(UUID id){
        return Enrollment.builder()
                .id(id)
                .build();
    }

    public Experience addNewExperienceAs(Role role){
        this.getCurrentExperience().ifPresent(Experience::finish);
        var newExperience = Experience.createNewOneAs(role);
        newExperience.setEnrollment(this);
        this.experiences.add(newExperience);
        return newExperience;
    }

    public Optional<Experience> getCurrentExperience(){
        return this.experiences.stream()
                .sorted()
                .filter(Experience::isActive)
                .findFirst();
    }

    public Optional<Experience> getPreviousExperience() {
        var numberOfExperiences = this.experiences.size();
        if (this.experiences.isEmpty() || (numberOfExperiences == 1 && this.experiences.get(0).isActive()))
            return Optional.empty();
        Collections.sort(this.experiences);
        Collections.reverse(this.experiences);
        var hasOnlyOneXPWhichIsInactive = numberOfExperiences == 1;
        var indexOfPreviousXp = numberOfExperiences - (hasOnlyOneXPWhichIsInactive? 1 : 2);
        var previousXp = this.experiences.get(indexOfPreviousXp);
        return Optional.of(previousXp);
    }

    public void end(){
        this.getCurrentExperience().ifPresent(Experience::finish);
        this.active = false;
    }
}
