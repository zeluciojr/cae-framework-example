package com.zeluciojr.enrollments.adapters.dependencies.db.utils;

import com.zeluciojr.enrollments.core.entities.UUIDBasedEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FromUUIDToString {

    public static String getIdAsStringFrom(UUIDBasedEntity uuidBasedEntity){
        return Optional.ofNullable(uuidBasedEntity)
                .flatMap(entity -> Optional.ofNullable(entity.getId()))
                .map(UUID::toString)
                .orElse(null);
    }

}
