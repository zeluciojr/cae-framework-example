package com.zeluciojr.enrollments.core.entities;

import com.cae.entities.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Experience implements Entity, UUIDBasedEntity, Comparable<Experience> {

    private UUID id;
    private Role role;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Enrollment enrollment;

    public static Experience createNewOneAs(Role role) {
        return Experience.builder()
                .id(UUID.randomUUID())
                .startedAt(LocalDateTime.now())
                .role(role)
                .build();
    }

    @Override
    public int compareTo(Experience otherExperience) {
        return this.startedAt.compareTo(otherExperience.startedAt);
    }

    public void finish() {
        this.endedAt = LocalDateTime.now();
    }

    public boolean isActive(){
        return this.endedAt == null;
    }

}
