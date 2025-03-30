package com.zeluciojr.enrollments.core.entities;

import com.cae.entities.Entity;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Entity, UUIDBasedEntity {

    private UUID id;
    private LegalId legalId;
    private String fullName;
    private String preferredName;

    public static Person of(UUID id){
        return Person.builder()
                .id(id)
                .build();
    }

    public static Person createNewOne(String fullName, String preferredName, LegalId legalId){
        return Person.builder()
                .id(UUID.randomUUID())
                .fullName(fullName)
                .preferredName(preferredName)
                .legalId(legalId)
                .build();
    }

    public static Person createNewOne(String fullName, LegalId legalId){
        return Person.builder()
                .id(UUID.randomUUID())
                .fullName(fullName)
                .legalId(legalId)
                .build();
    }

    public boolean hasPreferredName(){
        return this.preferredName != null && !this.preferredName.isBlank();
    }

}
