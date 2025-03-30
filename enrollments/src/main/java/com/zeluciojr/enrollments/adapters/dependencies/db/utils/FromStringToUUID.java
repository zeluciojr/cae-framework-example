package com.zeluciojr.enrollments.adapters.dependencies.db.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FromStringToUUID {

    public static Optional<UUID> convertToUUID(String uuidInStringFormat){
        return Optional.ofNullable(uuidInStringFormat)
                .map(UUID::fromString);
    }

}
