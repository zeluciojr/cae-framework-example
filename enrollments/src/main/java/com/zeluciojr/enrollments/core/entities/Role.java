package com.zeluciojr.enrollments.core.entities;

import com.cae.entities.Entity;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Entity, UUIDBasedEntity {

    private UUID id;
    private String name;
    private String description;
    private Boolean active;

    public static Role createNewOne(String name, String description){
        return Role.builder()
                .id(UUID.randomUUID())
                .name(name)
                .description(description)
                .active(true)
                .build();
    }

    public static Role of(UUID id){
        return Role.builder()
                .id(id)
                .build();
    }

}
