package com.zeluciojr.enrollments.core.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum Countries {

    BR("BR", CPF::of);

    private final String name;
    private final Function<String, LegalId> personalLegalIdConstructor;

    public static Optional<Countries> of(String value){
        return Stream.of(values())
                .filter(allowedOption -> allowedOption.getName().equalsIgnoreCase(value))
                .findFirst();
    }

    public static String getAllowedOptionsToString() {
        return Stream.of(values())
                .map(Countries::getName)
                .reduce("", (previous, next) -> previous + next + "; ");
    }
}
